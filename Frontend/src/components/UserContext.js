import { createContext, useReducer } from "react";
import { getToken } from "../services/userSessionManagement";
import LoginPage from "./LoginPage/LoginPage";

export const UserContext = createContext({}); // provides currently logged in user
export const UserLoginContext = createContext(null); // handles login/logout

function userManagementReducer(user, action) {
  switch (action.type) {
    case "login": {
      // TODO: Load user data using token and set user to result.
      return { username: "The User" };
    }
    case "logout": {
      return {};
    }
    default: {
      throw Error("Unknown action: " + action.type);
    }
  }
}

export function UserProvider({ children }) {
  const [user, dispatch] = useReducer(userManagementReducer, {});

  if (user.username === undefined) {
    let token = getToken();
    if (token) {
      dispatch({ action: "login" });
    } else {
      return <LoginPage />;
    }
  }

  return (
    <UserContext.Provider value={user}>
      <UserLoginContext.Provider value={dispatch}>
        {children}
      </UserLoginContext.Provider>
    </UserContext.Provider>
  );
}
