import React from 'react';
import axios from 'axios';
import {Product} from './components/Product'
import {products} from './data/products'
import { singleCategory } from './models';
import {singleProduct} from './models';
import { NavPanel } from './components/NavPanel';

function App() {

  const [axCategories, axSetCategories] = React.useState<singleCategory[]>();
  const [axProducts, axSetProducts] = React.useState<singleProduct[]>();
  const [loading, setLoading] = React.useState(false);

  async function fetchCategories() {
    setLoading(true);
    const responseCatgs = await axios.get<singleCategory[]>('https://api.escuelajs.co/api/v1/categories');
    axSetCategories(responseCatgs.data);
    setLoading(false);
  };

  async function fetchProducts() {
    setLoading(true);
    const responsePrdcts = await axios.get<singleProduct[]>('https://api.escuelajs.co/api/v1/products?offset=0&limit=10');
    axSetProducts(responsePrdcts.data);
    setLoading(false);
  };

  React.useEffect(() => {
    fetchCategories();
    fetchProducts();
  }, []);

  return (
    <div className="app">
      {axCategories && <NavPanel categories={axCategories!}/>}
      <div className="mx-auto max-w-2xl pt-5 -z-50">
        {loading && <p className="text-center">Loading...</p>}
        {axProducts?.map(singleProduct => <Product key={singleProduct.id} product={singleProduct}/>)}
        {/* {products!.map(singleProduct => <Product product={singleProduct}/>)} */}
      </div>
    </div>
  )
}

export default App;
