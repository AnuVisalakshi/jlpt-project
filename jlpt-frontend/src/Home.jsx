import React from 'react';
import { useNavigate } from 'react-router-dom';

function Home() {
  const navigate = useNavigate();
//   const levels = ["N5", "N4", "N3", "N2", "N1"];
  const levelData = [
    { name: "N5", count: 718 }, 
    { name: "N4", count: 1120 },
    { name: "N3", count: 1850 },
    { name: "N2", count: 2040 },
    { name: "N1", count: 2402 }
  ];

  return (
    <div className="home-container">
      <h1 className="home-title">Select Your JLPT Level</h1>
      <p className="home-subtitle">Choose a level to start practicing your vocabulary</p>
      
      <div className="level-grid">
        {levelData.map((lvl) => (
          <button 
            key={lvl.name} 
            className={`level-card ${lvl.name.toLowerCase()}`}
            onClick={() => navigate(`/level/${lvl.name}`)}
          >
            <span className="level-label">JLPT</span>
            <span className="level-number">{lvl.name}</span>
            <span className="level-count">({lvl.count} words)</span>
          </button>
        ))}
      </div>
    </div>
  );
}

export default Home;