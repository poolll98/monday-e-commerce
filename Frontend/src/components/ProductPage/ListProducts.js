import React from "react";
import productData from "../../mocks/products";
import { Link } from "react-router-dom";

const ListProducts = () => {
  const products = productData.map((product) => {
    return (
      <div key={product.id}>
        <h3>
          <Link to={`/products/${product.id}`}>{product.name}</Link>
        </h3>
        <p>Price: ${product.price}</p>
        <img src={product.img} alt={product.id} />
        <hr />
      </div>
    );
  });

  return (
    <>
      <h1>Products Page</h1>
      {products}
    </>
  );
};

export default ListProducts;
