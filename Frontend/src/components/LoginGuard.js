import { useContext } from "react";
import { UserContext, UserLoginContext } from "./UserContext";
import { getToken } from "../services/userSessionManagement";
import SignupLoginPage from "./SignupLogin/SignupLoginPage";

export default function LoginGuard({ children }) {
  const user = useContext(UserContext);
  const userAction = useContext(UserLoginContext);

  if (!user || Object.keys(user).length === 0) {
    let token = getToken();
    if (!token) {
      return <SignupLoginPage />;
    }
    userAction({ type: "login" });
  }

  return children;
}
