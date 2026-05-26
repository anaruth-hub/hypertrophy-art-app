import { useState } from "react";
import { API_BASE_URL } from "../../services/api";

function NutritionForm() {
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

  const [nutritionMessage, setNutritionMessage] = useState("");

  function handleNutritionChange(event) {
    const { name, value } = event.target;
    setNutritionForm({ ...nutritionForm, [name]: value });
  }

  async function handleNutritionSubmit(event) {
    event.preventDefault();

    try {
      const response = await fetch(`${API_BASE_URL}/api/nutrition-entries`, {
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

      if (!response.ok) {
        throw new Error("Could not register nutrition");
      }

      const nutrition = await response.json();

      setNutritionMessage(`Nutrition registered: ${nutrition.calories} kcal`);
      setNutritionForm({
        userId: "",
        date: "",
        calories: "",
        proteinGrams: "",
        carbsGrams: "",
        fatGrams: "",
        hydrationLiters: "",
        notes: "",
      });
    } catch (error) {
      setNutritionMessage("Error registering nutrition");
    }
  }

  return (
    <form className="form-card" onSubmit={handleNutritionSubmit}>
      <h2>Nutrition macros</h2>

      <input
        name="userId"
        placeholder="User ID"
        value={nutritionForm.userId}
        onChange={handleNutritionChange}
        required
      />

      <input
        type="date"
        name="date"
        value={nutritionForm.date}
        onChange={handleNutritionChange}
        required
      />

      <input
        type="number"
        name="calories"
        placeholder="Calories"
        value={nutritionForm.calories}
        onChange={handleNutritionChange}
        required
      />

      <input
        type="number"
        step="0.1"
        name="proteinGrams"
        placeholder="Protein grams"
        value={nutritionForm.proteinGrams}
        onChange={handleNutritionChange}
        required
      />

      <input
        type="number"
        step="0.1"
        name="carbsGrams"
        placeholder="Carbs grams"
        value={nutritionForm.carbsGrams}
        onChange={handleNutritionChange}
        required
      />

      <input
        type="number"
        step="0.1"
        name="fatGrams"
        placeholder="Fat grams"
        value={nutritionForm.fatGrams}
        onChange={handleNutritionChange}
        required
      />

      <input
        type="number"
        step="0.1"
        name="hydrationLiters"
        placeholder="Hydration liters"
        value={nutritionForm.hydrationLiters}
        onChange={handleNutritionChange}
        required
      />

      <textarea
        name="notes"
        placeholder="Nutrition notes"
        value={nutritionForm.notes}
        onChange={handleNutritionChange}
      />

      <button type="submit">Register nutrition</button>

      {nutritionMessage && <p className="message">{nutritionMessage}</p>}
    </form>
  );
}

export default NutritionForm;