import { useState } from "react";
import { API_BASE_URL } from "../../services/api";

function ProgressSummary() {
  const [userId, setUserId] = useState("");
  const [summary, setSummary] = useState(null);
  const [message, setMessage] = useState("");

  async function handleSubmit(event) {
    event.preventDefault();

    try {
      const response = await fetch(
        `${API_BASE_URL}/api/progress-summary/${userId}`
      );

      if (!response.ok) {
        throw new Error("Could not load progress summary");
      }

      const data = await response.json();

      setSummary(data);
      setMessage("");
    } catch (error) {
      setSummary(null);
      setMessage("Could not load progress summary. Check the user ID.");
    }
  }

  return (
    <section className="form-card">
      <h2>Progress summary</h2>

      <p className="form-helper">
        Paste a User ID to view training, recovery, nutrition and wellness progress.
      </p>

      <form onSubmit={handleSubmit} className="summary-search">
        <input
          name="userId"
          placeholder="Paste here the generated User ID"
          value={userId}
          onChange={(event) => setUserId(event.target.value)}
          required
        />

        <button type="submit">View summary</button>
      </form>

      {message && <p className="message">{message}</p>}

      {summary && (
        <div className="summary-list">
          <p><span>Total trainings</span><strong>{summary.totalTrainings}</strong></p>
          <p><span>Latest training</span><strong>{summary.latestTrainingMuscleGroup} — {summary.latestTrainingDate}</strong></p>
          <p><span>Recovery fatigue</span><strong>{summary.latestRecoveryFatigue}</strong></p>
          <p><span>Sleep hours</span><strong>{summary.latestRecoverySleepHours}</strong></p>
          <p><span>Calories</span><strong>{summary.latestNutritionCalories}</strong></p>
          <p><span>Protein</span><strong>{summary.latestNutritionProteinGrams}</strong></p>
          <p><span>Stress</span><strong>{summary.latestWellnessStress}</strong></p>
          <p><span>Motivation</span><strong>{summary.latestWellnessMotivation}</strong></p>
        </div>
      )}
    </section>
  );
}

export default ProgressSummary;