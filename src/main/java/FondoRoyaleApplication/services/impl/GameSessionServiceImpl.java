package FondoRoyaleApplication.services.impl;

import FondoRoyaleApplication.entities.GameSession;
import FondoRoyaleApplication.repositories.GameSessionRepository;
import FondoRoyaleApplication.services.GameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GameSessionServiceImpl implements GameSessionService {
    
	@Autowired
    private GameSessionRepository gameSessionRepository;
    
	@Override
    public ResponseEntity<GameSession> saveGameSession(GameSession newGameSession) {
        if (newGameSession == null ||
            newGameSession.getGameName() == null || newGameSession.getGameName().isEmpty() ||
            newGameSession.getRounds() == null || newGameSession.getRounds().isEmpty() ||
            newGameSession.getExperienceEarned() < 0 ||
            newGameSession.getFondocoinsSpent() < 0 ||
            newGameSession.getFondocoinsEarned() < 0 ||
            newGameSession.getUser() == null) {
            
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            GameSession savedSession = gameSessionRepository.save(newGameSession);
            return new ResponseEntity<>(savedSession, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}