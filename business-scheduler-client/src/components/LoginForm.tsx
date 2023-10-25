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

  const { handleLoggedIn } = useContext(AuthContext);

  const navigate = useNavigate()

  const handleSubmit = (evt: any) => {
    evt.preventDefault();
    setErrors([]);
    login(credentials)
      .then(user => {
        handleLoggedIn(user);
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
    <div>
      <ValidationSummary errors={errors} />
      <form onSubmit={handleSubmit}>
        <div>
          <div className="form-group">
            <label htmlFor="label">Username</label>
            <input
              type="text"
              className="form-control"
              id="username"
              name="username"
              value={credentials.username}
              onChange={handleChange}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="label">Password</label>
            <input
              type="password"
              className="form-control"
              id="password"
              name="password"
              value={credentials.password}
              onChange={handleChange}
              required
            />
          </div>
          <div>
            <Link to="/" className="btn btn-secondary">
              Cancel
            </Link>
            <button type="submit" className="btn btn-primary">
              Log in
            </button>
          </div>
        </div>
      </form>
    </div>
  );
}

export default LoginForm;
