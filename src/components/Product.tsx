import React from 'react';
import { singleProduct } from '../models';

interface productProps {
    product: singleProduct
}

export function Product(props: productProps) {
    return (    
        <div className="border py-2 px-4 rounded flex flex-col text-center mb-2">
            <h1><strong>{props.product.title}</strong></h1>
            <img src={props.product.images[0]}></img>
            <p>{props.product.description}</p>
            <p>Price: <strong>{props.product.price}</strong> rubles</p>
            <p className='text-left'>Category: <b>{props.product.category.name}</b></p>
            <p><img className='text-right' width="128" src={props.product.category.image}></img></p>
        </div>
    )
}