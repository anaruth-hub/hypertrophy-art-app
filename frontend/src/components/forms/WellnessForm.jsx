import { useState } from "react";
import { API_BASE_URL } from "../../services/api";

function WellnessForm() {
  const [wellnessForm, setWellnessForm] = useState({
    userId: "",
    date: "",
    physicalState: "MEDIUM",
    mentalState: "MEDIUM",
    emotionalState: "MEDIUM",
    stressLevel: "MEDIUM",
    motivationLevel: "MEDIUM",
    notes: "",
  });

  const [wellnessMessage, setWellnessMessage] = useState("");

  function