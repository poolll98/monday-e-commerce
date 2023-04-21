import React from "react";

const CartItem = ({
  selected,
  item,
  addItem,
  subItem,
  removeItem,
  toggleSelection,
}) => {
  function addToWishlist(itemId) {
    // TODO: Add to wishlist.
    console.log("Added item to wishlist.");
  }

  return (
    <li className="item">
      <div className="sel-box">
        <input
          type="checkbox"
          checked={selected}
          onChange={() => toggleSelection(item.id)}
        />
      </div>

      <div className="imgname-box">
        <img src={item.img} alt="item" />
      </div>

      <div className="product-name">
        <p>{item.name}</p>
      </div>

      <div className="button-box">
        <div className="action-box">
          <button
            className="white-button"
            style={{ marginRight: "10px" }}
            onClick={() => removeItem(item.id)}
          >
            Remove
          </button>
          <button
            className="white-button"
            onClick={() => addToWishlist(item.id)}
          >
            Wishlist♡
          </button>
        </div>

        <div className="price-box">
          &nbsp; &nbsp; Fr. &nbsp; &nbsp;{" "}
          <span className="price">{item.price}</span>
        </div>

        <div className="count-box">
          <button onClick={() => subItem(item.id)}>-</button>
          <input
            value={item.quantity}
            onChange={(newVal) => {
              console.log(newVal);
            }}
          />
          <button
            onClick={() => {
              console.log("on + click", item.id);
              addItem(item.id);
            }}
          >
            +
          </button>
        </div>

        <div className="action-box">
          <button className="blue-button" onClick={() => subItem(item.id)}>
            Buy
          </button>
        </div>
      </div>
    </li>
  );
};

export default CartItem;
