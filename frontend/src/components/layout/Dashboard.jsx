import { useState } from "react";

import NavigationTabs from "./NavigationTabs";

import UserForm from "../forms/UserForm";
import TrainerForm from "../forms/TrainerForm";
import AssignmentForm from "../forms/AssignmentForm";
import TrainingForm from "../forms/TrainingForm";
import RecoveryForm from "../forms/RecoveryForm";
import NutritionForm from "../forms/NutritionForm";
import WellnessForm from "../forms/WellnessForm";

function Dashboard() {

  const [activeTab, setActiveTab] = useState("users");

  return (

    <main className="dashboard">

      <header className="dashboard-header">

        <h1>El arte de la hipertrofia muscular</h1>

        <p>
          Natural hypertrophy tracking platform
        </p>

      </header>

      <NavigationTabs
        activeTab={activeTab}
        onTabChange={setActiveTab}
      />

      <section className="dashboard-content">

        {activeTab === "users" && <UserForm />}

        {activeTab === "trainers" && <TrainerForm />}

        {activeTab === "assignment" && <AssignmentForm />}

        {activeTab === "training" && <TrainingForm />}

        {activeTab === "recovery" && <RecoveryForm />}

        {activeTab === "nutrition" && <NutritionForm />}

        {activeTab === "wellness" && <WellnessForm />}

      </section>

    </main>
  );
}

export default Dashboard;