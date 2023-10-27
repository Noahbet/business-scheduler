import { useState, useContext } from "react";
import { Link, useNavigate } from "react-router-dom";
import { login } from "../services/authAPI";

import AuthContext from "../contexts/AuthContext";
import ValidationSummary from "./ValidationSummary";

interface Credentials {
  username: string;
  password: string;
}

function LoginForm() {
  const [credentials, setCredentials] = useState({
    username: "",
    password: "",
  });
  const [errors, setErrors] = useState([""]);

  const auth = useContext( AuthContext );

  const navigate = useNavigate()

  const handleSubmit = (evt: any) => {
    evt.preventDefault();
    setErrors([]);
    login(credentials)
      .then(user => {
        auth?.handleLoggedIn(user);
        navigate("/");
      })
      .catch(err => {
        setErrors(['Invalid username/password.']);
      });
  };

  const handleChange = (evt: any) => {
    const nextCredentials: any = {...credentials};
    nextCredentials[evt.target.name] = evt.target.value;
    setCredentials(nextCredentials);
  };

  return (
    <div className="w-screen h-screen bg-gray-600 hover:bg-fixed flex flex-col items-center">
      <div className="w-1/3 h-3/7 bg-gray-400 flex flex-col items-center rounded my-10">      
        <ValidationSummary errors={errors} />
        <form onSubmit={handleSubmit}>
          <div className="p-4">
            <div className="flex flex-col items-center">
              <label htmlFor="username">Email</label>
              <input
                type="text"
                className="m-2 border border-maroon-200 p-1 rounded"
                id="username"
                name="username"
                value={credentials.username}
                onChange={handleChange}
                required
              />
            </div>
            <div className="flex flex-col items-center">
              <label htmlFor="password">Password</label>
              <input
                type="password"
                className="m-2 border border-maroon-200 p-1 rounded"
                id="password"
                name="password"
                value={credentials.password}
                onChange={handleChange}
                required
              />
            </div>
            <div className="p-2 flex flex-col items-center">
            <button type="submit" className="border border-gray-500 bg-maroon-200 hover:bg-maroon-100 p-2 m-3 rounded text-white">
                Log in
              </button>
              <Link to="/" className="border border-gray-500 bg-maroon-200 hover:bg-maroon-100 p-2 rounded text-white">
                Cancel
              </Link>
            </div>
          </div>
        </form>
        </div>
    </div>
  );
}

export default LoginForm;
