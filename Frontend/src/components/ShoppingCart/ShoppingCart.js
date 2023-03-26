import React from "react";
import { useContext } from "react";
import { useState, useEffect, useCallback } from "react";
import { UserContext } from "../UserContext";
import { getShoppingCartItems } from "../../services/shoppingCart";

import CartItem from "./CartItem";

import "./ShoppingCart.css";
import productData from "./MockData";

export default function ShoppingCart() {
  //let items = [{'id': 1}, {'id': 2}, {'id': 3}]; // example items

  //const [activeIndex, setActiveIndex] = useState(0);
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

  // let cartItems = items?.map((item) => <CartItem item={item} key={item.id} />);

  // return cartItems.length ? <ul>{cartItems}</ul> : <p>No items in cart.</p>;

  const [cart, setCart] = useState([]);
  const [allSelected, setAllSelected] = useState(false);

  /* Add item */
  const addItem = useCallback(
    (i) => {
      console.log("addItem", i);
      setCart(
        cart.map((item, _i) => {
          _i === i && item.count++;
          return item;
        })
      );
    },
    [cart]
  );

  /* reduce item */
  const subItem = useCallback(
    (i) => {
      console.log("subItem", i);
      setCart(
        cart.map((item, _i) => {
          _i === i && item.count && item.count--;
          return item;
        })
      );
    },
    [cart]
  );

  /* remove item */
  const removeItem = useCallback(
    (i) => {
      console.log("removeItem", i);
      setCart(cart.filter((item, _i) => _i !== i));
    },
    [cart]
  );

  /* Select and unselect item */
  const toggleItem = useCallback(
    (i) => {
      console.log("toggleItem", i);
      console.log("cart", cart);

      const item = cart.find((item, _i) => {
        console.log("_i", _i);
        return _i === i;
      });

      if (item) {
        console.log("here");
        item.selected = !item.selected;
        setCart([...cart]);
      }
    },
    [cart]
  );

  /* Calculate total quantity */
  const getTotalCount = useCallback(() => {
    // console.log("getTotalItems");
    return cart
      .filter((item) => item.selected)
      .reduce((pv, cv, i) => {
        // console.log(pv, cv);
        return pv + cv.count;
      }, 0);
  }, [cart]);

  /* calculate total amount */
  const getTotalAmount = useCallback(() => {
    // console.log("getTotalAmount");
    return cart
      .filter((item) => item.selected)
      .reduce((pv, cv, i) => {
        // console.log(pv, cv);
        return pv + cv.price * cv.count;
      }, 0);
  }, [cart]);

  useEffect(() => {
    !cart.length && setCart(productData);
  }, [cart]);
  /* ===== Select all -- "two-way data binding" ===== */
  /* 
  Listen to the data change of the shopping cart to switch the selected state
   cart driver allSelected
  */
  useEffect(() => {
    // console.log("useEffect", "cart=>allSelected");
    cart.every((item) => item.selected)
      ? setAllSelected(true)
      : setAllSelected(false);
    // return () => { }
  }, [cart]);

  /*     
  When the user actively uses the [Select All] function
  First modify the cart data (select all or delete all) and then drive allSelected by cart 
  */
  const toggleAllSelected = useCallback(
    (value) => {
      console.log("toggleAllSelected", value);
      // setAllSelected(value)

      // First modify the cart data to (select all or delete all)
      // Modify shopping cart data => [data listening logic A] will trigger setAllSelected
      cart.forEach((item) => (item.selected = value));
      setCart([...cart]);
    },
    [cart]
  );
  /* ===== ENDOF Select all -- "two-way data binding" ===== */

  if (!user || Object.keys(user).length === 0) {
    return <p>Please log in to access your cart.</p>;
  }
  if (items === null) {
    return <div>Loading...</div>;
  }

  /* functional component => render JSX */
  return (
    <div className="cart-wrapper">
      <div className="top">
        <div className="sel-box">
          <input
            type="checkbox"
            checked={allSelected}
            onChange={(e) => {
              console.log("SelectAll input onChanged", !allSelected);
              toggleAllSelected(!allSelected);
            }}
          />
          <i>SelectAll</i>
        </div>

        <span className="imgname-box">Product Name</span>
        <span>Unit Price</span>
        <span className="count-box">Quantity</span>
        <span>Amount</span>
        <span>Operation</span>
      </div>

      <div className="middle">
        <ul>
          {cart.map((item, i) => (
            <CartItem
              index={i}
              item={item}
              addItem={addItem}
              subItem={subItem}
              removeItem={removeItem}
              toggleSelection={toggleItem}
              key={item.name}
            ></CartItem>
          ))}
        </ul>
      </div>

      <div className="bottom">
        <div className="left"></div>
        <div className="count-box">
          <span className="price">{getTotalCount()}</span> items has been
          selected
        </div>
        <div className="amount-box">
          Total Amount{" "}
          <span className="price">{getTotalAmount().toFixed(2)}</span>
        </div>
        <div className="pay-box">
          <i>Buy</i>
        </div>
      </div>
    </div>
  );

  //return (user.username !== undefined && <ul>{cartItems}</ul>);
}
