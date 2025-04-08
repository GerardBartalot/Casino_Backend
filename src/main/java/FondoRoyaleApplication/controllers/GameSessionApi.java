package FondoRoyaleApplication.controllers;

import FondoRoyaleApplication.constants.commons.ApiPathVariables;
import FondoRoyaleApplication.entities.GameSession;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathVariables.GAME_BASE)
public interface GameSessionApi {
    
	@PostMapping(ApiPathVariables.SAVE)
    ResponseEntity<GameSession> saveGameSession(@RequestBody GameSession newGameSession);
	
	@GetMapping(ApiPathVariables.SESSIONS)
	ResponseEntity<List<GameSession>> getGameSessionsByUserId(@PathVariable int id);
}