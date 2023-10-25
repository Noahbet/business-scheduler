import { useContext } from "react";
import { NavLink, Link } from "react-router-dom";

import AuthContext from "../contexts/AuthContext";

function NavBar() {
  const { user, logout } = useContext(AuthContext);

  return (
    <header className="w-screen bg-gray-500 p-2">
      <nav className="w-screen flex flex-wrap items-center justify-between mx-auto p-0">
        <NavLink to="/" className={({ isActive }) => isActive ? "font-bold p-3" : "" }>
          Appoints
        </NavLink>
        <ul>
          <li>
            <NavLink to="/" className={({ isActive }) => isActive ? "font-bold" : "" }>
              Home
            </NavLink>
          </li>
          {user && (
            <li>
              <NavLink to="/dashboard" className={({ isActive }) => isActive ? "font-bold" : "" }>
                Profile
              </NavLink>
            </li>
          )}
        </ul>

        {!user && <div className="m-4 font-bold">
          <Link to="/login">Login</Link>
            { '  |  ' }
          <Link to="/signup">Sign Up?</Link>
        </div>}
        {user && (
          <div>
            <span>
              {user.username}
            </span>
            <button className="text-bold" onClick={logout}>
              Log out
            </button>
          </div>
        )}
      </nav>
    </header>
  );
}

export default NavBar;
