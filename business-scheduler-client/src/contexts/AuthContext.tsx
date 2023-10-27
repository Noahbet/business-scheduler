import { createContext } from "react";

export type Auth = {
    user: any;
    hasAuthority: (authority: any) => boolean;
    handleLoggedIn: (user: any) => void;
    logout: () => void;
}

const AuthContext = createContext<Auth | null>(null);

export default AuthContext;