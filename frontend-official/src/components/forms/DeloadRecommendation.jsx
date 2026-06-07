import { useState } from "react";
import { apiFetch } from "../../services/api";

function DeloadRecommendation() {
  const [recommendations, setRecommendations] = useState([]);
  const [message, setMessage] = useState("");

  async function loadRecommendations() {
    try {
      const data = await apiFetch("/api/recommendations/me");

      setRecommendations(data);
      setMessage("");
    } catch (error) {
      setRecommendations([]);
      setMessage(
        "Could not load recommendations. Check your session."
      );
    }
  }

  return (
    <section className="form-card">
      <h2>My recommendations</h2>

      <p className="form-helper">
        Recommendations received from your trainer.
      </p>

      <button
        type="button"
        onClick={loadRecommendations}
      >
        Load recommendations
      </button>

      {message && (
        <p className="message">
          {message}
        </p>
      )}

      {recommendations.length > 0 && (
        <div className="summary-list">
          {recommendations.map((recommendation, index) => (
            <div
              key={index}
              className="created-card"
            >
              <p>
                <span>Type</span>
                <strong>{recommendation.type}</strong>
              </p>

              <p>
                <span>Message</span>
                <strong>{recommendation.message}</strong>
              </p>
            </div>
          ))}
        </div>
      )}
    </section>
  );
}

export default DeloadRecommendation;