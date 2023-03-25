import React from "react";

export default function CartItem({ item, isActive, onHighlight }) {
  return (
    <li style={{ color: isActive ? "red" : "black" }}>
      {item.name} with id {item.id}{" "}
      <button onClick={onHighlight}>Highlight</button>
    </li>
  );
}
