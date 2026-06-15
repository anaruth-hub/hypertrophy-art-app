import { useState } from "react";
import { apiFetch } from "../../services/api";

function NutritionForm() {
  const [nutritionForm, setNutritionForm] = useState({
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
      const nutrition = await apiFetch("/api/nutrition-entries", {
        method: "POST",
        body: JSON.stringify({
          date: nutritionForm.date,
          calories: Number(nutritionForm.calories),
          proteinGrams: Number(nutritionForm.proteinGrams),
          carbsGrams: Number(nutritionForm.carbsGrams),
          fatGrams: Number(nutritionForm.fatGrams),
          hydrationLiters: Number(nutritionForm.hydrationLiters),
          notes: nutritionForm.notes,
        }),
      });

      setNutritionMessage(
        `Nutrition registered successfully: ${nutrition.calories} kcal`
      );

      setNutritionForm({
        date: "",
        calories: "",
        proteinGrams: "",
        carbsGrams: "",
        fatGrams: "",
        hydrationLiters: "",
        notes: "",
      });
    } catch (error) {
      setNutritionMessage(
        "Could not register nutrition. Check required fields and active session."
      );
    }
  }

  return (
    <form className="form-card" onSubmit={handleNutritionSubmit}>
      <h2>Nutrition macros</h2>

      <p className="form-helper">
        Nutrition data will be linked to the logged-in user.
      </p>

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
