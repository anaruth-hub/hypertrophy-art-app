import { useState } from "react";
import { apiFetch, getStoredAuth } from "../../services/api";

function RecoveryForm() {
  const storedAuth = getStoredAuth();

  const [recoveryForm, setRecoveryForm] = useState({
    userId: storedAuth?.id || "",
    date: "",
    fatigueLevel: "MEDIUM",
    sorenessLevel: "MEDIUM",
    energyLevel: "MEDIUM",
    sleepHours: 8,
    notes: "",
  });

  const [recoveryMessage, setRecoveryMessage] = useState("");

  function handleRecoveryChange(event) {
    const { name, value } = event.target;
    setRecoveryForm({ ...recoveryForm, [name]: value });
  }

  async function handleRecoverySubmit(event) {
    event.preventDefault();

    try {
      const recovery = await apiFetch("/api/recovery-checkins", {
        method: "POST",
        body: JSON.stringify({
          ...recoveryForm,
          sleepHours: Number(recoveryForm.sleepHours),
        }),
      });

      setRecoveryMessage(
        `Recovery registered successfully: ${recovery.fatigueLevel}`
      );

      setRecoveryForm({
        userId: storedAuth?.id || "",
        date: "",
        fatigueLevel: "MEDIUM",
        sorenessLevel: "MEDIUM",
        energyLevel: "MEDIUM",
        sleepHours: 8,
        notes: "",
      });
    } catch (error) {
      setRecoveryMessage(
        "Could not register recovery. Check user ID and required fields."
      );
    }
  }

  return (
    <form className="form-card" onSubmit={handleRecoverySubmit}>
      <h2>Recovery check-in</h2>

      <p className="form-helper">
        Recovery data will be linked to the logged-in user.
      </p>

      <label className="field-group">
        <span>User ID</span>
        <input
          name="userId"
          value={recoveryForm.userId}
          onChange={handleRecoveryChange}
          readOnly
          required
        />
      </label>

      <label className="field-group">
        <span>Date</span>
        <input
          type="date"
          name="date"
          value={recoveryForm.date}
          onChange={handleRecoveryChange}
          required
        />
      </label>

      <label className="field-group">
        <span>Fatigue level</span>
        <small>How tired do you feel?</small>
        <select
          name="fatigueLevel"
          value={recoveryForm.fatigueLevel}
          onChange={handleRecoveryChange}
        >
          <option value="LOW">LOW</option>
          <option value="MEDIUM">MEDIUM</option>
          <option value="HIGH">HIGH</option>
        </select>
      </label>

      <label className="field-group">
        <span>Muscle soreness level</span>
        <small>How sore are your muscles?</small>
        <select
          name="sorenessLevel"
          value={recoveryForm.sorenessLevel}
          onChange={handleRecoveryChange}
        >
          <option value="LOW">LOW</option>
          <option value="MEDIUM">MEDIUM</option>
          <option value="HIGH">HIGH</option>
        </select>
      </label>

      <label className="field-group">
        <span>Energy level</span>
        <small>How much energy do you have?</small>
        <select
          name="energyLevel"
          value={recoveryForm.energyLevel}
          onChange={handleRecoveryChange}
        >
          <option value="LOW">LOW</option>
          <option value="MEDIUM">MEDIUM</option>
          <option value="HIGH">HIGH</option>
        </select>
      </label>

      <label className="field-group">
        <span>Sleep hours</span>
        <small>How many hours did you sleep?</small>
        <input
          type="number"
          step="0.5"
          name="sleepHours"
          placeholder="Sleep hours"
          value={recoveryForm.sleepHours}
          onChange={handleRecoveryChange}
          required
        />
      </label>

      <label className="field-group">
        <span>Recovery notes</span>
        <textarea
          name="notes"
          placeholder="Recovery notes"
          value={recoveryForm.notes}
          onChange={handleRecoveryChange}
        />
      </label>

      <button type="submit">Register recovery</button>

      {recoveryMessage && <p className="message">{recoveryMessage}</p>}
    </form>
  );
}

export default RecoveryForm;