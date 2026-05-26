import { useState } from "react";
import { API_BASE_URL } from "../../services/api";

function TrainerProgressSummary() {
  const [form, setForm] = useState({
    trainerId: "",
    userId: "",
  });

  const [summary, setSummary] = useState(null);
  const [message, setMessage] = useState("");

  function handleChange(event) {
    const { name, value } = event.target;
    setForm({ ...form, [name]: value });
  }

  async function handleSubmit(event) {
    event.preventDefault();

    try {
      const response = await fetch(
        `${API_BASE_URL}/api/progress-summary/trainers/${form.trainerId}/users/${form.userId}`
      );

      if (!response.ok) {
        throw new Error("Could not load assigned user progress");
      }

      const data = await response.json();

      setSummary(data);
      setMessage("");
    } catch (error) {
      setSummary(null);
      setMessage("Error loading assigned user progress");
    }
  }

  return (
    <section className="form-card">
      <h2>Trainer progress view</h2>

      <form onSubmit={handleSubmit} className="summary-search">
        <input
          name="trainerId"
          placeholder="Trainer ID"
          value={form.trainerId}
          onChange={handleChange}
          required
        />

        <input
          name="userId"
          placeholder="Assigned User ID"
          value={form.userId}
          onChange={handleChange}
          required
        />

        <button type="submit">View assigned user progress</button>
      </form>

      {message && <p className="message">{message}</p>}

      {summary && (
        <div className="summary-list">
          <p><span>User ID</span><strong>{summary.userId}</strong></p>
          <p><span>Total trainings</span><strong>{summary.totalTrainings}</strong></p>
          <p><span>Latest training</span><strong>{summary.latestTrainingMuscleGroup ?? "No data"} — {summary.latestTrainingDate ?? ""}</strong></p>
          <p><span>Recovery fatigue</span><strong>{summary.latestRecoveryFatigue ?? "No data"}</strong></p>
          <p><span>Sleep hours</span><strong>{summary.latestRecoverySleepHours}</strong></p>
          <p><span>Calories</span><strong>{summary.latestNutritionCalories ?? "No data"}</strong></p>
          <p><span>Protein</span><strong>{summary.latestNutritionProteinGrams ?? "No data"}</strong></p>
          <p><span>Stress</span><strong>{summary.latestWellnessStress ?? "No data"}</strong></p>
          <p><span>Motivation</span><strong>{summary.latestWellnessMotivation ?? "No data"}</strong></p>
        </div>
      )}
    </section>
  );
}

export default TrainerProgressSummary;