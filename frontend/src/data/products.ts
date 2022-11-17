import { singleCategory, singleProduct } from '../models';
import {categories} from './categories';

export const products: singleProduct[] = [
    {
        productId: 4,
        title: 'Small Cotton Chips',
        description: 'Ergonomic executive chair upholstered in bonded black leather and PVC padded seat and back for all-day comfort and support',
        price: 713,
        category: categories[0],
        user: null
    },
    {
        productId: 5,
        title: "Incredible Concrete Gloves",
        description: "The Football Is Good For Training And Recreational Purposes",
        price: 289,
        category: categories[0],
        user: null
    }
]