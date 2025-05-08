package FondoRoyaleApplication.services.impl;

import FondoRoyaleApplication.entities.Game;
import FondoRoyaleApplication.repositories.GameRepository;
import FondoRoyaleApplication.services.GameService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
}