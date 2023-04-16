import { useContext } from "react";
import { UserContext } from "./UserContext";
import { getToken } from "../services/userSessionManagement";
import SignupLoginPage from "./SignupLogin/SignupLoginPage";

export default function LoginGuard({ children }) {
  const user = useContext(UserContext);

  console.log(user);
  if (!user || Object.keys(user).length === 0) {
    let token = getToken();
    if (!token) {
      return <SignupLoginPage />;
    }
  }

  return children;
}
