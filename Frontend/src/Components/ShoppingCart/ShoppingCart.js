import React from "react";
import { useContext, useEffect, useState } from "react";
import { UserContext } from "../UserContext";
import { getShoppingCartItems } from "../../services/shoppingCart";

import CartItem from "./CartItem";

export default function ShoppingCart() {
  const user = useContext(UserContext);
  const [items, setItems] = useState(null);

  useEffect(() => {
    let isMounted = true;
    getShoppingCartItems(user).then((data) => {
      if (isMounted) {
        setItems(data);
      }
    });
    return () => {
      isMounted = false;
    };
  }, [user]);

  if (!user || Object.keys(user).length === 0) {
    return <p>Please log in to access your cart.</p>;
  }
  if (items === null) {
    return <div>Loading...</div>;
  }

  let cartItems = items?.map((item) => <CartItem item={item} key={item.id} />);

  return <ul>{cartItems}</ul>;
}
