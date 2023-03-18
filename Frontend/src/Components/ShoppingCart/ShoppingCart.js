import React from 'react';
import { useState } from 'react';

import CartItem from './CartItem';

export default function ShoppingCart() {
  let items = [{'id': 1}, {'id': 2}, {'id': 3}]; // example items

  const [activeIndex, setActiveIndex] = useState(0);

  let cartItems = items.map(item => <CartItem item={item} key={item.id} isActive={activeIndex === item.id} onHighlight={() => setActiveIndex(item.id)}/>)

  return <ul>{cartItems}</ul>;
}