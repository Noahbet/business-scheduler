import { useEffect, useState, useCallback } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";

import Home from "./components/home_components/Home";
import Profile from "./components/profile_components/Profile";
import BusinessPage from "./components/business_page_components/BusinessPage";
// import ServiceForm from "./components/ServiceForm";
import Error from "./components/Error";
import NavBar from "./components/NavBar";
import LoginForm from "./components/LoginForm";
import SignUpForm from "./components/SignUpForm";
import AppointmentForm from "./components/AppointmentForm";
import AuthContext, { Auth } from "./contexts/AuthContext";

import { refreshToken, logout } from "./services/authAPI";
import ConfirmDeleteAppointment from "./components/profile_components/ConfirmDeleteAppointment";
import BusinessForm from "./components/profile_components/BusinessForm";

const TIMEOUT_MILLISECONDS = 14 * 60 * 1000;

function App() {
  const [user, setUser] = useState<any | null>(null);
  const [initialized, setInitialized] = useState(false);

  const resetUser = useCallback(() => {
    refreshToken()
      .then((user: any) => {
        setUser(user);
        setTimeout(resetUser, TIMEOUT_MILLISECONDS);
      })
      .catch((err) => {
        console.log(err);
      })
      .finally(() => setInitialized(true));
  }, []);

  useEffect(() => {
    resetUser();
  }, [resetUser]);

  const auth: Auth = {
    user: user,
    handleLoggedIn(user: any) {
      setUser(user);
      setTimeout(resetUser, TIMEOUT_MILLISECONDS);
    },
    hasAuthority(authority: any) {
      return user?.authorities.includes(authority);
    },
    logout() {
      logout();
      setUser(null);
    },
  };

  if (!initialized) {
    return null;
  }

  const renderWithAuthority = (Component: any, ...authorities: any[]) => {
    for (let authority of authorities) {
      if (auth.hasAuthority(authority)) {
        return <Component />;
      }
    }
    return <Error />;
  };

  return (
    <div className="container">
      <AuthContext.Provider value={auth}>
        <Router>
          <NavBar></NavBar>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<LoginForm />} />
            <Route path="/signup" element={<SignUpForm />} />
            <Route path="/profile" element={renderWithAuthority(Profile, "USER")}/>
            <Route path="/business/:businessId" element={<BusinessPage></BusinessPage>}/>
            <Route path="/business/update/:businessId" element={<BusinessForm></BusinessForm>}/>
            <Route path="/business/add" element={renderWithAuthority(BusinessForm, "USER")}/>
            <Route
              path="/appointment/delete/:appointmentId"
              element={renderWithAuthority(ConfirmDeleteAppointment, "USER")}
            />
            <Route
              path="/appointment/add/:serviceId"
              element={renderWithAuthority(AppointmentForm, "USER")}
            />
            {/* <Route
              path="/service/add"
              element={renderWithAuthority(ServiceForm, "OWNER", "ADMIN")}
            /> */}
            <Route path="/error" element={<Error />} />
            <Route path="*" element={<Error />} />
          </Routes>
        </Router>
      </AuthContext.Provider>
    </div>
  );
}

export default App;
