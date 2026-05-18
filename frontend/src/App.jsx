import { useState } from "react";
import "./App.css";

function App() {

  // USER
  const [userForm, setUserForm] = useState({
    name: "",
    email: "",
    mode: "SELF_MANAGED",
  });

  const [userMessage, setUserMessage] = useState("");

  // TRAINER
  const [trainerForm, setTrainerForm] = useState({
    name: "",
    email: "",
  });

  const [trainerMessage, setTrainerMessage] = useState("");

  // ASSIGNMENT
  const [assignmentForm, setAssignmentForm] = useState({
    userId: "",
    trainerId: "",
  });

  const [assignmentMessage, setAssignmentMessage] = useState("");

  // TRAINING
  const [trainingForm, setTrainingForm] = useState({
    userId: "",
    date: "",
    muscleGroup: "",
    exercises: "",
    intensity: "MEDIUM",
    durationMinutes: 60,
  });

  const [trainingMessage, setTrainingMessage] = useState("");

  // RECOVERY
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

  // NUTRITION
  const [nutritionForm, setNutritionForm] = useState({
    userId: "",
    date: "",
    calories: "",
    proteinGrams: "",
    carbsGrams: "",
    fatGrams: "",
    hydrationLiters: "",
    notes: "",
  });

  const [nutritionResult, setNutritionResult] = useState(null);

  // =========================
  // HANDLERS
  // =========================

  function handleUserChange(event) {
    const { name, value } = event.target;
    setUserForm({ ...userForm, [name]: value });
  }

  function handleTrainerChange(event) {
    const { name, value } = event.target;
    setTrainerForm({ ...trainerForm, [name]: value });
  }

  function handleAssignmentChange(event) {
    const { name, value } = event.target;
    setAssignmentForm({ ...assignmentForm, [name]: value });
  }

  function handleTrainingChange(event) {
    const { name, value } = event.target;
    setTrainingForm({ ...trainingForm, [name]: value });
  }

  function handleRecoveryChange(event) {
    const { name, value } = event.target;
    setRecoveryForm({ ...recoveryForm, [name]: value });
  }

  function handleNutritionChange(event) {
    const { name, value } = event.target;
    setNutritionForm({ ...nutritionForm, [name]: value });
  }

  // =========================
  // USER
  // =========================

  async function handleUserSubmit(event) {
    event.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/api/users", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(userForm),
      });

      if (!response.ok) {
        throw new Error("Could not create user");
      }

      const createdUser = await response.json();

      setUserMessage(`User created: ${createdUser.name}`);

    } catch (error) {
      setUserMessage("Error creating user");
    }
  }

  // =========================
  // TRAINER
  // =========================

  async function handleTrainerSubmit(event) {
    event.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/api/trainers", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(trainerForm),
      });

      if (!response.ok) {
        throw new Error("Could not create trainer");
      }

      const createdTrainer = await response.json();

      setTrainerMessage(`Trainer created: ${createdTrainer.name}`);

    } catch (error) {
      setTrainerMessage("Error creating trainer");
    }
  }

  // =========================
  // ASSIGN TRAINER
  // =========================

  async function handleAssignmentSubmit(event) {
    event.preventDefault();

    try {
      const response = await fetch(
        `http://localhost:8080/api/users/${assignmentForm.userId}/assign-trainer/${assignmentForm.trainerId}`,
        {
          method: "POST",
        }
      );

      if (!response.ok) {
        throw new Error("Could not assign trainer");
      }

      const assignment = await response.json();

      setAssignmentMessage(`Trainer assigned to ${assignment.userName}`);

    } catch (error) {
      setAssignmentMessage("Error assigning trainer");
    }
  }

  // =========================
  // TRAINING
  // =========================

  async function handleTrainingSubmit(event) {
    event.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/api/trainings", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          ...trainingForm,
          durationMinutes: Number(trainingForm.durationMinutes),
        }),
      });

      if (!response.ok) {
        throw new Error("Could not register training");
      }

      const training = await response.json();

      setTrainingMessage(`Training registered: ${training.muscleGroup}`);

    } catch (error) {
      setTrainingMessage("Error registering training");
    }
  }

  // =========================
  // RECOVERY
  // =========================

  async function handleRecoverySubmit(event) {
    event.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/api/recovery-checkins", {
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

      setRecoveryMessage(`Recovery registered: ${recovery.fatigueLevel}`);

    } catch (error) {
      setRecoveryMessage("Error registering recovery");
    }
  }

  // =========================
  // NUTRITION
  // =========================

  async function handleNutritionSubmit(event) {
    event.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/api/nutrition-entries", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          userId: nutritionForm.userId,
          date: nutritionForm.date,
          calories: Number(nutritionForm.calories),
          proteinGrams: Number(nutritionForm.proteinGrams),
          carbsGrams: Number(nutritionForm.carbsGrams),
          fatGrams: Number(nutritionForm.fatGrams),
          hydrationLiters: Number(nutritionForm.hydrationLiters),
          notes: nutritionForm.notes,
        }),
      });

      const data = await response.json();

      setNutritionResult(data);

    } catch (error) {
      console.error(error);
    }
  }

  return (
    <main className="page">

      <section className="card">

        <h1>El arte de la hipertrofia muscular</h1>

        <p>Natural hypertrophy tracking MVP</p>

        <div className="forms">

          {/* USER */}
          <form onSubmit={handleUserSubmit}>
            <h2>Create user</h2>

            <input
              name="name"
              placeholder="Name"
              value={userForm.name}
              onChange={handleUserChange}
            />

            <input
              name="email"
              placeholder="Email"
              value={userForm.email}
              onChange={handleUserChange}
            />

            <select
              name="mode"
              value={userForm.mode}
              onChange={handleUserChange}
            >
              <option value="SELF_MANAGED">SELF_MANAGED</option>
              <option value="SUPERVISED">SUPERVISED</option>
            </select>

            <button type="submit">Create user</button>

            <p>{userMessage}</p>
          </form>

          {/* TRAINER */}
          <form onSubmit={handleTrainerSubmit}>
            <h2>Create trainer</h2>

            <input
              name="name"
              placeholder="Trainer name"
              value={trainerForm.name}
              onChange={handleTrainerChange}
            />

            <input
              name="email"
              placeholder="Trainer email"
              value={trainerForm.email}
              onChange={handleTrainerChange}
            />

            <button type="submit">Create trainer</button>

            <p>{trainerMessage}</p>
          </form>

          {/* ASSIGN TRAINER */}
          <form onSubmit={handleAssignmentSubmit}>
            <h2>Assign trainer</h2>

            <input
              name="userId"
              placeholder="User ID"
              value={assignmentForm.userId}
              onChange={handleAssignmentChange}
            />

            <input
              name="trainerId"
              placeholder="Trainer ID"
              value={assignmentForm.trainerId}
              onChange={handleAssignmentChange}
            />

            <button type="submit">Assign trainer</button>

            <p>{assignmentMessage}</p>
          </form>

          {/* TRAINING */}
          <form onSubmit={handleTrainingSubmit}>
            <h2>Register training</h2>

            <input
              name="userId"
              placeholder="User ID"
              value={trainingForm.userId}
              onChange={handleTrainingChange}
            />

            <input
              type="date"
              name="date"
              value={trainingForm.date}
              onChange={handleTrainingChange}
            />

            <input
              name="muscleGroup"
              placeholder="Muscle group"
              value={trainingForm.muscleGroup}
              onChange={handleTrainingChange}
            />

            <input
              name="exercises"
              placeholder="Exercises"
              value={trainingForm.exercises}
              onChange={handleTrainingChange}
            />

            <select
              name="intensity"
              value={trainingForm.intensity}
              onChange={handleTrainingChange}
            >
              <option value="LOW">LOW</option>
              <option value="MEDIUM">MEDIUM</option>
              <option value="HIGH">HIGH</option>
            </select>

            <input
              type="number"
              name="durationMinutes"
              placeholder="Duration"
              value={trainingForm.durationMinutes}
              onChange={handleTrainingChange}
            />

            <button type="submit">Register training</button>

            <p>{trainingMessage}</p>
          </form>

          {/* RECOVERY */}
          <form onSubmit={handleRecoverySubmit}>
            <h2>Recovery check-in</h2>

            <input
              name="userId"
              placeholder="User ID"
              value={recoveryForm.userId}
              onChange={handleRecoveryChange}
            />

            <input
              type="date"
              name="date"
              value={recoveryForm.date}
              onChange={handleRecoveryChange}
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
            />

            <textarea
              name="notes"
              placeholder="Recovery notes"
              value={recoveryForm.notes}
              onChange={handleRecoveryChange}
            />

            <button type="submit">Register recovery</button>

            <p>{recoveryMessage}</p>
          </form>

          {/* NUTRITION */}
          <div className="card">
            <h2>Nutrition macros</h2>

            <form onSubmit={handleNutritionSubmit}>

              <input
                type="text"
                name="userId"
                placeholder="User ID"
                value={nutritionForm.userId}
                onChange={handleNutritionChange}
              />

              <input
                type="date"
                name="date"
                value={nutritionForm.date}
                onChange={handleNutritionChange}
              />

              <input
                type="number"
                name="calories"
                placeholder="Calories"
                value={nutritionForm.calories}
                onChange={handleNutritionChange}
              />

              <input
                type="number"
                step="0.1"
                name="proteinGrams"
                placeholder="Protein grams"
                value={nutritionForm.proteinGrams}
                onChange={handleNutritionChange}
              />

              <input
                type="number"
                step="0.1"
                name="carbsGrams"
                placeholder="Carbs grams"
                value={nutritionForm.carbsGrams}
                onChange={handleNutritionChange}
              />

              <input
                type="number"
                step="0.1"
                name="fatGrams"
                placeholder="Fat grams"
                value={nutritionForm.fatGrams}
                onChange={handleNutritionChange}
              />

              <input
                type="number"
                step="0.1"
                name="hydrationLiters"
                placeholder="Hydration liters"
                value={nutritionForm.hydrationLiters}
                onChange={handleNutritionChange}
              />

              <textarea
                name="notes"
                placeholder="Nutrition notes"
                value={nutritionForm.notes}
                onChange={handleNutritionChange}
              />

              <button type="submit">
                Register nutrition
              </button>

            </form>

            {nutritionResult && (
              <p>
                Nutrition registered: {nutritionResult.calories} kcal
              </p>
            )}
          </div>

        </div>

      </section>

    </main>
  );
}

export default App;