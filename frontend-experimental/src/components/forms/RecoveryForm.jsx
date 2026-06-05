import { useState } from "react";
import { API_BASE_URL } from "../../services/api";

function RecoveryForm() {
  const [recoveryForm, setRecoveryForm] = useState({
    userId: "",
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
      const response = await fetch(`${API_BASE_URL}/api/recovery-checkins`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          ...recoveryForm,
          sleepHours: Number(recoveryForm.sleepHours),
        }),
      });

      if (!response.ok) {
        throw new Error("Could not register recovery");
      }

      const recovery = await response.json();

      setRecoveryMessage(
        `Recovery registered successfully: ${recovery.fatigueLevel}`
      );

      setRecoveryForm({
        userId: "",
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
        Use a generated User ID to connect recovery data to a profile.
      </p>

      <label className="field-group">
        <span>User ID</span>
        <input
          name="userId"
          placeholder="Paste here the generated User ID"
          value={recoveryForm.userId}
          onChange={handleRecoveryChange}
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