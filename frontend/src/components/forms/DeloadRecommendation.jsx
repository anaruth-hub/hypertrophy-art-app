import { useState } from "react";
import { API_BASE_URL } from "../../services/api";

function DeloadRecommendation() {
  const [userId, setUserId] = useState("");
  const [recommendation, setRecommendation] = useState(null);
  const [message, setMessage] = useState("");

  async function handleSubmit(event) {
    event.preventDefault();

    try {
      const response = await fetch(
        `${API_BASE_URL}/api/recommendations/deload/${userId}`
      );

      if (!response.ok) {
        throw new Error("Could not load recommendation");
      }

      const data = await response.json();

      setRecommendation(data);
      setMessage("");
    } catch (error) {
      setRecommendation(null);
      setMessage("Could not load recommendation. Check the user ID.");
    }
  }

  return (
    <section className="form-card">
      <h2>Deload recommendation</h2>

      <form onSubmit={handleSubmit} className="summary-search">
        <input
          name="userId"
          placeholder="User ID"
          value={userId}
          onChange={(event) => setUserId(event.target.value)}
          required
        />

        <button type="submit">View recommendation</button>
      </form>

      {message && <p className="message">{message}</p>}

      {recommendation && (
        <div className="summary-list">
          <p>
            <span>User ID</span>
            <strong>{recommendation.userId}</strong>
          </p>

          <p>
            <span>Recommendation</span>
            <strong>{recommendation.recommendation}</strong>
          </p>

          <p>
            <span>Reason</span>
            <strong>{recommendation.reason}</strong>
          </p>
        </div>
      )}
    </section>
  );
}

export default DeloadRecommendation;