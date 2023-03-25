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
    getShoppingCartItems().then((data) => {
      if (isMounted) {
        setItems(data);
      }
    });
    return () => {
      isMounted = false;
    };
  }, []);

  const [activeIndex, setActiveIndex] = useState(0);

  if (items === null) {
    return <div>Loading...</div>;
  }

  let cartItems = items?.map((item) => (
    <CartItem
      item={item}
      key={item.id}
      isActive={activeIndex === item.id}
      onHighlight={() => setActiveIndex(item.id)}
    />
  ));

  return user?.username !== undefined && <ul>{cartItems}</ul>;
}
