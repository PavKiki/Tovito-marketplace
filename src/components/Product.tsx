import React from 'react';
import { singleProduct } from '../models';
import '../css_components/product.css';

interface productProps {
    product: singleProduct
}

export function Product(props: productProps) {

    const [imgIndex, setImgIndex] = React.useState(0);

    function indexHandler(len: number, increase: boolean): void {
        if ((increase && imgIndex === (len - 1)) || (!increase && imgIndex === 0)) return;
        if (increase) setImgIndex(imgIndex+1);
        else setImgIndex(imgIndex-1);
    }

    return (    
        <div className="border py-2 px-4 rounded flex flex-col mb-2">
            <h1><strong>{props.product.title}</strong></h1>
            <img alt='' src={props.product.images[imgIndex]}></img>
            <div className='flex border-b space-x-0'>
                <div className="w-1/2 text-left">
                    {imgIndex !== 0 && 
                        <button onClick={() => indexHandler(props.product.images.length, false)}>&#8592;</button>}
                </div>
                <div className="w-1/2 text-right">
                    {imgIndex !== props.product.images.length - 1 && 
                        <button onClick={() => indexHandler(props.product.images.length, true)}>&#8594;</button>}
                </div>
            </div>
            <p>{props.product.description}</p>
            <p>Price: <strong>{props.product.price}</strong> rubles</p>
            <p className='text-left'>Category: <b>{props.product.category.name}</b></p>
            <p><img alt='' className='category-image' width="128" src={props.product.category.image}></img></p>
        </div>
    )
}