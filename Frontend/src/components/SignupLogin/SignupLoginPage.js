import React, { useState } from "react";
import LoginPage from "./LoginPage";
import SignupPage from "./SignupPage";
import { useContext, useEffect } from "react";
import { UserContext, UserLoginContext } from "../UserContext";
import { getToken } from "../../services/userSessionManagement";

import { useNavigate } from "react-router-dom";

export default function SignupLoginPage({ initiallyLogin = true }) {
  const [isLogin, setIsLogin] = useState(initiallyLogin);

  const user = useContext(UserContext);
  const userAction = useContext(UserLoginContext);

  const navigate = useNavigate();

  useEffect(() => {
    if (!user || Object.keys(user).length === 0) {
      let token = getToken();
      if (!token) {
        return;
      }
    }
    userAction({ type: "login" });
    navigate("/");
  });

  return (
    <>
      {isLogin ? <LoginPage /> : <SignupPage />}
      <button id="signup-login-switch" onClick={() => setIsLogin(!isLogin)}>
        {isLogin ? "Sign Up Instead" : "Sign In Instead"}
      </button>
    </>
  );
}
