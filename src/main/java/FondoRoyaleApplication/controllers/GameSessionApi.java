package FondoRoyaleApplication.controllers;

import FondoRoyaleApplication.constants.commons.ApiPathVariables;
import FondoRoyaleApplication.entities.GameSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathVariables.GAME_BASE)
public interface GameSessionApi {
    
	@PostMapping(ApiPathVariables.SAVE)
    ResponseEntity<GameSession> saveGameSession(@RequestBody GameSession newGameSession);
}