import { useState } from "react";
import { apiFetch, saveAuth } from "../../services/api";

function RegisterTrainerAuthForm({ onLogin }) {
  const [form, setForm] = useState({
    name: "",
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
      const authData = await apiFetch("/api/auth/register-trainer", {
        method: "POST",
        body: JSON.stringify(form),
      });

      saveAuth(authData);
      onLogin(authData);
    } catch (error) {
      setMessage("Could not register trainer. Check required fields or email.");
    }
  }

  return (
    <form className="form-card" onSubmit={handleSubmit}>
      <h2>Register Trainer</h2>

      <input
        name="name"
        placeholder="Trainer name"
        value={form.name}
        onChange={handleChange}
        required
      />

      <input
        name="email"
        type="email"
        placeholder="Trainer email"
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

      <button type="submit">Register Trainer</button>

      {message && <p className="message">{message}</p>}
    </form>
  );
}

export default RegisterTrainerAuthForm;