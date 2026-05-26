const tabs = [
  { id: "users", label: "Users" },
  { id: "trainers", label: "Trainers" },
  { id: "assignment", label: "Assign Trainer" },
  { id: "training", label: "Training" },
  { id: "recovery", label: "Recovery" },
  { id: "nutrition", label: "Nutrition" },
  { id: "wellness", label: "Wellness" },
  { id: "progress", label: "Progress" },
  { id: "trainer-progress", label: "Trainer Progress" },
  { id: "recommendation", label: "Recommendation" },
];

function NavigationTabs({ activeTab, onTabChange }) {
  return (
    <nav className="tabs">
      {tabs.map((tab) => (
        <button
          key={tab.id}
          className={
            activeTab === tab.id
              ? "tab-button active"
              : "tab-button"
          }
          onClick={() => onTabChange(tab.id)}
        >
          {tab.label}
        </button>
      ))}
    </nav>
  );
}

export default NavigationTabs;