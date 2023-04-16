import { createContext, useReducer } from "react";
import { getToken, getUserData } from "../services/userSessionManagement";

export const UserContext = createContext({}); // provides currently logged in user
export const UserLoginContext = createContext(null); // handles login/logout

function userManagementReducer(user, action) {
  switch (action.type) {
    case "login": {
      // TODO: Load user data using token and set user to result.
      let token = getToken();
      if (!token) {
        console.log("Missing token");
        return {};
      }
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

  if ((!user || Object.keys(user).length === 0) && getToken()) {
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
