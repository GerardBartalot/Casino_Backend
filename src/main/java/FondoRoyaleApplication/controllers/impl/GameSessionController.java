package FondoRoyaleApplication.controllers.impl;

import FondoRoyaleApplication.controllers.GameSessionApi;
import FondoRoyaleApplication.entities.GameSession;
import FondoRoyaleApplication.services.GameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameSessionController implements GameSessionApi {
	@Autowired
	private GameSessionService gameSessionService;

	@Override
	public ResponseEntity<GameSession> saveGameSession(@RequestBody GameSession newGameSession) {
		return gameSessionService.saveGameSession(newGameSession);
	}
}