package FondoRoyaleApplication.controllers;

import FondoRoyaleApplication.constants.commons.ApiPathVariables;
import FondoRoyaleApplication.entities.Game;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ApiPathVariables.GAMES_BASE)
public interface GameApi {
    
    @GetMapping(ApiPathVariables.GET_ALL_GAMES)
    ResponseEntity<List<Game>> getGames();
    
}