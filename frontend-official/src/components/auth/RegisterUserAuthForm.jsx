import { useState } from "react";
import { apiFetch, saveAuth } from "../../services/api";

function RegisterUserAuthForm({ onLogin }) {
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
    mode: "SUPERVISED",
  });

  const [message, setMessage] = useState("");

  function handleChange(event) {
    const { name, value } = event.target;
    setForm({ ...form, [name]: value });
  }

  async function handleSubmit(event) {
    event.preventDefault();

    try {
      const authData = await apiFetch("/api/auth/register-user", {
        method: "POST",
        body: JSON.stringify(form),
      });

      saveAuth(authData);
      onLogin(authData);
    } catch (error) {
      setMessage("Could not register user. Check required fields or email.");
    }
  }

  return (
    <form className="form-card" onSubmit={handleSubmit}>
      <h2>Register User</h2>

      <input
        name="name"
        placeholder="Name"
        value={form.name}
        onChange={handleChange}
        required
      />

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

      <select name="mode" value={form.mode} onChange={handleChange}>
        <option value="SUPERVISED">SUPERVISED</option>
        <option value="SELF_MANAGED">SELF_MANAGED</option>
      </select>

      <button type="submit">Register User</button>

      {message && <p className="message">{message}</p>}
    </form>
  );
}

export default RegisterUserAuthForm;