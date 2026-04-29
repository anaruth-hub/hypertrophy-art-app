import { useState } from "react";
import "./App.css";

function App() {
  const [form, setForm] = useState({
    name: "",
    email: "",
    mode: "SELF_MANAGED",
  });

  const [message, setMessage] = useState("");

  function handleChange(event) {
    const { name, value } = event.target;

    setForm({
      ...form,
      [name]: value,
    });
  }

  async function handleSubmit(event) {
    event.preventDefault();
    setMessage("Creating user...");

    try {
      const response = await fetch("http://localhost:8080/api/users", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(form),
      });

      if (!response.ok) {
        throw new Error("Could not create user");
      }

      const createdUser = await response.json();

      setMessage(`User created: ${createdUser.name} (${createdUser.mode})`);
      setForm({
        name: "",
        email: "",
        mode: "SELF_MANAGED",
      });
    } catch (error) {
      setMessage("Error creating user. Check backend or CORS.");
    }
  }

  return (
    <main className="page">
      <section className="card">
        <h1>El arte de la hipertrofia muscular</h1>
        <p>Create your user profile</p>

        <form onSubmit={handleSubmit}>
          <label>
            Name
            <input
              name="name"
              value={form.name}
              onChange={handleChange}
              placeholder="Ana"
              required
            />
          </label>

          <label>
            Email
            <input
              name="email"
              type="email"
              value={form.email}
              onChange={handleChange}
              placeholder="ana@test.com"
              required
            />
          </label>

          <label>
            Mode
            <select name="mode" value={form.mode} onChange={handleChange}>
              <option value="SELF_MANAGED">Self-managed</option>
              <option value="SUPERVISED">Supervised</option>
            </select>
          </label>

          <button type="submit">Create user</button>
        </form>

        {message && <p className="message">{message}</p>}
      </section>
    </main>
  );
}

export default App;