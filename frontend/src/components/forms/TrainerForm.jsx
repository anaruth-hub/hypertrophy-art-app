import { useState } from "react";
import { API_BASE_URL } from "../../services/api";

function TrainerForm() {

  const [trainerForm, setTrainerForm] = useState({
    name: "",
    email: "",
  });

  const [trainerMessage, setTrainerMessage] = useState("");

  function handleTrainerChange(event) {

    const { name, value } = event.target;

    setTrainerForm({
      ...trainerForm,
      [name]: value,
    });
  }

  async function handleTrainerSubmit(event) {

    event.preventDefault();

    try {

      const response = await fetch(
        `${API_BASE_URL}/api/trainers`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(trainerForm),
        }
      );

      if (!response.ok) {
        throw new Error("Could not create trainer");
      }

      const createdTrainer = await response.json();

      setTrainerMessage(
        `Trainer created: ${createdTrainer.name}`
      );

      setTrainerForm({
        name: "",
        email: "",
      });

    } catch (error) {

      setTrainerMessage(
        "Error creating trainer"
      );
    }
  }

  return (

    <form
      className="form-card"
      onSubmit={handleTrainerSubmit}
    >

      <h2>Create trainer</h2>

      <input
        name="name"
        placeholder="Trainer name"
        value={trainerForm.name}
        onChange={handleTrainerChange}
        required
      />

      <input
        name="email"
        type="email"
        placeholder="Trainer email"
        value={trainerForm.email}
        onChange={handleTrainerChange}
        required
      />

      <button type="submit">
        Create trainer
      </button>

      {trainerMessage && (
        <p className="message">
          {trainerMessage}
        </p>
      )}

    </form>
  );
}

export default TrainerForm;