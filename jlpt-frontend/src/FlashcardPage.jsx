import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

function FlashcardPage() {
  const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080';
  // to hold the list of words that are fetched from DB
  const [words, setWords] = useState([]);
  // to keep track of the current page number with first page#: 0
  const [page, setPage] = useState(0);
  // total page to be known for handling extremes
  const [totalPages, setTotalPages] = useState(0);
  // 4 modes of study: furigana, kanji, meaning, both
  const [displayMode, setDisplayMode] = useState("furigana");
  // keep track of the flipped status of the card
  const [flippedCards, setFlippedCards] = useState({});
  // check the level param
  const { level } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get(`${API_BASE_URL}/api/jlpt/wordslist/${level}?page=${page}&size=12`)
      .then((response) => {
        setWords(response.data.content);
        setTotalPages(response.data.page.totalPages);
        setFlippedCards({}); // reset flips on page change
      })
      .catch((err) => {
        console.error("Connection failed! Check if IntelliJ is running.", err);
      });
  }, [page, level]);

  const handlePageChange = (event) => {
    let targetPage = parseInt(event.target.value) - 1;
    if (isNaN(targetPage)) return;
    if (targetPage < 0) targetPage = 0;
    if (targetPage >= totalPages) targetPage = totalPages - 1;

    setPage(targetPage);
  };

  const renderFront = (word) => {
    // displaying furigana for words with no kanji
    const hasKanji = word.kanji && word.kanji.trim() !== "";
    const kanjiToDisplay = hasKanji ? word.kanji : word.furigana;

    if (displayMode === "furigana")
      return <h2 className="kanji-text">{word.furigana}</h2>;
    if (displayMode === "kanji")
      return <h2 className="kanji-text">{kanjiToDisplay}</h2>;
    if (displayMode === "meaning")
      return <h2 className="kanji-text">{word.meaning}</h2>;
    return (
      <>
        {hasKanji && <p className="furigana-text">{word.furigana}</p>}
        <h2 className="kanji-text">{kanjiToDisplay}</h2>
        <div className="divider"></div>
        <p className="meaning-text">{word.meaning}</p>
      </>
    );
  };

  const toggleFlip = (id) => {
    if (displayMode === "both") return;
    setFlippedCards((prev) => ({
      ...prev,
      [id]: !prev[id],
    }));
  };

  return (
    <div className="page-container">
      <button className="back-button" onClick={() => navigate('/')}>← Back to Home page</button>
      <h1 className="main-title">{level} Study Cards</h1>

      <div className="mode-selector">
        {["furigana", "kanji", "meaning", "both"].map((mode) => (
          <button
            key={mode}
            className={`mode-button ${displayMode === mode ? "active" : ""}`}
            onClick={() => {
              setDisplayMode(mode);
              setFlippedCards({});
            }}
          >
            {mode.charAt(0).toUpperCase() + mode.slice(1)} Only
          </button>
        ))}
      </div>

      <div className="cards-grid">
        {words.map((word) => (
          <div
            key={word.id}
            className="flip-card"
            onClick={() => toggleFlip(word.id)}
            style={{ cursor: displayMode === "both" ? "default" : "pointer" }}
          >
            <div
              className={`flip-card-inner ${
                flippedCards[word.id] ? "is-flipped" : ""
              }`}
            >
              <div className="card-front">
                {renderFront(word)}
                {displayMode !== "both" && (
                  <p
                    style={{
                      fontSize: "0.6rem",
                      color: "#ccc",
                      marginTop: "10px",
                    }}
                  >
                    Click to reveal
                  </p>
                )}
              </div>
              <div className="card-back">
                {word.kanji && word.kanji.trim() !== "" && (
                  <p className="furigana-text">{word.furigana}</p>
                )}
                <h2 className="kanji-text">{word.kanji || word.furigana}</h2>
                <div className="divider"></div>
                <p className="meaning-text">{word.meaning}</p>
              </div>
            </div>
          </div>
        ))}
      </div>

      <div className="pagination-controls">
        <button
          className="nav-button"
          onClick={() => setPage((p) => Math.max(0, p - 1))}
          disabled={page === 0}
        >
          Previous
        </button>
        <span className="page-info">
          Page
          <input
            type="number"
            className="page-input"
            value={page + 1}
            onChange={handlePageChange}
            min="1"
            max={totalPages || 1}
          />
          {totalPages > 0 ? ` of ${totalPages}` : " ..."}
        </span>
        <button className="nav-button" onClick={() => setPage((p) => p + 1)}>
          Next
        </button>
      </div>
    </div>
  );
}

export default FlashcardPage;
