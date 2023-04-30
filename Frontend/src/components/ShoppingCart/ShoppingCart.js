import React from "react";
import { useContext } from "react";
import { useState, useEffect, useCallback } from "react";
import { UserContext } from "../UserContext";
import { getShoppingCartItems } from "../../services/shoppingCart";

import CartItem from "./CartItem";

import "./ShoppingCart.css";
import LoginGuard from "../LoginGuard";
import CheckoutPage from "./CheckoutPage";

export default function ShoppingCart() {
  const user = useContext(UserContext);

  const [cart, setCart] = useState([]);
  const [allSelected, setAllSelected] = useState(false);
  const [selectedItems, setSelectedItems] = useState([]); // contains the ids of the selected items

  const [checkingOut, setCheckingOut] = useState(false); // flag if checkout page should be shown

  useEffect(() => {
    let isMounted = true;
    getShoppingCartItems(user).then((data) => {
      console.log("Shopping cart items:");
      console.log(data);
      if (isMounted) {
        setCart(data);
      }
    });
    return () => {
      isMounted = false;
    };
  }, [user]);

  /* Add item */
  const addItem = useCallback(
    (id) => {
      console.log("addItem", id);
      setCart(
        cart.map((item) => {
          if (item.id === id) {
            item.quantity += 1;
          }
          return item;
        })
      );
    },
    [cart]
  );

  /* reduce item */
  const subItem = useCallback(
    (id) => {
      console.log("subItem", id);
      setCart(
        cart.map((item) => {
          if (item.id === id) {
            item.quantity -= 1;
          }
          return item;
        })
      );
    },
    [cart]
  );

  /* remove item */
  const removeItem = useCallback(
    (id) => {
      console.log("removeItem", id);
      setCart(cart.filter((item) => item.id !== id));
    },
    [cart]
  );

  /* Select and unselect item */
  const toggleItem = (id) => {
    let newSelectedItems = [];
    if (selectedItems.includes(id)) {
      newSelectedItems = selectedItems.filter(
        (selectedId) => selectedId !== id
      );
    } else {
      newSelectedItems = selectedItems.map((el) => el);
      newSelectedItems.push(id);
    }
    setSelectedItems(newSelectedItems);
  };

  /* Calculate total quantity */
  const getTotalCount = useCallback(() => {
    console.log("getTotalItems");
    let selectedCartItems = selectedItems
      .map((id) => cart.filter((item) => item.id === id))
      .flat();
    console.log(selectedCartItems);
    return selectedCartItems.reduce(
      (total, item, i) => total + item.quantity,
      0
    );
  }, [cart, selectedItems]);

  /* calculate total amount */
  const getTotalAmount = useCallback(() => {
    console.log("getTotalAmount");
    let selectedCartItems = selectedItems
      .map((id) => cart.filter((item) => item.id === id))
      .flat();
    return selectedCartItems.reduce(
      (total, item, i) => total + item.price * item.quantity,
      0
    );
  }, [cart, selectedItems]);

  /*     
  When the user actively uses the [Select All] function
  First modify the cart data (select all or delete all) and then drive allSelected by cart 
  */
  const toggleAllSelected = (value) => {
    console.log("toggleAllSelected", value);
    if (value) {
      let itemIds = cart.map((item) => item.id);
      setSelectedItems(itemIds);
    } else {
      setSelectedItems([]);
    }
    setAllSelected(value);
  };
  /* ===== ENDOF Select all -- "two-way data binding" ===== */

  if (cart === null) {
    return <div>Loading...</div>;
  }

  if (checkingOut) {
    return <CheckoutPage orderItems={cart}></CheckoutPage>;
  }

  /* functional component => render JSX */
  return (
    <LoginGuard>
      <div className="cart-wrapper">
        <div className="cart-header">
          <h3>Shopping Cart</h3>
        </div>

        <div className="middle">
          <ul>
            {cart.map((item) => (
              <CartItem
                selected={selectedItems.includes(item.id)}
                item={item}
                addItem={addItem}
                subItem={subItem}
                removeItem={removeItem}
                toggleSelection={toggleItem}
                key={item.id}
              ></CartItem>
            ))}
          </ul>
        </div>

        <div className="bottom">
          <div className="sel-box">
            <input
              type="checkbox"
              checked={allSelected}
              onChange={(e) => {
                console.log("SelectAll input onChanged", !allSelected);
                toggleAllSelected(!allSelected);
              }}
            />
            <i> SelectAll </i>
          </div>
          <div className="left"></div>
          <div className="count-box">
            <span className="price">{getTotalCount()}</span> Items selected
          </div>
          <div className="amount-box">
            Total Amount{" "}
            <span className="price">{getTotalAmount().toFixed(2)}</span>
          </div>
          <div className="pay-box">
            <button
              className="blue-button"
              onClick={() => setCheckingOut(true)}
            >
              Buy
            </button>
          </div>
        </div>
      </div>
    </LoginGuard>
  );
}
