import React, { useState, useContext } from "react";
import "./LoginPage.css";

import { login } from "../../services/userSessionManagement";
import { UserLoginContext } from "../UserContext";

export default function LoginPage() {
  const [username, setUserName] = useState();
  const [password, setPassword] = useState();

  const userAction = useContext(UserLoginContext);

  const handleSubmit = async (e) => {
    e.preventDefault();
    login(username, password);
    userAction({ type: "login" });
  };

  return (
    <div className="login-wrapper">
      <h1>Log In To Access This Page</h1>
      <form onSubmit={handleSubmit}>
        <label>
          <p>Username</p>
          <input
            type="text"
            required={true}
            onChange={(e) => setUserName(e.target.value)}
          />
        </label>
        <label>
          <p>Password</p>
          <input
            type="password"
            required={true}
            onChange={(e) => setPassword(e.target.value)}
          />
        </label>
        <div style={{ margin: "20px" }}>
          <button type="submit">Login</button>
        </div>
      </form>
    </div>
  );
}
