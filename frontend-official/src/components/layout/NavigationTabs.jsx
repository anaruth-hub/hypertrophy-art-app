function NavigationTabs({
  tabs,
  activeTab,
  onTabChange,
}) {
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