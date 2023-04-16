import React, { useState, useContext } from "react";
import "./form-styling.css";

import { signup } from "../../services/userSessionManagement";
import { UserLoginContext } from "../UserContext";

export default function LoginPage() {
  const [username, setUserName] = useState();
  const [password, setPassword] = useState();
  const [email, setEmail] = useState();
  const [firstname, setFirstname] = useState();
  const [lastname, setLastname] = useState();

  const userAction = useContext(UserLoginContext);

  const handleSubmit = async (e) => {
    e.preventDefault();
    let userData = {
      username: username,
      password: password,
      email: email,
      firstname: firstname,
      lastName: lastname,
    };
    signup(userData);
    userAction({ type: "login" });
  };

  return (
    <div className="form-wrapper">
      <h1>Create a New Account</h1>
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
        <label>
          <p>E-Mail</p>
          <input
            type="text"
            required={true}
            onChange={(e) => setEmail(e.target.value)}
          />
        </label>
        <label>
          <p>Firstname</p>
          <input
            type="text"
            required={true}
            onChange={(e) => setFirstname(e.target.value)}
          />
        </label>
        <label>
          <p>Lastname</p>
          <input
            type="text"
            required={true}
            onChange={(e) => setLastname(e.target.value)}
          />
        </label>
        <div className="form-submit-button">
          <button type="submit">Signup</button>
        </div>
      </form>
    </div>
  );
}
