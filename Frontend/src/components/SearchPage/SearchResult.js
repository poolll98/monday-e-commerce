import React from "react";
import { Link } from "react-router-dom";

export default function SearchResult({ productData }) {
  return (
    <div
      key={productData.id}
      style={{
        borderStyle: "solid",
        margin: "1em",
        width: "20em",
        height: "24em",
      }}
    >
      <h3>
        <Link to={`/products/${productData.id}`}>{productData.name}</Link>
      </h3>
      <p>{productData.description}</p>
      <img
        src={productData.media[0]}
        alt={productData.description}
        style={{ width: "80%" }}
      />
    </div>
  );
}
