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
          value={item.count}
          onChange={(newVal) => {
            console.log(newVal);
          }}
        />
        <button
          onClick={() => {
            console.log("on + click", index);
            addItem(index);
          }}
        >
          +
        </button>
      </div>

      <div className="amount-box">
        <span className="price">{(item.price * item.count).toFixed(2)}</span>
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
