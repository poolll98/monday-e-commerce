import React from 'react';
import CartItem from './CartItem';

export default function ShoppingCart() {
  let items = [{'id': 1}, {'id': 2}]; // example items

  let cartItems = items.map(item => <CartItem item={item} key={item.id}/>)

  return <ul>{cartItems}</ul>;
}