import { useState } from "react";
import { API_BASE_URL } from "../../services/api";

function UserForm() {
  const [userForm, setUserForm] = useState({
    name: "",
    email: "",
    mode: "SELF_MANAGED",
  });

  const [userMessage, setUserMessage] = useState("");

  function handleUserChange(event) {
    const { name, value } = event.target;
    setUserForm({ ...userForm, [name]: value });
  }

  async function handleUserSubmit(event) {
    event.preventDefault();

    try {
      const response = await fetch(`${API_BASE_URL}/api/users`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(userForm),
      });

      if (!response.ok) {
        throw new Error("Could not create user");
      }

      const createdUser = await response.json();

      setUserMessage(`User created: ${createdUser.name}`);

      setUserForm({
        name: "",
        email: "",
        mode: "SELF_MANAGED",
      });
    } catch (error) {
      setUserMessage("Error creating user");
    }
  }

  return (
    <form className="form-card" onSubmit={handleUserSubmit}>
      <h2>Create user</h2>

      <input
        name="name"
        placeholder="Name"
        value={userForm.name}
        onChange={handleUserChange}
        required
      />

      <input
        name="email"
        type="email"
        placeholder="Email"
        value={userForm.email}
        onChange={handleUserChange}
        required
      />

      <select name="mode" value={userForm.mode} onChange={handleUserChange}>
        <option value="SELF_MANAGED">SELF_MANAGED</option>
        <option value="SUPERVISED">SUPERVISED</option>
      </select>

      <button type="submit">Create user</button>

      {userMessage && <p className="message">{userMessage}</p>}
    </form>
  );
}

export default UserForm;