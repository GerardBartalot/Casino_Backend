package FondoRoyaleApplication.services.impl;
import java.util.List;
import FondoRoyaleApplication.entities.GameSession;
import FondoRoyaleApplication.entities.User;
import FondoRoyaleApplication.repositories.GameSessionRepository;
import FondoRoyaleApplication.services.GameSessionService;
import FondoRoyaleApplication.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GameSessionServiceImpl implements GameSessionService {
    
	@Autowired
    private GameSessionRepository gameSessionRepository;
	@Autowired
	private UserService userService;

    
	@Override
    public ResponseEntity<GameSession> saveGameSession(GameSession newGameSession) {
        if (newGameSession == null ||
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
	
	
	@Override
	public ResponseEntity<List<GameSession>> getGameSessionsByUserId(int userId) {
	    try {
	        ResponseEntity<User> userResponse = userService.getUserById(userId);
	        if (userResponse.getStatusCode().is2xxSuccessful() && userResponse.getBody() != null) {
	            User user = userResponse.getBody();
	            
	            List<GameSession> sessions = gameSessionRepository.findByUser(user);

	            if (sessions == null || sessions.isEmpty()) {
	                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	            }
	            return new ResponseEntity<>(sessions, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	
}