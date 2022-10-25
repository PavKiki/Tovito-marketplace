import { singleProduct } from "../models"
import {Product} from "./Product";
import React from "react";
import axios from "axios";
import { useParams } from "react-router-dom";


export function Category() {
    const [axProducts, axSetProducts] = React.useState<singleProduct[]>();
    const [loading, setLoading] = React.useState(false);
    let cId: any = useParams();

    React.useEffect(() => {
        async function fetchProducts() {
            setLoading(true);
            const responsePrdcts = await axios.get<singleProduct[]>('https://api.escuelajs.co/api/v1/categories/'+ cId.categoryId + '/products');
            axSetProducts(responsePrdcts.data);
            setLoading(false);
        };
        console.log(axProducts);
        fetchProducts();
    }, [])

    return (
    <div className="category">
        <div className="mx-auto max-w-2xl pt-5 -z-50">
            {loading && <p className="text-center">Loading...</p>}
            {axProducts?.map(singleProduct => <Product key={singleProduct.id} product={singleProduct}/>)}
        </div>
    </div>
)}