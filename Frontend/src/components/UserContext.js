import { createContext, useReducer } from 'react';

export const UserContext = createContext(null); // provides currently logged in user
export const UserLoginContext = createContext(null); // handles login/logout

function userManagementReducer(user, action) {
    switch (action.type) {
        case 'login': {
            return { "username": "The User" };
        }
        case 'logout': {
            return {};
        }
        default: {
            throw Error('Unknown action: ' + action.type);
        }
    }
}


export function UserProvider({ children }) {
    const [user, dispatch] = useReducer(
        userManagementReducer,
        {}
    );

    return (
        <UserContext.Provider value={user}>
            <UserLoginContext.Provider value={dispatch}>
                {children}
            </UserLoginContext.Provider>
        </UserContext.Provider>
    );
}