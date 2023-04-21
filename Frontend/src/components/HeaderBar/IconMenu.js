import React from "react";
import ProfileLink from "./ProfileLink";
import WishlistLink from "./WishlistLink";
import ShoppingCartLink from "./ShoppingCartLink";

import "./IconMenu.css";

export default function IconMenu() {
  return (
    <div className="IconMenu">
      <ProfileLink></ProfileLink>
      <WishlistLink></WishlistLink>
      <ShoppingCartLink></ShoppingCartLink>
    </div>
  );
}
