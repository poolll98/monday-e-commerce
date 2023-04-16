import React, { useState } from "react";
import LoginPage from "./LoginPage";
import SignupPage from "./SignupPage";

export default function SignupLoginPage({ initiallyLogin = true }) {
  const [isLogin, setIsLogin] = useState(initiallyLogin);

  return (
    <>
      {isLogin ? <LoginPage /> : <SignupPage />}
      <button id="signup-login-switch" onClick={() => setIsLogin(!isLogin)}>
        {isLogin ? "Sign Up Instead" : "Sign In Instead"}
      </button>
    </>
  );
}
