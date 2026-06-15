import { useState } from "react";
import { apiFetch } from "../../services/api";

function WellnessForm() {
  const [wellnessForm, setWellnessForm] = useState({
    date: "",
    physicalState: "MEDIUM",
    mentalState: "MEDIUM",
    emotionalState: "MEDIUM",
    stressLevel: "MEDIUM",
    motivationLevel: "MEDIUM",
    notes: "",
  });

  const [wellnessMessage, setWellnessMessage] = useState("");

  function handleWellnessChange(event) {
    const { name, value } = event.target;
    setWellnessForm({ ...wellnessForm, [name]: value });
  }

  async function handleWellnessSubmit(event) {
    event.preventDefault();

    try {
      const wellness = await apiFetch("/api/wellness-checkins", {
        method: "POST",
        body: JSON.stringify(wellnessForm),
      });

      setWellnessMessage(
        `Wellness registered successfully: ${wellness.emotionalState}`
      );

      setWellnessForm({
        date: "",
        physicalState: "MEDIUM",
        mentalState: "MEDIUM",
        emotionalState: "MEDIUM",
        stressLevel: "MEDIUM",
        motivationLevel: "MEDIUM",
        notes: "",
      });
    } catch (error) {
      setWellnessMessage(
        "Could not register wellness. Check required fields and active session."
      );
    }
  }

  return (
    <form className="form-card" onSubmit={handleWellnessSubmit}>
      <h2>Wellness check-in</h2>

      <p className="form-helper">
        Wellness data will be linked to the logged-in user.
      </p>

      <input
        type="date"
        name="date"
        value={wellnessForm.date}
        onChange={handleWellnessChange}
        required
      />

      <select
        name="physicalState"
        value={wellnessForm.physicalState}
        onChange={handleWellnessChange}
      >
        <option value="LOW">Physical LOW</option>
        <option value="MEDIUM">Physical MEDIUM</option>
        <option value="HIGH">Physical HIGH</option>
      </select>

      <select
        name="mentalState"
        value={wellnessForm.mentalState}
        onChange={handleWellnessChange}
      >
        <option value="LOW">Mental LOW</option>
        <option value="MEDIUM">Mental MEDIUM</option>
        <option value="HIGH">Mental HIGH</option>
      </select>

      <select
        name="emotionalState"
        value={wellnessForm.emotionalState}
        onChange={handleWellnessChange}
      >
        <option value="LOW">Emotional LOW</option>
        <option value="MEDIUM">Emotional MEDIUM</option>
        <option value="HIGH">Emotional HIGH</option>
      </select>

      <select
        name="stressLevel"
        value={wellnessForm.stressLevel}
        onChange={handleWellnessChange}
      >
        <option value="LOW">Stress LOW</option>
        <option value="MEDIUM">Stress MEDIUM</option>
        <option value="HIGH">Stress HIGH</option>
      </select>

      <select
        name="motivationLevel"
        value={wellnessForm.motivationLevel}
        onChange={handleWellnessChange}
      >
        <option value="LOW">Motivation LOW</option>
        <option value="MEDIUM">Motivation MEDIUM</option>
        <option value="HIGH">Motivation HIGH</option>
      </select>

      <textarea
        name="notes"
        placeholder="Wellness notes"
        value={wellnessForm.notes}
        onChange={handleWellnessChange}
      />

      <button type="submit">Register wellness</button>

      {wellnessMessage && <p className="message">{wellnessMessage}</p>}
    </form>
  );
}

export default WellnessForm;
