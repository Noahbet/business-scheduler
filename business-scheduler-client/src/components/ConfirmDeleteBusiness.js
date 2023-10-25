import { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

import {
  findBoardGameById,
  deleteBoardGameById,
} from "../services/boardGamesAPI";

function ConfirmDeleteBoardGame() {
  const [boardGame, setBoardGame] = useState({ title: "" });

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

  const handleSubmit = (evt) => {
    evt.preventDefault();
    deleteBoardGameById(id).then(() => {
      navigate("/", {
        state: { message: `${boardGame.title} deleted!` },
      });
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <h1 className="modal-title">Delete {boardGame.title}?</h1>
        <div className="alert alert-danger">This cannot be undone.</div>
        <div>
          <Link to="/" className="btn btn-secondary">
            Cancel
          </Link>
          <button type="submit" className="btn btn-primary">
            Delete
          </button>
        </div>
      </div>
    </form>
  );
}

export default ConfirmDeleteBoardGame;
