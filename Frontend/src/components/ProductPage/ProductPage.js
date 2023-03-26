import React from "react";
import { Link, useParams } from "react-router-dom";
import productData from "../../mockdata/products";
import { useState } from "react";

export default function Products() {
  const { productId } = useParams();
  const thisProduct = productData.find((prod) => prod.id === productId);
  const [index, setIndex] = useState(0);

  function handleAddClick() {
    setIndex(index + 1);
  }

  function handleReduceClick() {
    if (index) {
      setIndex(index - 1);
    } else {
      setIndex(0);
    }
  }

  return (
    <div>
      <h1>{thisProduct.name}</h1>
      <p>Price: ${thisProduct.price}</p>
      <p>Description: {thisProduct.description}</p>

      <button onClick={handleAddClick}>+</button>
      <button onClick={handleReduceClick}>-</button>
      <p>Quantity: {index}</p>
      <p>Total amount: ${thisProduct.price * index}</p>
      <h3>
        <Link to={`/cart/`}>ShoppingCart</Link>
      </h3>

      <img src={thisProduct.img} alt={thisProduct.id} />
    </div>
  );
}
