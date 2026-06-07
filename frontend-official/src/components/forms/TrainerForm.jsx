import { useState } from "react";
import { API_BASE_URL } from "../../services/api";

function TrainerForm() {

  const [trainerForm, setTrainerForm] = useState({
    name: "",
    email: "",
  });

  const [trainerMessage, setTrainerMessage] = useState("");
  const [createdTrainer, setCreatedTrainer] = useState(null);

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

      setCreatedTrainer(createdTrainer);

      setTrainerMessage(
        `Trainer created successfully: ${createdTrainer.name}`
      );

      setTrainerForm({
        name: "",
        email: "",
      });

    } catch (error) {

      setTrainerMessage(
        "Could not create trainer. Please check the form data."
      );
    }
  }

  async function copyTrainerId() {
    if (!createdTrainer?.id) {
      return;
    }

    await navigator.clipboard.writeText(createdTrainer.id);
    setTrainerMessage("Trainer ID copied to clipboard.");
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

      {createdTrainer && (
        <div className="created-card">
          <h3>Created trainer</h3>

          <p>
            <span>Name</span>
            <strong>{createdTrainer.name}</strong>
          </p>

          <p>
            <span>Email</span>
            <strong>{createdTrainer.email}</strong>
          </p>

          <p>
            <span>Trainer ID</span>
            <strong>{createdTrainer.id}</strong>
          </p>

          <button type="button" onClick={copyTrainerId}>
            Copy Trainer ID
          </button>
        </div>
      )}

    </form>
  );
}

export default TrainerForm;