package FondoRoyaleApplication.repositories;

import java.util.List;

import FondoRoyaleApplication.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUsernameAndPassword(String username, String password);
	User findByName(String name);
	List<User> findByNameContainingIgnoreCase(String name);
}

