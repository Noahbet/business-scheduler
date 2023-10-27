import { useState } from "react";
import { Link } from "react-router-dom";
import { register } from "../services/authAPI";

import ValidationSummary from "./ValidationSummary";

function SignUpForm() {
  const [errors, setErrors] = useState([""]);
  const [credentials, setCredentials] = useState({
    username: "",
    password: "",
    confirmPassword: "",
  });
  const [success, setSuccess] = useState(false);

  const handleChange = (evt: any) => {
    const nextCredentials: any = { ...credentials };
    nextCredentials[evt.target.name] = evt.target.value;
    setCredentials(nextCredentials);
  };

  const handleSubmit = (evt: any) => {
    evt.preventDefault();
    setErrors([]);
    if (!validateForm()) {
      setErrors(["Passwords do not match!"]);
      return;
    }

    register(credentials).then((data) => {
      if (data && data.errors != null) {
        setErrors(data.errors);
      } else {
        setSuccess(true);
      }
    });
  };

  const validateForm = () => {
    return credentials.password === credentials.confirmPassword;
  };

  return (
    <div className="w-screen h-screen bg-gray-600 hover:bg-fixed flex flex-col items-center">
      <div className="w-1/3 h-7/9 bg-gray-400 flex flex-col items-center rounded my-10 ">
        <ValidationSummary errors={errors} />
        {success ? (
          <div className="p-6 font-bold">
            Congratulations {credentials.username}, you have been registered.
            Login <Link to="/login" className="text-maroon-200 hover:text-maroon-100">here</Link>.
          </div>
        ) : (
          <form onSubmit={handleSubmit}>
            <div className="flex flex-col items-center m-5">
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
              <div className="flex flex-col items-center">
                <label htmlFor="confirmPassword">Confirm password</label>
                <input
                  type="password"
                  className="m-2 border border-maroon-200 p-1 rounded"
                  id="confirmPassword"
                  name="confirmPassword"
                  value={credentials.confirmPassword}
                  onChange={handleChange}
                  required
                />
              </div>
              <div className="flex flex-col items-center">
                <button type="submit" className="border border-gray-500 bg-maroon-200 hover:bg-maroon-100 p-2 m-2 rounded text-white">
                  Sign up
                </button>
                <Link to="/" className="border border-gray-500 bg-maroon-200 hover:bg-maroon-100 p-2 m-2 rounded text-white">
                  Cancel
                </Link>
              </div>
            </div>
          </form>
        )}
      </div>
    </div>
  );
}

export default SignUpForm;
