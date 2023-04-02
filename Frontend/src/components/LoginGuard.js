import { useContext } from "react";
import { UserContext, UserLoginContext } from "./UserContext";
import { getToken } from "../services/userSessionManagement";
import LoginPage from "./LoginPage/LoginPage";

export default function LoginGuard({ children }) {
  const user = useContext(UserContext);
  const userAction = useContext(UserLoginContext);

  if (user.username === undefined) {
    let token = getToken();
    if (token) {
      userAction({ action: "login" });
    } else {
      return <LoginPage />;
    }
  }

  return children;
}
