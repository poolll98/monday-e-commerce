import React from "react";
import { Link } from "react-router-dom";

export default function SearchResult({ productData }) {
  return (
    <div key={productData.id}>
      <h3>
        <Link to={`/products/${productData.id}`}>{productData.name}</Link>
      </h3>
      <p>{productData.description}</p>
      <img src={productData.media[0]} alt={productData.description} />
      <hr />
    </div>
  );
}
