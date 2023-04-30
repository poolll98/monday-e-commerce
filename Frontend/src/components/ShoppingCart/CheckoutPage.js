import React from "react";
import { useState, useEffect } from "react";
import { addAddress, getAddresses } from "../../services/address";
import { confirmOrder } from "../../services/order";
import { addPaymentMethod, getPaymentMethods } from "../../services/payment";

import "./CheckoutPage.css";

export default function CheckoutPage({ orderItems }) {
  const [address, setAddress] = useState({});
  const [addressIsStored, setAddressIsStored] = useState(false); // is address stored in DB
  const [paymentMethod, setPaymentMethod] = useState({});
  const [paymentMethodIsStored, setPaymentMethodIsStored] = useState(false); // is paymentMethod stored in DB

  useEffect(() => {
    let isMounted = true;
    getAddresses().then((data) => {
      if (isMounted) {
        // For now, use first address by default.
        data = data.length >= 1 ? data[0] : {};
        setAddressIsStored(data.id !== undefined);
        setAddress(data);
      }
    });
    getPaymentMethods().then((data) => {
      if (isMounted) {
        // For now, use first payment method by default.
        data = data.length >= 1 ? data[0] : {};
        setPaymentMethodIsStored(data.id !== undefined);
        setPaymentMethod(data);
      }
    });
    return () => {
      isMounted = false;
    };
  }, []);

  async function confirmPurchase() {
    if (!addressIsStored) {
      let addressId = await addAddress(address);
      setAddress({ ...address, id: addressId });
    }
    if (!paymentMethodIsStored) {
      let paymentMethodId = await addPaymentMethod(paymentMethod);
      setPaymentMethod({ ...paymentMethod, id: paymentMethodId });
    }
    confirmOrder();
    alert("Purchased");
    console.log(orderItems);
    console.log(address);
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
      <AddressForm address={address} addAddress={addAddress} />
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

function AddressForm({ address, setAddress }) {
  return (
    <div className="form-wrapper">
      <h2>Shipping Address</h2>
      <div className="horizontal-field-group">
        <label>
          <p>Name</p>
          <input
            type="text"
            required={true}
            onChange={(e) =>
              setAddress({ ...address, receiver: e.target.value })
            }
            value={address?.receiver}
          />
        </label>
      </div>
      <div className="horizontal-field-group">
        <label>
          <p>Street</p>
          <input
            type="text"
            required={true}
            onChange={(e) => setAddress({ ...address, street: e.target.value })}
            value={address?.street}
          />
        </label>
        <label>
          <p>Street Number</p>
          <input
            type="number"
            required={true}
            onChange={(e) =>
              setAddress({ ...address, street_nr: e.target.value })
            }
            value={address?.street_nr}
          />
        </label>
      </div>
      <div className="horizontal-field-group">
        <label>
          <p>City Code</p>
          <input
            type="number"
            required={true}
            onChange={(e) =>
              setAddress({ ...address, postalCode: e.target.value })
            }
            value={address?.postalCode}
          />
        </label>
        <label>
          <p>City</p>
          <input
            type="text"
            required={true}
            onChange={(e) => setAddress({ ...address, city: e.target.value })}
            value={address?.city}
          />
        </label>
      </div>
    </div>
  );
}

function PaymentMethodForm({ paymentMethod, setPaymentMethod }) {
  return (
    <div className="form-wrapper">
      <h2>Payment Information</h2>
      <div className="horizontal-field-group">
        <label>
          <p>Name on Card</p>
          <input
            type="text"
            required={true}
            onChange={(e) =>
              setPaymentMethod({
                ...paymentMethod,
                name_on_card: e.target.value,
              })
            }
            value={paymentMethod?.name_on_card}
          />
        </label>
      </div>
      <div className="horizontal-field-group">
        <label>
          <p>Card Number</p>
          <input
            type="text"
            required={true}
            onChange={(e) =>
              setPaymentMethod({ ...paymentMethod, card_nr: e.target.value })
            }
            value={paymentMethod?.card_nr}
          />
        </label>
        <label>
          <p>Expiry Date (MM/yyyy)</p>
          <input
            type="text"
            required={true}
            onChange={(e) =>
              setPaymentMethod({
                ...paymentMethod,
                expiry_date: e.target.value,
              })
            }
            value={paymentMethod?.expiry_date}
          />
        </label>
      </div>
      <div className="horizontal-field-group">
        <label>
          <p>Security Code</p>
          <input
            type="number"
            required={true}
            onChange={(e) =>
              setPaymentMethod({
                ...paymentMethod,
                security_code: e.target.value,
              })
            }
            value={paymentMethod?.security_code}
          />
        </label>
      </div>
    </div>
  );
}
