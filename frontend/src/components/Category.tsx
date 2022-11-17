import { singleProduct } from "../models"
import {Product} from "./Product";
import React from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import { ToMainNavPanel } from "./ToMainNavPanel";


export function Category() {
    const [axProducts, axSetProducts] = React.useState<singleProduct[]>();
    const [loading, setLoading] = React.useState(false);
    let cId: any = useParams();

    React.useEffect(() => {
        async function fetchProducts() {
            setLoading(true);
            const responsePrdcts = await axios.get<singleProduct[]>('http://localhost:8080/products/ofcategory?id=' + cId.categoryId);
            axSetProducts(responsePrdcts.data);
            setLoading(false);
        };
        console.log(axProducts);
        fetchProducts();
    }, [])

    return (
    <div className="category">
        <ToMainNavPanel/>
        <div className="mx-auto max-w-2xl pt-5 -z-50">
            {loading && <p className="text-center">Loading...</p>}
            {axProducts?.map(singleProduct => <Product key={singleProduct.productId} product={singleProduct}/>)}
        </div>
    </div>
)}