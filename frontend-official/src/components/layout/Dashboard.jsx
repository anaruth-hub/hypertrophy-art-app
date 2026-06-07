import { useState } from "react";

import NavigationTabs from "./NavigationTabs";

import AssignmentForm from "../forms/AssignmentForm";
import TrainingForm from "../forms/TrainingForm";
import RecoveryForm from "../forms/RecoveryForm";
import NutritionForm from "../forms/NutritionForm";
import WellnessForm from "../forms/WellnessForm";
import ProgressSummary from "../forms/ProgressSummary";
import TrainerProgressSummary from "../forms/TrainerProgressSummary";
import DeloadRecommendation from "../forms/DeloadRecommendation";

function Dashboard({ auth, onLogout }) {
  const initialTab = auth.role === "TRAINER" ? "trainer-progress" : "training";
  const [activeTab, setActiveTab] = useState(initialTab);

  const userTabs = [
    { id: "training", label: "Training" },
    { id: "recovery", label: "Recovery" },
    { id: "nutrition", label: "Nutrition" },
    { id: "wellness", label: "Wellness" },
    { id: "progress", label: "My Progress" },
    { id: "recommendation", label: "Recommendations" },
    { id: "assignment", label: "Assign Trainer" },
  ];

  const trainerTabs = [
    { id: "trainer-progress", label: "Trainer Dashboard" },
  ];

  const tabs = auth.role === "TRAINER" ? trainerTabs : userTabs;

  return (
    <main className="dashboard">
      <header className="dashboard-header">
        <h1>El arte de la hipertrofia muscular</h1>

        <p>
          Logged in as {auth.name} ({auth.role})
        </p>

        <button type="button" className="logout-button" onClick={onLogout}>
          Logout
        </button>
      </header>

      <NavigationTabs
        tabs={tabs}
        activeTab={activeTab}
        onTabChange={setActiveTab}
      />

      <section className="dashboard-content">
        {activeTab === "assignment" && <AssignmentForm />}

        {activeTab === "training" && <TrainingForm />}

        {activeTab === "recovery" && <RecoveryForm />}

        {activeTab === "nutrition" && <NutritionForm />}

        {activeTab === "wellness" && <WellnessForm />}

        {activeTab === "progress" && <ProgressSummary />}

        {activeTab === "trainer-progress" && <TrainerProgressSummary />}

        {activeTab === "recommendation" && <DeloadRecommendation />}
      </section>
    </main>
  );
}

export default Dashboard;