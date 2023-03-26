import React from "react";
import { useParams } from "react-router-dom";
import productData from "../../mockdata/products";
import { useState } from "react";

export default function ProductPage() {
  const { productId } = useParams();
  const thisProduct = productData.find((prod) => prod.id === productId);
  const [quantity, setQuantity] = useState(0);

  function handleAddClick() {
    setQuantity(quantity + 1);
  }

  function handleReduceClick() {
    if (quantity) {
      setQuantity(quantity - 1);
    } else {
      setQuantity(0);
    }
  }

  return (
    <div>
      <h1>{thisProduct.name}</h1>
      <p>Price: ${thisProduct.price}</p>
      <p>Description: {thisProduct.description}</p>

      <button onClick={handleAddClick}>+</button>
      <button onClick={handleReduceClick}>-</button>
      <p>Quantity: {quantity}</p>
      <p>Total amount: ${thisProduct.price * quantity}</p>

      <button
        onClick={() => {
          alert("Added to cart");
        }}
      >
        Add to Cart
      </button>

      <img src={thisProduct.img} alt={thisProduct.id} />
    </div>
  );
}
