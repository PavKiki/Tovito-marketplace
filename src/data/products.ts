import { singleCategory, singleProduct } from '../models';
import {categories} from './categories';

export const products: singleProduct[] = [
    {
        id: 4,
        title: 'Small Cotton Chips',
        price: 713,
        description: 'Ergonomic executive chair upholstered in bonded black leather and PVC padded seat and back for all-day comfort and support',
        category: categories[0],
        images: [
            'https://api.lorem.space/image/furniture?w=640&h=480&r=9164',
            'https://api.lorem.space/image/furniture?w=640&h=480&r=2268', 
            'https://api.lorem.space/image/furniture?w=640&h=480&r=1732'
        ]
    },
    {
        id: 5,
        title: "Incredible Concrete Gloves",
        price: 289,
        description: "The Football Is Good For Training And Recreational Purposes",
        category: categories[0],
        images: [
            "https://api.lorem.space/image/fashion?w=640&h=480&r=299",
            "https://api.lorem.space/image/fashion?w=640&h=480&r=9881",
            "https://api.lorem.space/image/fashion?w=640&h=480&r=3306"
        ]
    }
]