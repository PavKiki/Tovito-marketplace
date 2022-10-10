import React from 'react';
import axios from 'axios';
import {Product} from './components/Product'
import {products} from './data/products'
import { singleCategory } from './models';
import {singleProduct} from './models';

function App() {

  const [axCategories, axSetCategories] = React.useState<singleCategory[]>();
  const [axProducts, axSetProducts] = React.useState<singleProduct[]>();
  const [loading, setLoading] = React.useState(false);

  async function fetchCategories() {
    setLoading(true);
    const responseCatgs = await axios.get<singleCategory[]>('https://api.escuelajs.co/api/v1/categories');
    console.log(responseCatgs);
    axSetCategories(responseCatgs.data);
    setLoading(false);
  };

  async function fetchProducts() {
    setLoading(true);
    const responsePrdcts = await axios.get<singleProduct[]>('https://api.escuelajs.co/api/v1/products?offset=0&limit=30');
    console.log(responsePrdcts);
    axSetProducts(responsePrdcts.data);
    setLoading(false);
  };

  React.useEffect(() => {
    fetchCategories();
    fetchProducts();
  }, []);

  return <div className="container mx-auto max-w-2xl pt-5"> 
    {loading && <p className="text-center">Loading...</p>}
    {axProducts?.map(singleProduct => <Product product={singleProduct}/>)}
    {/* {products!.map(singleProduct => <Product product={singleProduct}/>)} */}
  </div>
}

export default App;
