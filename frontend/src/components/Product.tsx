import React from 'react';
import axios from 'axios';
import { singleProduct, singlePhoto } from '../models';
import '../css_components/product.css';

interface productProps {
    product: singleProduct
}

export function Product(props: productProps) {

    const [imgIndex, setImgIndex] = React.useState(0);
    const [axPhotos, axSetPhotos] = React.useState<singlePhoto[]>();

    async function fetchPhotos() {
        const responsePhotos = await axios.get<singlePhoto[]>('http://localhost:8080/photo/ofproduct?id='+props.product.productId);
        axSetPhotos(responsePhotos.data);
    }

    function indexHandler(len: number, increase: boolean): void {
        if ((increase && imgIndex === (len - 1)) || (!increase && imgIndex === 0)) return;
        if (increase) setImgIndex(imgIndex+1);
        else setImgIndex(imgIndex-1);
    }

    React.useEffect(() => {
        fetchPhotos();
      }, []);

    return (    
        <div className="border py-2 px-4 rounded flex flex-col mb-2">
            <h1><strong>{props.product.title}</strong></h1>
            {axPhotos && axPhotos.length !== 0 && <img alt='' src={axPhotos.at(imgIndex).path}></img>}
            {axPhotos && axPhotos.length !== 0 && <div className='flex border-b space-x-0'>
                <div className="w-1/2 text-left">
                    {imgIndex !== 0 && 
                        <button onClick={() => indexHandler(axPhotos.length, false)}>&#8592;</button>}
                </div>
                <div className="w-1/2 text-right">
                    {imgIndex !== axPhotos.length - 1 && 
                        <button onClick={() => indexHandler(axPhotos.length, true)}>&#8594;</button>}
                </div>
            </div>}
            <p>{props.product.description}</p>
            <p>Price: <strong>{props.product.price}</strong> rubles</p>
            <p className='text-left'>Category: <b>{props.product.category.title}</b></p>
        </div>
    )
}