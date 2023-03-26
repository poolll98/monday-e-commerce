import React from "react"
import {Link, useParams} from "react-router-dom"
import productData from "../../MockData/ProductData"
import { useState } from 'react';

function ProductDetail() {
    const {productId} = useParams()
    const thisProduct = productData.find(prod => prod.id === productId)
    const [Quantity, setQuantity] = useState(0);

    const TotalAmount=Quantity*thisProduct.price;


    function handleAddClick() {
        setQuantity(Quantity + 1);
      }

    function handleReduceClick() {
        if (Quantity){
            setQuantity(Quantity-1)

        }else{
            setQuantity(0);

        }
    }


    
    return (
        <div>
            <h1>{thisProduct.name}</h1>
            <p>Price: ${thisProduct.price}</p>
            <p>Description: {thisProduct.description}</p>

            <button onClick={handleAddClick}>
                +
            </button>
            <button onClick={handleReduceClick}>
                -
            </button>
            <p>Quantity: {Quantity}</p>
            <p>Total amount: ${TotalAmount}</p>
            <button
                onClick={() => {
                    alert("Added to cart");
                }}
            >
                Add to Cart
            </button>

            <h3>
                <Link to={`/cart/`}>ShoppingCart</Link>
            </h3>

            <img src={thisProduct.img} alt={thisProduct.id}/>
        </div>
    )
}

export default ProductDetail