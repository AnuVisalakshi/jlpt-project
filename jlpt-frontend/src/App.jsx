import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './Home';
import FlashcardPage from './FlashcardPage';
import './App.css';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        
        <Route path="/level/:level" element={<FlashcardPage />} />
      </Routes>
    </Router>
  );
}

export default App;