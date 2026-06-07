import { useState } from "react";
import { apiFetch } from "../../services/api";

function ProgressSummary() {
  const [summary, setSummary] = useState(null);
  const [message, setMessage] = useState("");

  async function handleLoadSummary() {
    try {
      const data = await apiFetch("/api/progress-summary/me");

      setSummary(data);
      setMessage("");
    } catch (error) {
      setSummary(null);
      setMessage("Could not load your progress summary. Check your session.");
    }
  }

  return (
    <section className="form-card">
      <h2>My progress summary</h2>

      <p className="form-helper">
        This summary is loaded using the logged-in user session.
      </p>

      <button type="button" onClick={handleLoadSummary}>
        View my summary
      </button>

      {message && <p className="message">{message}</p>}

      {summary && (
        <div className="summary-list">
          <p>
            <span>Total trainings</span>
            <strong>{summary.totalTrainings}</strong>
          </p>

          <p>
            <span>Latest training</span>
            <strong>
              {summary.latestTrainingMuscleGroup || "No data"} —{" "}
              {summary.latestTrainingDate || "No date"}
            </strong>
          </p>

          <p>
            <span>Recovery fatigue</span>
            <strong>{summary.latestRecoveryFatigue || "No data"}</strong>
          </p>

          <p>
            <span>Sleep hours</span>
            <strong>{summary.latestRecoverySleepHours ?? "No data"}</strong>
          </p>

          <p>
            <span>Calories</span>
            <strong>{summary.latestNutritionCalories ?? "No data"}</strong>
          </p>

          <p>
            <span>Protein</span>
            <strong>{summary.latestNutritionProteinGrams ?? "No data"}</strong>
          </p>

          <p>
            <span>Stress</span>
            <strong>{summary.latestWellnessStress || "No data"}</strong>
          </p>

          <p>
            <span>Motivation</span>
            <strong>{summary.latestWellnessMotivation || "No data"}</strong>
          </p>
        </div>
      )}
    </section>
  );
}

export default ProgressSummary;