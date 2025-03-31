package FondoRoyaleApplication.repositories;

import FondoRoyaleApplication.entities.GameSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameSessionRepository extends CrudRepository<GameSession, Integer> {
}
