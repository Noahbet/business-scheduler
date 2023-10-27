import { useContext } from "react";
import { NavLink, Link } from "react-router-dom";

import AuthContext from "../contexts/AuthContext";

function NavBar() {
  const auth = useContext( AuthContext );

  return (
    <header className="w-screen h-15 bg-gray-500 p-2">
      <nav className="w-screen flex flex-wrap items-center justify-between mx-auto p-0">
        <NavLink to="/" className={({ isActive }) => isActive ? "p-3 hover:text-white" : "" }>
          Appoints
        </NavLink>
        <ul className="flex flex-wrap justify-between items-center">
          <li className="m-3">
            <NavLink to="/" className={({ isActive }) => isActive ? "hover:text-white" : "" }>
              Home
            </NavLink>
          </li>
          {auth?.user && (
            <li className="m-3">
              <NavLink to="/profile" className={({ isActive }) => isActive ? "hover:text-white" : "" }>
                Profile
              </NavLink>
            </li>
          )}
        </ul>

        {!auth?.user && <div>
          <Link className="m-3 font-bold hover:text-white" to="/login">Login</Link>
            { '  |  ' }
          <Link className="m-3 font-bold hover:text-white" to="/signup">Sign Up?</Link>
        </div>}
        {auth?.user && (
          <div>
            <span>
              {auth?.user.username}
            </span>
            <Link className="text-bold hover:text-white m-5" onClick={auth?.logout} to="/">
              Log out
            </Link>
          </div>
        )}
      </nav>
    </header>
  );
}

export default NavBar;
