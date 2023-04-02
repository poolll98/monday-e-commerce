import { createContext, useReducer } from "react";

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

  return (
    <UserContext.Provider value={user}>
      <UserLoginContext.Provider value={dispatch}>
        {children}
      </UserLoginContext.Provider>
    </UserContext.Provider>
  );
}
