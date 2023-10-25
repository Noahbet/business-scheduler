import { useState, useEffect } from "react";
import { useNavigate, useParams, Link } from "react-router-dom";

import { findBoardGameById, saveBoardGame } from "../services/boardGamesAPI";
import { findAllAvailabilities } from "../services/availabilityAPI";
import { findAllCategories } from "../services/categoryAPI";
import ValidationSummary from "./ValidationSummary";

const INITIAL_BOARD_GAME = {
  id: 0,
  title: "",
  minimumPlayers: 1,
  maximumPlayers: 4,
  retailReleaseDate: "",
  msrp: 0.0,
  rating: 5.0,
  availability: "",
  inCollection: false,
  imageUrl: "",
  categories: []
};

function BoardGameForm() {
  const [availabilities, setAvailabilities] = useState([]);
  const [categories, setCategories] = useState([]);
  const [boardGame, setBoardGame] = useState(INITIAL_BOARD_GAME);
  const [errors, setErrors] = useState([]);

  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    if (id) {
      findBoardGameById(id)
        .then((data) => setBoardGame(data))
        .catch((err) =>
          navigate("/error", {
            state: { message: err },
          })
        );
    }
  }, [id, navigate]);

  useEffect(() => {
    findAllAvailabilities().then(setAvailabilities);
  }, []);

  useEffect(() => {
    findAllCategories().then(setCategories);
  }, []);

  const handleChange = (evt) => {
    const nextGame = { ...boardGame };
    if (evt.target.type === "number") {
      nextGame[evt.target.name] = handleNumberChange(evt);
    } else if (evt.target.type === "checkbox") {
      nextGame[evt.target.name] = evt.target.checked;
    } else {
      nextGame[evt.target.name] = evt.target.value;
    }
    setBoardGame(nextGame);
  };

  const handleNumberChange = (evt) => {
    let nextValue = evt.target.value;
    if (
      evt.target.name === "minimumPlayers" ||
      evt.target.name === "minimumPlayers"
    ) {
      nextValue = parseInt(nextValue, 10);
    } else {
      nextValue = parseFloat(nextValue, 10);
    }
    return isNaN(nextValue) ? evt.target.value : nextValue;
  };

  const handleCategoryChanged = (evt) => {
    const nextBoardGame = {
      ...boardGame,
    };
    const categoryId = parseInt(evt.target.value, 10);
    if (evt.target.checked) {
      const category = categories.find((c) => c.id === categoryId);
      if (category) {
        nextBoardGame.categories = [...boardGame.categories, category];
      }
    } else {
      nextBoardGame.categories = boardGame.categories.filter(
        (c) => c.id !== categoryId
      );
    }
    setBoardGame(nextBoardGame);
  };

  const handleSubmit = (evt) => {
    evt.preventDefault();
    saveBoardGame(boardGame).then((data) => {
      if (data?.errors) {
        setErrors(data.errors);
      } else {
        navigate("/", {
          state: { message: `${boardGame.title} saved!` },
        });
      }
    });
  };

  const gameIsInCategory = (g, categoryId) => {
    return g.categories.find(gc => gc.id === categoryId) !== undefined;
  };

  return (
    <div>
      <ValidationSummary errors={errors} />

      <form onSubmit={handleSubmit}>
        <div>
          <h2 className="modal-title">
            {id ? `Edit ${boardGame.title}` : "New Board Game"}
          </h2>
          <div className="form-group">
            <label htmlFor="title">Title</label>
            <input
              className="form-control"
              type="text"
              name="title"
              id="title"
              value={boardGame.title}
              onChange={handleChange}
            />
          </div>
          <div className="form-row">
            <div className="col form-group">
              <label htmlFor="minimumPlayers">Minimum Players</label>
              <input
                className="form-control"
                min="0"
                type="number"
                name="minimumPlayers"
                id="minimumPlayers"
                value={boardGame.minimumPlayers}
                onChange={handleChange}
              />
            </div>
            <div className="col form-group">
              <label htmlFor="maximumPlayers">Maximum Players</label>
              <input
                min="0"
                className="form-control"
                type="number"
                name="maximumPlayers"
                id="maximumPlayers"
                value={boardGame.maximumPlayers}
                onChange={handleChange}
              />
            </div>
          </div>
          <div className="form-row">
            <div className="col form-group">
              <label htmlFor="retailReleaseDate">Retail Release Date</label>
              <input
                className="form-control"
                type="date"
                name="retailReleaseDate"
                id="retailReleaseDate"
                value={boardGame.retailReleaseDate}
                onChange={handleChange}
              />
            </div>
            <div className="col form-group">
              <label htmlFor="msrp">MSRP</label>
              <input
                min="0"
                step="0.01"
                className="form-control"
                type="number"
                name="msrp"
                id="msrp"
                value={boardGame.msrp}
                onChange={handleChange}
              />
            </div>
            <div className="col form-group">
              <label htmlFor="rating">Rating</label>
              <input
                min="0"
                max="10"
                step="0.01"
                className="form-control"
                type="number"
                name="rating"
                id="rating"
                value={boardGame.rating}
                onChange={handleChange}
              />
            </div>
          </div>
          <div className="form-row">
            <div className="col form-group">
              <label htmlFor="availability">Availability</label>
              <select
                required
                className="form-control"
                name="availability"
                value={boardGame.availability}
                onChange={handleChange}
              >
                <option disabled value="">
                  Choose an Availability
                </option>
                {availabilities.map((availability) => (
                  <option key={availability.value} value={availability.value}>
                    {availability.label}
                  </option>
                ))}
              </select>
            </div>
          </div>
          <div className="col form-group">
            <div className="form-check">
              <input
                className="form-check-input"
                type="checkbox"
                id="inCollection"
                name="inCollection"
                checked={boardGame.inCollection}
                onChange={handleChange}
              />
              <label className="form-check-label" htmlFor="inCollection">
                In collection
              </label>
            </div>
          </div>
          <div className="form-group">
            <label htmlFor="imageUrl">Image URL</label>
            <input
              type="url"
              className="form-control"
              name="imageUrl"
              id="imageUrl"
              value={boardGame.imageUrl}
              onChange={handleChange}
            />
          </div>
          <div className="col form-group">
            <label>Categories</label>
            {categories.map((c) => (
              <div className="form-check" key={c.id}>
                <input
                  className="form-check-input"
                  type="checkbox"
                  name="categories"
                  value={c.id}
                  id={`category_${c.id}`}
                  checked={gameIsInCategory(boardGame, c.id)}
                  onChange={handleCategoryChanged}
                />
                <label
                  className="form-check-label"
                  htmlFor={`category_${c.id}`}
                >
                  {c.label}
                </label>
              </div>
            ))}
          </div>
          <div>
            <Link to="/" className="btn btn-secondary">
              Cancel
            </Link>
            <button type="submit" className="btn btn-primary">
              Save changes
            </button>
          </div>
        </div>
      </form>
    </div>
  );
}

export default BoardGameForm;
