import React from "react";
import { Link } from "react-router-dom";
import { UserContext, UserLoginContext } from "./UserContext";
import { useContext } from "react";

export default function HeaderBar() {
  const colors = { color: "blue" };

  const user = useContext(UserContext);
  const userAction = useContext(UserLoginContext);

  return (
    <div style={{ ...colors, textAlign: "center" }}>
      <Link to="/">MarketMate</Link>
      <h3>Here be the header</h3>
      {user.username === undefined && (
        <button
          onClick={() => {
            userAction({ type: "login" });
          }}
        >
          Log In
        </button>
      )}
      {user.username !== undefined && (
        <button
          onClick={() => {
            userAction({ type: "logout" });
          }}
        >
          Log Out
        </button>
      )}
      <Link to="/cart">Shopping Cart</Link>
    </div>
  );
}
