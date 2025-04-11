package FondoRoyaleApplication.services;

import FondoRoyaleApplication.entities.GameSession;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface GameSessionService {
    ResponseEntity<GameSession> saveGameSession(GameSession newGameSession);
	ResponseEntity<List<GameSession>> getGameSessionsByUserId(int userId);
}