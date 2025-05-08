package FondoRoyaleApplication.controllers.impl;

import FondoRoyaleApplication.constants.commons.ApiPathVariables;
import FondoRoyaleApplication.controllers.GameApi;
import FondoRoyaleApplication.entities.Game;
import FondoRoyaleApplication.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(ApiPathVariables.GAMES_BASE)
public class GameController implements GameApi {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    @GetMapping(ApiPathVariables.GET_ALL_GAMES)
    public ResponseEntity<List<Game>> getGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }
}