import React from 'react';
import { useContext } from 'react';
import { useState } from 'react';
import { UserContext } from '../UserContext';

import CartItem from './CartItem';

export default function ShoppingCart() {
  let items = [{'id': 1}, {'id': 2}, {'id': 3}]; // example items

  const [activeIndex, setActiveIndex] = useState(0);
  const user = useContext(UserContext);

  let cartItems = items.map(item => <CartItem item={item} key={item.id} isActive={activeIndex === item.id} onHighlight={() => setActiveIndex(item.id)}/>)

  return (user.username !== undefined && <ul>{cartItems}</ul>);
}