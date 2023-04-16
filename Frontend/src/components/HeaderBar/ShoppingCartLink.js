import React from "react";
import { Link } from "react-router-dom";
import { ReactComponent as ShoppingCartIcon } from "./shopping_cart_icon.svg";

export default function ShoppingCartLink() {
  return (
    <Link to="/cart">
      <ShoppingCartIcon style={{ width: "30px", height: "30px" }} />
    </Link>
  );
}
