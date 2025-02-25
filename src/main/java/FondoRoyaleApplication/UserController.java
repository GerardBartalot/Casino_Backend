package FondoRoyaleApplication;

import FondoRoyaleApplication.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/register")

	public ResponseEntity<User> createUser(@RequestBody User newUser) {
		if (newUser.getName() == null || newUser.getName().isEmpty() || newUser.getUsername() == null
				|| newUser.getUsername().isEmpty() || newUser.getPassword() == null
				|| newUser.getPassword().isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		User savedUser = userRepository.save(newUser);
		if (savedUser != null) {
			return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<User> validateLogin(@RequestParam String username, @RequestParam String password) {
		User user = userRepository.findByUsernameAndPassword(username, password);

		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/index")
	public ResponseEntity<List<User>> getAll() {
		List<User> user = (List<User>) userRepository.findAll();
		return ResponseEntity.ok(user);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<User> findByName(@PathVariable String name) {
		User user = userRepository.findByName(name);
		if (user != null && user.getName().equalsIgnoreCase(name)) {
			return ResponseEntity.ok(user); // 200
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404
	}

	@PutMapping("/delete/{id}")
	public ResponseEntity<Map<String, String>> deleteUser(@PathVariable int id) {
		System.out.println("Recibida petición para borrar ID: " + id);
		Optional<User> existingUser = userRepository.findById(id);
		Map<String, String> response = new HashMap<>();
		if (existingUser.isPresent()) {
			userRepository.deleteById(id);
			response.put("message", "User deleted");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response.put("message", "Id not found");
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
	}

	@GetMapping("/read/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);// 200
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);// 404
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Map<String, String>> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
		Optional<User> existingUser = userRepository.findById(id);

		if (existingUser.isPresent()) {
			User user = existingUser.get();

			if (updatedUser.getName() != null) {
				user.setName(updatedUser.getName());
			}
			if (updatedUser.getUsername() != null) {
				user.setUsername(updatedUser.getUsername());
			}
			if (updatedUser.getPassword() != null) {
				user.setPassword(updatedUser.getPassword());
			}

			userRepository.save(user);

			Map<String, String> response = new HashMap<>();
			response.put("message", "User updated successfully");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			Map<String, String> response = new HashMap<>();
			response.put("message", "User not found");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

}