import React from "react";

const CartItem = ({
  index,
  item,
  addItem,
  subItem,
  removeItem,
  toggleSelection,
}) => {
  return (
    <li className="item">
      <div className="sel-box">
        <input
          type="checkbox"
          checked={item.selected}
          onChange={() => toggleSelection(index)}
        />
      </div>

      <div className="imgname-box">
        <img src={item.img} alt="item" />
      </div>

      <div>
        <p>{item.name}</p>
      </div>

      <div className="price-box">
        ￥<span className="price">{item.price}</span>
      </div>

      <div className="count-box">
        <button onClick={() => subItem(index)}>-</button>
        <input
          value={item.amount}
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

      <div className="amount-box">
        <span className="price">{(item.price * item.amount).toFixed(2)}</span>
      </div>
      <div className="action-box">
        <a href="#" onClick={() => removeItem(index)}>
          Remove Item
        </a>
      </div>
    </li>
  );
};

export default CartItem;
