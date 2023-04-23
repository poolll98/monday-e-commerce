import React from "react";
import { useState, useEffect } from "react";
import { getAddress } from "../../services/address";

import "./CheckoutPage.css";

export default function CheckoutPage({ orderItems }) {
  // TODO: Confirm to trigger payment.
  const [address, setAddress] = useState({});

  useEffect(() => {
    let isMounted = true;
    getAddress().then((data) => {
      console.log("User address:");
      console.log(data);
      if (isMounted) {
        setAddress(data);
      }
    });
    return () => {
      isMounted = false;
    };
  }, [address]);

  function confirmPurchase() {
    alert("Purchased");
  }

  return (
    <div style={{ marginTop: "3em" }}>
      <div className="order-list-container">
        {orderItems.map((order) => (
          <OrderEntry order={order} key={order.id}></OrderEntry>
        ))}
      </div>
      <div>
        Total:{" "}
        {orderItems
          .reduce((total, item, i) => total + item.quantity * item.price, 0)
          .toFixed(2)}
      </div>
      <div className="address-wrapper">
        <h2>Shipping Address</h2>
        <div className="horizontal-field-group">
          <label>
            <p>Name</p>
            <input
              type="text"
              required={true}
              onChange={(e) => (address.receiver = e.target.value)}
              value={address.receiver}
            />
          </label>
        </div>
        <div className="horizontal-field-group">
          <label>
            <p>Street</p>
            <input
              type="text"
              required={true}
              onChange={(e) => (address.street = e.target.value)}
              value={address.street}
            />
          </label>
          <label>
            <p>Street Number</p>
            <input
              type="number"
              required={true}
              onChange={(e) => (address.streetNumber = e.target.value)}
              value={address.streetNumber}
            />
          </label>
        </div>
        <div className="horizontal-field-group">
          <label>
            <p>City Code</p>
            <input
              type="number"
              required={true}
              onChange={(e) => (address.cityCode = e.target.value)}
              value={address.cityCode}
            />
          </label>
          <label>
            <p>City</p>
            <input
              type="text"
              required={true}
              onChange={(e) => (address.city = e.target.value)}
              value={address.city}
            />
          </label>
        </div>
      </div>
      <button
        className="blue-button"
        style={{ marginTop: "2em" }}
        onClick={confirmPurchase}
      >
        Confirm Purchase
      </button>
    </div>
  );
}

function OrderEntry({ order }) {
  return (
    <div className="order-entry-container">
      <img src={order.img} alt="order entry" className="order-entry-image" />

      <div>
        <p>{order.name}</p>
      </div>

      <div>
        <p>{order.price} Fr.</p>
      </div>

      <div>
        <p>Quantity: {order.quantity}</p>
      </div>
    </div>
  );
}
