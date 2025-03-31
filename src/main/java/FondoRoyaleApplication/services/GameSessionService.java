package FondoRoyaleApplication.services;

import FondoRoyaleApplication.entities.GameSession;
import org.springframework.http.ResponseEntity;

public interface GameSessionService {
    ResponseEntity<GameSession> saveGameSession(GameSession newGameSession);
}