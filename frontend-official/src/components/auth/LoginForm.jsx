import { useState } from "react";
import { apiFetch, saveAuth } from "../../services/api";

function LoginForm({ onLogin }) {
  const [form, setForm] = useState({
    email: "",
    password: "",
  });

  const [message, setMessage] = useState("");

  function handleChange(event) {
    const { name, value } = event.target;
    setForm({ ...form, [name]: value });
  }

  async function handleSubmit(event) {
    event.preventDefault();

    try {
      const authData = await apiFetch("/api/auth/login", {
        method: "POST",
        body: JSON.stringify(form),
      });

      saveAuth(authData);
      onLogin(authData);
    } catch (error) {
      setMessage("Login failed. Check email and password.");
    }
  }

  return (
    <form className="form-card" onSubmit={handleSubmit}>
      <h2>Login</h2>

      <input
        name="email"
        type="email"
        placeholder="Email"
        value={form.email}
        onChange={handleChange}
        required
      />

      <input
        name="password"
        type="password"
        placeholder="Password"
        value={form.password}
        onChange={handleChange}
        required
      />

      <button type="submit">Login</button>

      {message && <p className="message">{message}</p>}
    </form>
  );
}

export default LoginForm;