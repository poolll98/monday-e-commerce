import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { UserContext, UserLoginContext } from "../UserContext";
import { useContext } from "react";
import IconMenu from "./IconMenu";
import { logout } from "../../services/userSessionManagement";
import SearchBar from "./SearchBar";

export default function HeaderBar() {
  const colors = { color: "blue" };

  const user = useContext(UserContext);
  const userAction = useContext(UserLoginContext);

  let navigate = useNavigate();

  return (
    <div
      style={{
        ...colors,
        textAlign: "center",
        display: "flex",
        justifyContent: "space-around",
      }}
    >
      <Link to="/">
        <img
          src="logo.png"
          alt="Logo - Links to Homepage"
          width="170"
          height="32"
        ></img>
      </Link>
      <SearchBar />
      {!user?.username && (
        <button
          onClick={() => {
            return navigate("/login");
          }}
        >
          Log In
        </button>
      )}
      {user?.username && (
        <button
          onClick={() => {
            logout();
            userAction({ type: "logout" });
          }}
        >
          Log Out
        </button>
      )}
      <IconMenu></IconMenu>
    </div>
  );
}
