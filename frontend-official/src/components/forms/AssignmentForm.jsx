import { useEffect, useState } from "react";
import { apiFetch } from "../../services/api";

function AssignmentForm() {
  const [trainers, setTrainers] = useState([]);
  const [assignmentForm, setAssignmentForm] = useState({
    trainerId: "",
  });

  const [assignmentMessage, setAssignmentMessage] = useState("");

  useEffect(() => {
    async function loadTrainers() {
      try {
        const data = await apiFetch("/api/trainers");
        setTrainers(data);
      } catch (error) {
        setAssignmentMessage("Could not load trainers.");
      }
    }

    loadTrainers();
  }, []);

  function handleAssignmentChange(event) {
    const { name, value } = event.target;
    setAssignmentForm({ ...assignmentForm, [name]: value });
  }

  async function handleAssignmentSubmit(event) {
    event.preventDefault();

    try {
      const result = await apiFetch(
        `/api/users/me/assign-trainer/${assignmentForm.trainerId}`,
        { method: "POST" }
      );

      setAssignmentMessage(`Trainer assigned successfully to ${result.userName}`);
    } catch (error) {
      setAssignmentMessage("Could not assign trainer. Check selected trainer.");
    }
  }

  return (
    <form className="form-card" onSubmit={handleAssignmentSubmit}>
      <h2>Assign trainer</h2>

      <p className="form-helper">Select a trainer from the list.</p>

      <label className="field-group">
        <span>Trainer</span>
        <select
          name="trainerId"
          value={assignmentForm.trainerId}
          onChange={handleAssignmentChange}
          required
        >
          <option value="">Select trainer</option>

          {trainers.map((trainer) => (
            <option key={trainer.id} value={trainer.id}>
              {trainer.name} - {trainer.email}
            </option>
          ))}
        </select>
      </label>

      <button type="submit">Assign trainer</button>

      {assignmentMessage && <p className="message">{assignmentMessage}</p>}
    </form>
  );
}

export default AssignmentForm;
