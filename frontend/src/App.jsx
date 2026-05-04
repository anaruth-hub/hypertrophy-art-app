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

  const [assignmentForm, setAssignmentForm] = useState({
    userId: "",
    trainerId: "",
  });

  const [trainingForm, setTrainingForm] = useState({
    userId: "",
    date: "",
    muscleGroup: "",
    exercises: "",
    intensity: "MEDIUM",
    durationMinutes: 60,
  });

  const [userMessage, setUserMessage] = useState("");
  const [trainerMessage, setTrainerMessage] = useState("");
  const [assignmentMessage, setAssignmentMessage] = useState("");
  const [trainingMessage, setTrainingMessage] = useState("");

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

  function handleTrainingChange(event) {
    const { name, value } = event.target;
    setTrainingForm({ ...trainingForm, [name]: value });
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

      if (!response.ok) throw new Error("Could not create user");

      const createdUser = await response.json();
      setUserMessage(`User created: ${createdUser.name} (${createdUser.mode})`);
      setUserForm({ name: "", email: "", mode: "SELF_MANAGED" });
    } catch (error) {
      setUserMessage("Error creating user.");
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

      if (!response.ok) throw new Error("Could not create trainer");

      const createdTrainer = await response.json();
      setTrainerMessage(`Trainer created: ${createdTrainer.name}`);
      setTrainerForm({ name: "", email: "" });
    } catch (error) {
      setTrainerMessage("Error creating trainer.");
    }
  }

  async function handleAssignmentSubmit(event) {
    event.preventDefault();
    setAssignmentMessage("Assigning trainer...");

    try {
      const response = await fetch(
        `http://localhost:8080/api/users/${assignmentForm.userId}/assign-trainer/${assignmentForm.trainerId}`,
        { method: "POST" }
      );

      if (!response.ok) throw new Error("Could not assign trainer");

      const assignment = await response.json();
      setAssignmentMessage(`Trainer assigned to user: ${assignment.userName}`);
      setAssignmentForm({ userId: "", trainerId: "" });
    } catch (error) {
      setAssignmentMessage("Error assigning trainer. Check IDs or user mode.");
    }
  }

  async function handleTrainingSubmit(event) {
    event.preventDefault();
    setTrainingMessage("Registering training...");

    try {
      const response = await fetch("http://localhost:8080/api/trainings", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          ...trainingForm,
          durationMinutes: Number(trainingForm.durationMinutes),
        }),
      });

      if (!response.ok) throw new Error("Could not register training");

      const training = await response.json();
      setTrainingMessage(
        `Training registered: ${training.muscleGroup} (${training.intensity})`
      );

      setTrainingForm({
        userId: "",
        date: "",
        muscleGroup: "",
        exercises: "",
        intensity: "MEDIUM",
        durationMinutes: 60,
      });
    } catch (error) {
      setTrainingMessage("Error registering training. Check user ID.");
    }
  }

  return (
    <main className="page">
      <section className="card">
        <h1>El arte de la hipertrofia muscular</h1>
        <p>Natural hypertrophy tracking MVP</p>

        <div className="forms">
          <form onSubmit={handleUserSubmit}>
            <h2>Create user</h2>

            <label>
              Name
              <input name="name" value={userForm.name} onChange={handleUserChange} required />
            </label>

            <label>
              Email
              <input name="email" type="email" value={userForm.email} onChange={handleUserChange} required />
            </label>

            <label>
              Mode
              <select name="mode" value={userForm.mode} onChange={handleUserChange}>
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
              <input name="name" value={trainerForm.name} onChange={handleTrainerChange} required />
            </label>

            <label>
              Email
              <input name="email" type="email" value={trainerForm.email} onChange={handleTrainerChange} required />
            </label>

            <button type="submit">Create trainer</button>
            {trainerMessage && <p className="message">{trainerMessage}</p>}
          </form>

          <form onSubmit={handleAssignmentSubmit}>
            <h2>Assign trainer</h2>

            <label>
              User ID
              <input name="userId" value={assignmentForm.userId} onChange={handleAssignmentChange} required />
            </label>

            <label>
              Trainer ID
              <input name="trainerId" value={assignmentForm.trainerId} onChange={handleAssignmentChange} required />
            </label>

            <button type="submit">Assign trainer</button>
            {assignmentMessage && <p className="message">{assignmentMessage}</p>}
          </form>

          <form onSubmit={handleTrainingSubmit}>
            <h2>Register training</h2>

            <label>
              User ID
              <input name="userId" value={trainingForm.userId} onChange={handleTrainingChange} required />
            </label>

            <label>
              Date
              <input name="date" type="date" value={trainingForm.date} onChange={handleTrainingChange} required />
            </label>

            <label>
              Muscle group
              <input name="muscleGroup" value={trainingForm.muscleGroup} onChange={handleTrainingChange} required />
            </label>

            <label>
              Exercises
              <input name="exercises" value={trainingForm.exercises} onChange={handleTrainingChange} required />
            </label>

            <label>
              Intensity
              <select name="intensity" value={trainingForm.intensity} onChange={handleTrainingChange}>
                <option value="LOW">Low</option>
                <option value="MEDIUM">Medium</option>
                <option value="HIGH">High</option>
              </select>
            </label>

            <label>
              Duration minutes
              <input
                name="durationMinutes"
                type="number"
                min="1"
                value={trainingForm.durationMinutes}
                onChange={handleTrainingChange}
                required
              />
            </label>

            <button type="submit">Register training</button>
            {trainingMessage && <p className="message">{trainingMessage}</p>}
          </form>
        </div>
      </section>
    </main>
  );
}

export default App;