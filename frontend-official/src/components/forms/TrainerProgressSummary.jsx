import { useEffect, useState } from "react";
import { apiFetch } from "../../services/api";

function TrainerProgressSummary() {
  const [users, setUsers] = useState([]);
  const [selectedUserId, setSelectedUserId] = useState("");
  const [summary, setSummary] = useState(null);

  const [recommendationForm, setRecommendationForm] = useState({
    date: "",
    message: "",
  });

  const [message, setMessage] = useState("");

  useEffect(() => {
    async function loadSupervisedUsers() {
      try {
        const data = await apiFetch("/api/trainers/me/users");
        setUsers(data);
        setMessage("");
      } catch (error) {
        setUsers([]);
        setMessage("Could not load supervised users.");
      }
    }

    loadSupervisedUsers();
  }, []);

  function handleUserChange(event) {
    setSelectedUserId(event.target.value);
    setSummary(null);
    setMessage("");
  }

  function handleRecommendationChange(event) {
    const { name, value } = event.target;
    setRecommendationForm({
      ...recommendationForm,
      [name]: value,
    });
  }

  async function loadProgress() {
    if (!selectedUserId) {
      setMessage("Select a supervised user first.");
      return;
    }

    try {
      const data = await apiFetch(
        `/api/progress-summary/trainers/me/users/${selectedUserId}/progress`
      );

      setSummary(data);
      setMessage("");
    } catch (error) {
      setSummary(null);
      setMessage("Could not load assigned user progress.");
    }
  }

  async function createRecommendation(event) {
    event.preventDefault();

    if (!selectedUserId) {
      setMessage("Select a supervised user first.");
      return;
    }

    try {
      await apiFetch(`/api/recommendations/trainers/me/users/${selectedUserId}`, {
        method: "POST",
        body: JSON.stringify(recommendationForm),
      });

      setRecommendationForm({
        date: "",
        message: "",
      });

      setMessage("Recommendation created successfully.");
    } catch (error) {
      setMessage("Could not create recommendation.");
    }
  }

  return (
    <section className="form-card">
      <h2>Trainer dashboard</h2>

      <p className="form-helper">
        Select one supervised user to view progress and create recommendations.
      </p>

      <label className="field-group">
        <span>Supervised user</span>
        <select value={selectedUserId} onChange={handleUserChange}>
          <option value="">Select supervised user</option>

          {users.map((user) => (
            <option key={user.id} value={user.id}>
              {user.name} - {user.email}
            </option>
          ))}
        </select>
      </label>

      <button type="button" onClick={loadProgress}>
        View assigned user progress
      </button>

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
            <strong>{summary.latestWellnessStress ?? "No data"}</strong>
          </p>

          <p>
            <span>Motivation</span>
            <strong>{summary.latestWellnessMotivation ?? "No data"}</strong>
          </p>
        </div>
      )}

      <form className="summary-search" onSubmit={createRecommendation}>
        <h3>Create recommendation</h3>

        <input
          type="date"
          name="date"
          value={recommendationForm.date}
          onChange={handleRecommendationChange}
          required
        />

        <textarea
          name="message"
          placeholder="Write recommendation for this user"
          value={recommendationForm.message}
          onChange={handleRecommendationChange}
          required
        />

        <button type="submit">Create recommendation</button>
      </form>
    </section>
  );
}

export default TrainerProgressSummary;