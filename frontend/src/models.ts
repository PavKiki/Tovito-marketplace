export interface singleCategory {
    category_id: number;
    title: string;
    description: string;
}

export interface singleProduct {
    product_id: number;
    title: string;
    description: string;
    price: number;
    category: singleCategory;
    user: singleUser;
}

export interface singleUser {
    user_id: number;
    name: string;
    email: string;
    password: string;
    role: string;
    wallet_id: string;
    balance: number;
    frozen_balance: number;
}

export interface singlePhoto {
    photo_id: number;
    type: string;
    path: string;
    product_photo: singleProduct;
}

export interface singleComment {
    comment_id: number;
    rating: number;
    text: string;
    product_comment: singleProduct;
    written_comments: singleUser;
}