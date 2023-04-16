import React from "react";
import { Link } from "react-router-dom";
import { ReactComponent as WishlistIcon } from "./wishlist_icon.svg";

export default function WishlistLink() {
  return (
    <Link to="/wishlist" style={{ width: "30px" }}>
      <WishlistIcon />
    </Link>
  );
}
