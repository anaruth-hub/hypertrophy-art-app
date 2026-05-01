import { useState } from "react";
import "./App.css";

function App() {
  const [userForm, setUserForm] = useState({
    name: "",
    email: "",
    mode: "SELF_MANAGED",
  });

  const [trainerForm, setTrainerForm] = useState({
    name: "",
    email: "",
  });

  const [userMessage, setUserMessage] = useState("");
  const [trainerMessage, setTrainerMessage] = useState("");
  const [assignmentForm, setAssignmentForm] = useState({
    userId: "",
    trainerId: "",
  });

  const [assignmentMessage, setAssignmentMessage] = useState("");

  function handleUserChange(event) {
    const { name, value } = event.target;
    setUserForm({ ...userForm, [name]: value });
  }

  function handleTrainerChange(event) {
    const { name, value } = event.target;
    setTrainerForm({ ...trainerForm, [name]: value });
  }

  function handleAssignmentChange(event) {
    const { name, value } = event.target;
    setAssignmentForm({ ...assignmentForm, [name]: value });
  }
  async function handleUserSubmit(event) {
    event.preventDefault();
    setUserMessage("Creating user...");

    try {
      const response = await fetch("http://localhost:8080/api/users", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(userForm),
      });

      if (!response.ok) {
        throw new Error("Could not create user");
      }

      const createdUser = await response.json();
      setUserMessage(`User created: ${createdUser.name} (${createdUser.mode})`);
      setUserForm({ name: "", email: "", mode: "SELF_MANAGED" });
    } catch (error) {
      setUserMessage("Error creating user. Check backend or CORS.");
    }
  }

  async function handleTrainerSubmit(event) {
    event.preventDefault();
    setTrainerMessage("Creating trainer...");

    try {
      const response = await fetch("http://localhost:8080/api/trainers", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(trainerForm),
      });

      if (!response.ok) {
        throw new Error("Could not create trainer");
      }

      const createdTrainer = await response.json();
      setTrainerMessage(`Trainer created: ${createdTrainer.name}`);
      setTrainerForm({ name: "", email: "" });
    } catch (error) {
      setTrainerMessage("Error creating trainer. Check backend or CORS.");
    }
  }

    async function handleAssignmentSubmit(event) {
      event.preventDefault();
      setAssignmentMessage("Assigning trainer...");

      try {
        const response = await fetch(
          `http://localhost:8080/api/users/${assignmentForm.userId}/assign-trainer/${assignmentForm.trainerId}`,
          {
            method: "POST",
          }
        );

        if (!response.ok) {
          throw new Error("Could not assign trainer");
        }

        const assignment = await response.json();

        setAssignmentMessage(
          `Trainer assigned to user: ${assignment.userName}`
        );

        setAssignmentForm({
          userId: "",
          trainerId: "",
        });
      } catch (error) {
        setAssignmentMessage(
          "Error assigning trainer. Check IDs or user mode."
        );
      }
    }

  return (
    <main className="page">
      <section className="card">
        <h1>El arte de la hipertrofia muscular</h1>
        <p>Create profiles</p>

        <div className="forms">
          <form onSubmit={handleUserSubmit}>
            <h2>Create user</h2>

            <label>
              Name
              <input
                name="name"
                value={userForm.name}
                onChange={handleUserChange}
                placeholder="Ana"
                required
              />
            </label>

            <label>
              Email
              <input
                name="email"
                type="email"
                value={userForm.email}
                onChange={handleUserChange}
                placeholder="ana@test.com"
                required
              />
            </label>

            <label>
              Mode
              <select
                name="mode"
                value={userForm.mode}
                onChange={handleUserChange}
              >
                <option value="SELF_MANAGED">Self-managed</option>
                <option value="SUPERVISED">Supervised</option>
              </select>
            </label>

            <button type="submit">Create user</button>

            {userMessage && <p className="message">{userMessage}</p>}
          </form>

          <form onSubmit={handleTrainerSubmit}>
            <h2>Create trainer</h2>

            <label>
              Name
              <input
                name="name"
                value={trainerForm.name}
                onChange={handleTrainerChange}
                placeholder="Coach Laura"
                required
              />
            </label>

            <label>
              Email
              <input
                name="email"
                type="email"
                value={trainerForm.email}
                onChange={handleTrainerChange}
                placeholder="coach@test.com"
                required
              />
            </label>

            <button type="submit">Create trainer</button>

            {trainerMessage && <p className="message">{trainerMessage}</p>}
          </form>

          <form onSubmit={handleAssignmentSubmit}>
            <h2>Assign trainer</h2>

            <label>
              User ID
              <input
                name="userId"
                value={assignmentForm.userId}
                onChange={handleAssignmentChange}
                placeholder="Paste supervised user UUID"
                required
              />
            </label>

            <label>
              Trainer ID
              <input
                name="trainerId"
                value={assignmentForm.trainerId}
                onChange={handleAssignmentChange}
                placeholder="Paste trainer UUID"
                required
              />
            </label>

            <button type="submit">Assign trainer</button>

            {assignmentMessage && <p className="message">{assignmentMessage}</p>}
          </form>
        </div>
      </section>
    </main>
  );
}

export default App;