import { createContext, useReducer } from "react";
import { getToken, getUserData } from "../services/userSessionManagement";

export const UserContext = createContext({}); // provides currently logged in user
export const UserLoginContext = createContext(null); // handles login/logout

function userManagementReducer(user, action) {
  switch (action.type) {
    case "login": {
      // Assumes that token is set.
      return getUserData();
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

  if (!user && getToken()) {
    dispatch({ type: "login" });
  }

  return (
    <UserContext.Provider value={user}>
      <UserLoginContext.Provider value={dispatch}>
        {children}
      </UserLoginContext.Provider>
    </UserContext.Provider>
  );
}
