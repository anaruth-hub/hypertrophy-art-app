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

      setRecoveryMessage(`Recovery registered successfully: ${recovery.fatigueLevel}`);

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
      setRecoveryMessage("Could not register recovery. Check user ID and required fields.");
    }
  }

  return (
    <form className="form-card" onSubmit={handleRecoverySubmit}>
      <h2>Recovery check-in</h2>

      <input
        name="userId"
        placeholder="User ID"
        value={recoveryForm.userId}
        onChange={handleRecoveryChange}
        required
      />

      <input
        type="date"
        name="date"
        value={recoveryForm.date}
        onChange={handleRecoveryChange}
        required
      />

      <select
        name="fatigueLevel"
        value={recoveryForm.fatigueLevel}
        onChange={handleRecoveryChange}
      >
        <option value="LOW">LOW</option>
        <option value="MEDIUM">MEDIUM</option>
        <option value="HIGH">HIGH</option>
      </select>

      <select
        name="sorenessLevel"
        value={recoveryForm.sorenessLevel}
        onChange={handleRecoveryChange}
      >
        <option value="LOW">LOW</option>
        <option value="MEDIUM">MEDIUM</option>
        <option value="HIGH">HIGH</option>
      </select>

      <select
        name="energyLevel"
        value={recoveryForm.energyLevel}
        onChange={handleRecoveryChange}
      >
        <option value="LOW">LOW</option>
        <option value="MEDIUM">MEDIUM</option>
        <option value="HIGH">HIGH</option>
      </select>

      <input
        type="number"
        step="0.5"
        name="sleepHours"
        placeholder="Sleep hours"
        value={recoveryForm.sleepHours}
        onChange={handleRecoveryChange}
        required
      />

      <textarea
        name="notes"
        placeholder="Recovery notes"
        value={recoveryForm.notes}
        onChange={handleRecoveryChange}
      />

      <button type="submit">Register recovery</button>

      {recoveryMessage && <p className="message">{recoveryMessage}</p>}
    </form>
  );
}

export default RecoveryForm;