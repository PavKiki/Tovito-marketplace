export interface singleCategory {
    id: number;
    name: string;
    image: string;
}

export interface singleProduct {
    id: number;
    title: string;
    price: number;
    description: string;
    category: singleCategory;
    images: [string, string, string];
}