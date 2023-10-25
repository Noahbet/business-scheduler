import { useState, useEffect, useContext } from "react";
import { useNavigate, Link } from "react-router-dom";
import { findAllBoardGames } from "../../services/businessAPI";
import BoardGameCard from "./BusinessCard";
import AuthContext from "../../contexts/AuthContext";

function BoardGameList() {
  const [boardGames, setBoardGames] = useState([]);
  const navigate = useNavigate();

  const { user } = useContext(AuthContext);

  useEffect(() => {
    findAllBoardGames()
      .then((data) => setBoardGames(data))
      .catch((err) =>
        navigate("/error", {
          state: { message: err },
        })
      );
  }, [navigate]);

  return (
    <div>
      <div className="d-flex justify-content-between">
        <div className="me-auto p-2 bd-highlight">
          <h2>Board Games</h2>
        </div>
        <div className="p-2 bd-highlight">
          {user && (
            <Link to="/game/add" className="btn btn-secondary">
              Add Board Game
            </Link>
          )}
        </div>
      </div>
      <div className="row row-cols-1 row-cols-md-2 row-cols-lg-4">
        {boardGames.length === 0 ? (
          <div className="alert alert-info">
            Nothing here yet, add a board game!
          </div>
        ) : (
          boardGames.map((game) => <BoardGameCard key={game.id} game={game} />)
        )}
      </div>
    </div>
  );
}

export default BoardGameList;
