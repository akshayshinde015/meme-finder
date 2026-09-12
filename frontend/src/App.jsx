import { useState } from "react";
import "./App.css";

function App() {
  const [search, setSearch] = useState("");
  const [memes, setMemes] = useState([]);
  const [searched, setSearched] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const searchMeme = async () => {
    const trimmedSearch = search.trim();

    if (!trimmedSearch) {
      return;
    }

    setSearched(true);
    setLoading(true);
    setError("");
    setMemes([]);

    try {
      const encodedSearch = encodeURIComponent(trimmedSearch);

      const response = await fetch(
        `http://localhost:8080/api/memes/search?query=${encodedSearch}`
      );

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(
          errorData.message || "Failed to fetch memes"
        );
      }

      const data = await response.json();
      setMemes(data);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <main className="app">
      <section className="hero">
        <div className="badge">Multi-source meme search</div>

        <h1>
          Find the meme
          <span> you're looking for.</span>
        </h1>

        <p className="subtitle">
          Search across Memedroid and Imgflip from one place.
        </p>

        <form
          className="search-box"
          onSubmit={(e) => {
            e.preventDefault();
            searchMeme();
          }}
        >
          <input
            type="text"
            placeholder="Try: java, programming, cats..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />

          <button type="submit" disabled={loading}>
            {loading ? "Searching..." : "Search"}
          </button>
        </form>
      </section>

      <section className="results-section">
        {loading && (
          <p className="status">Looking for memes...</p>
        )}

        {error && (
          <p className="error">{error}</p>
        )}

        {searched && !loading && !error && memes.length === 0 && (
          <p className="status">
            No memes found for "{search}"
          </p>
        )}

        {!loading && memes.length > 0 && (
          <>
            <div className="results-header">
              <div>
                <h2>Results</h2>
                <p className="search-label">
                  Showing results for "{search}"
                </p>
              </div>

              <span>{memes.length} memes found</span>
            </div>

            <div className="meme-grid">
              {memes.map((meme) => (
                <article
                  className="meme-card"
                  key={`${meme.title}-${meme.imageUrl}`}
                >
                  <div className="image-wrapper">
                    <img
                      src={meme.imageUrl}
                      alt={meme.title}
                    />
                  </div>

                  <div className="card-content">
                    <h3>
                      {meme.title || "Untitled meme"}
                    </h3>

                    <span className="source">
                      {meme.source}
                    </span>
                  </div>
                </article>
              ))}
            </div>
          </>
        )}
      </section>
    </main>
  );
}

export default App;