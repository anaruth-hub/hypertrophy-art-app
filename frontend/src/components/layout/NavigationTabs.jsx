function NavigationTabs({ activeTab, onTabChange }) {

  const tabs = [
    "users",
    "trainers",
    "assignment",
    "training",
    "recovery",
    "nutrition",
    "wellness"
  ];

  return (

    <nav className="tabs">

      {tabs.map((tab) => (

        <button
          key={tab}
          className={
            activeTab === tab
              ? "tab-button active"
              : "tab-button"
          }
          onClick={() => onTabChange(tab)}
        >
          {tab}
        </button>

      ))}

    </nav>
  );
}

export default NavigationTabs;