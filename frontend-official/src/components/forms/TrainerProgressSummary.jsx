import { useState } from "react";
import { apiFetch } from "../../services/api";

function TrainerProgressSummary() {
  const [userId, setUserId] = useState("");

  const [summary, setSummary] = useState(null);
  const [message, setMessage] = useState("");

  async function handleSubmit(event) {
    event.preventDefault();

    try {
      const data = await apiFetch(
        `/api/progress-summary/trainers/me/users/${userId}/progress`
      );

      setSummary(data);
      setMessage("");
    } catch (error) {
      setSummary(null);
      setMessage("Could not load assigned user progress.");
    }
  }

  return (
    <section className="form-card">
      <h2>Trainer progress view</h2>

      <p className="form-helper">
        Enter an assigned User ID to view supervised progress.
      </p>

      <form onSubmit={handleSubmit} className="summary-search">
        <input
          placeholder="Assigned User ID"
          value={userId}
          onChange={(event) => setUserId(event.target.value)}
          required
        />

        <button type="submit">View assigned user progress</button>
      </form>

      {message && <p className="message">{message}</p>}

      {summary && (
        <div className="summary-list">
          <p>
            <span>User ID</span>
            <strong>{summary.userId}</strong>
          </p>
          <p>
            <span>Total trainings</span>
            <strong>{summary.totalTrainings}</strong>
          </p>
          <p>
            <span>Latest training</span>
            <strong>
              {summary.latestTrainingMuscleGroup ?? "No data"} —{" "}
              {summary.latestTrainingDate ?? ""}
            </strong>
          </p>
          <p>
            <span>Recovery fatigue</span>
            <strong>{summary.latestRecoveryFatigue ?? "No data"}</strong>
          </p>
          <p>
            <span>Sleep hours</span>
            <strong>{summary.latestRecoverySleepHours}</strong>
          </p>
          <p>
            <span>Calories</span>
            <strong>{summary.latestNutritionCalories ?? "No data"}</strong>
          </p>
          <p>
            <span>Protein</span>
            <strong>
              {summary.latestNutritionProteinGrams ?? "No data"}
            </strong>
          </p>
          <p>
            <span>Stress</span>
            <strong>{summary.latestWellnessStress ?? "No data"}</strong>
          </p>
          <p>
            <span>Motivation</span>
            <strong>{summary.latestWellnessMotivation ?? "No data"}</strong>
          </p>
        </div>
      )}
    </section>
  );
}

export default TrainerProgressSummary;