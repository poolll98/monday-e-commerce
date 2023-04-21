import React from "react";
import productData from "../mocks/products";
import { Link } from "react-router-dom";

export default function HomePage() {
  const products = productData.map((product) => (
    <ProductTile product={product} key={product.id}></ProductTile>
  ));

  return (
    <>
      <h1>Our Products</h1>
      {products}
    </>
  );
}

function ProductTile({ product }) {
  return (
    <div>
      <h3>
        <Link to={`/products/${product.id}`}>{product.name}</Link>
      </h3>
      <p>Price: ${product.price}</p>
      <img src={product.img} alt={product.id} />
      <hr />
    </div>
  );
}
