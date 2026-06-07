import { useState } from "react";
import { apiFetch, getStoredAuth } from "../../services/api";

function TrainingForm() {
  const storedAuth = getStoredAuth();

  const [trainingForm, setTrainingForm] = useState({
    userId: storedAuth?.id || "",
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
      const training = await apiFetch("/api/trainings", {
        method: "POST",
        body: JSON.stringify({
          ...trainingForm,
          durationMinutes: Number(trainingForm.durationMinutes),
        }),
      });

      setTrainingMessage(
        `Training registered successfully: ${training.muscleGroup}`
      );

      setTrainingForm({
        userId: storedAuth?.id || "",
        date: "",
        muscleGroup: "",
        exercises: "",
        intensity: "MEDIUM",
        durationMinutes: 60,
      });
    } catch (error) {
      setTrainingMessage(
        "Could not register training. Check required fields and active session."
      );
    }
  }

  return (
    <form className="form-card" onSubmit={handleTrainingSubmit}>
      <h2>Register training</h2>

      <p className="form-helper">
        This training will be linked to the logged-in user.
      </p>

      <input
        name="userId"
        value={trainingForm.userId}
        onChange={handleTrainingChange}
        readOnly
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