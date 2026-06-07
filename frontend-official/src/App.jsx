import { useState } from "react";
import "./App.css";

import Dashboard from "./components/layout/Dashboard";
import LoginForm from "./components/auth/LoginForm";
import RegisterUserAuthForm from "./components/auth/RegisterUserAuthForm";
import RegisterTrainerAuthForm from "./components/auth/RegisterTrainerAuthForm";
import { clearAuth, getStoredAuth } from "./services/api";

function App() {
  const [auth, setAuth] = useState(getStoredAuth());
  const [authView, setAuthView] = useState("login");

  function handleLogin(authData) {
    setAuth(authData);
  }

  function handleLogout() {
    clearAuth();
    setAuth(null);
    setAuthView("login");
  }

  if (!auth) {
    return (
      <main className="dashboard">
        <header className="dashboard-header">
          <h1>El arte de la hipertrofia muscular</h1>
          <p>Natural hypertrophy tracking platform</p>
        </header>

        <div className="tabs">
          <button
            className={`tab-button ${authView === "login" ? "active" : ""}`}
            onClick={() => setAuthView("login")}
          >
            Login
          </button>

          <button
            className={`tab-button ${authView === "registerUser" ? "active" : ""}`}
            onClick={() => setAuthView("registerUser")}
          >
            Register User
          </button>

          <button
            className={`tab-button ${authView === "registerTrainer" ? "active" : ""}`}
            onClick={() => setAuthView("registerTrainer")}
          >
            Register Trainer
          </button>
        </div>

        <section className="dashboard-content">
          {authView === "login" && <LoginForm onLogin={handleLogin} />}
          {authView === "registerUser" && (
            <RegisterUserAuthForm onLogin={handleLogin} />
          )}
          {authView === "registerTrainer" && (
            <RegisterTrainerAuthForm onLogin={handleLogin} />
          )}
        </section>
      </main>
    );
  }

  return <Dashboard auth={auth} onLogout={handleLogout} />;
}

export default App;