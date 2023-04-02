import React, { useState, useContext } from "react";
import "./LoginPage.css";

import { login } from "../../services/UserSessionService";
import { UserLoginContext } from "../UserContext";

export default function LoginPage() {
  const [username, setUserName] = useState();
  const [password, setPassword] = useState();

  const userAction = useContext(UserLoginContext);

  const handleSubmit = async (e) => {
    e.preventDefault();
    await login(username, password);
    userAction({ type: "login" });
  };

  return (
    <div className="login-wrapper">
      <h1>Please Log In</h1>
      <form onSubmit={handleSubmit}>
        <label>
          <p>Username</p>
          <input type="text" onChange={(e) => setUserName(e.target.value)} />
        </label>
        <label>
          <p>Password</p>
          <input
            type="password"
            onChange={(e) => setPassword(e.target.value)}
          />
        </label>
        <div>
          <button type="submit">Submit</button>
        </div>
      </form>
    </div>
  );
}
