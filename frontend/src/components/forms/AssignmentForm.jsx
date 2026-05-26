import { useState } from "react";
import { API_BASE_URL } from "../../services/api";

function AssignmentForm() {

  const [assignmentForm, setAssignmentForm] = useState({
    userId: "",
    trainerId: "",
  });

  const [assignmentMessage, setAssignmentMessage] = useState("");

  function handleAssignmentChange(event) {

    const { name, value } = event.target;

    setAssignmentForm({
      ...assignmentForm,
      [name]: value,
    });
  }

  async function handleAssignmentSubmit(event) {

    event.preventDefault();

    try {

      const response = await fetch(
        `${API_BASE_URL}/api/users/${assignmentForm.userId}/assign-trainer/${assignmentForm.trainerId}`,
        {
          method: "POST",
        }
      );

      if (!response.ok) {
        throw new Error("Could not assign trainer");
      }

      const result = await response.json();

      setAssignmentMessage(
        `Trainer assigned successfully to ${result.userName}`
      );

      setAssignmentForm({
        userId: "",
        trainerId: "",
      });

    } catch (error) {

      setAssignmentMessage(
        "Could not assign trainer. Check user mode and IDs."
      );
    }
  }

  return (

    <form
      className="form-card"
      onSubmit={handleAssignmentSubmit}
    >

      <h2>Assign trainer</h2>

      <p className="form-helper">
        Use the IDs generated in the Users and Trainers tabs.
      </p>

      <input
        name="userId"
        placeholder="Paste here the generated User ID"
        value={assignmentForm.userId}
        onChange={handleAssignmentChange}
        required
      />

      <input
        name="trainerId"
        placeholder="Paste here the generated Trainer ID"
        value={assignmentForm.trainerId}
        onChange={handleAssignmentChange}
        required
      />

      <button type="submit">
        Assign trainer
      </button>

      {assignmentMessage && (
        <p className="message">
          {assignmentMessage}
        </p>
      )}

    </form>
  );
}

export default AssignmentForm;