import { useState } from "react";
import { API_BASE_URL } from "../../services/api";

function TrainingForm() {
  const [trainingForm, setTrainingForm] = useState({
    userId: "",
    date: "",
    muscleGroup: "",
    exercises: "",
    intensity: "MEDIUM",
    durationMinutes: 60,
  });

  const [trainingMessage, setTrainingMessage] = useState("");

  function handleTrainingChange(event) {
    const { name, value } = event.target;
    setTrainingForm({ ...trainingForm, [name]: value });
  }

  async function handleTrainingSubmit(event) {
    event.preventDefault();

    try {
      const response = await fetch(`${API_BASE_URL}/api/trainings`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          ...trainingForm,
          durationMinutes: Number(trainingForm.durationMinutes),
        }),
      });

      if (!response.ok) {
        throw new Error("Could not register training");
      }

      const training = await response.json();

      setTrainingMessage(`Training registered: ${training.muscleGroup}`);
    } catch (error) {
      setTrainingMessage("Error registering training");
    }
  }

  return (
    <form className="form-card" onSubmit={handleTrainingSubmit}>
      <h2>Register training</h2>

      <input
        name="userId"
        placeholder="User ID"
        value={trainingForm.userId}
        onChange={handleTrainingChange}
        required
      />

      <input
        type="date"
        name="date"
        value={trainingForm.date}
        onChange={handleTrainingChange}
        required
      />

      <input
        name="muscleGroup"
        placeholder="Muscle group"
        value={trainingForm.muscleGroup}
        onChange={handleTrainingChange}
        required
      />

      <input
        name="exercises"
        placeholder="Exercises"
        value={trainingForm.exercises}
        onChange={handleTrainingChange}
        required
      />

      <select
        name="intensity"
        value={trainingForm.intensity}
        onChange={handleTrainingChange}
      >
        <option value="LOW">LOW</option>
        <option value="MEDIUM">MEDIUM</option>
        <option value="HIGH">HIGH</option>
      </select>

      <input
        type="number"
        name="durationMinutes"
        placeholder="Duration"
        value={trainingForm.durationMinutes}
        onChange={handleTrainingChange}
        required
      />

      <button type="submit">Register training</button>

      {trainingMessage && <p className="message">{trainingMessage}</p>}
    </form>
  );
}

export default TrainingForm;