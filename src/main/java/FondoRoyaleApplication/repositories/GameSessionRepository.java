package FondoRoyaleApplication.repositories;

import FondoRoyaleApplication.entities.GameSession;
import FondoRoyaleApplication.entities.User;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameSessionRepository extends CrudRepository<GameSession, Integer> {
	   List<GameSession> findByUser(User user);
}
