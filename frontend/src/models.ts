export interface singleCategory {
    category_id: number;
    title: string;
    description: string;
}

export interface singleProduct {
    productId: number;
    title: string;
    description: string;
    price: number;
    category: singleCategory;
    user: singleUser;
}

export interface singleUser {
    userId: number;
    name: string;
    email: string;
    role: string;
    walletId: string;
    balance: number;
    frozenBalance: number;
}

export interface singlePhoto {
    photoId: number;
    type: string;
    path: string;
    product: singleProduct;
}

export interface singleComment {
    commentId: number;
    rating: number;
    text: string;
    productComment: singleProduct;
    commentByUser: singleUser;
}