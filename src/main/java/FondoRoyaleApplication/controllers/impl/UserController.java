package FondoRoyaleApplication.controllers.impl;

import FondoRoyaleApplication.controllers.UserApi;
import FondoRoyaleApplication.entities.User;
import FondoRoyaleApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class UserController implements UserApi {
	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity<User> createUser(@RequestBody User newUser) {
		return userService.createUser(newUser);
	}

	@Override
	public ResponseEntity<User> validateLogin(@RequestParam String username, @RequestParam String password) {
		return userService.validateLogin(username, password);
	}

	@Override
	public ResponseEntity<List<User>> getAll() {
		return userService.getAll();
	}

	@Override
	public ResponseEntity<User> findByName(@PathVariable String name) {
		return userService.findByName(name);
	}

	@Override
	public ResponseEntity<Map<String, String>> deleteUser(@PathVariable int id) {
		return userService.deleteUser(id);
	}

	@Override
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}

	@Override
	public ResponseEntity<Map<String, String>> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
		return userService.updateUser(id, updatedUser);
	}

	@Override
	public ResponseEntity<Integer> getFondoCoinsByUser(@PathVariable int id) {
		return userService.getFondoCoinsByUser(id);
	}

	@Override
	public ResponseEntity<Map<String, String>> updateFondoCoinsByUser(@PathVariable int id,
			@RequestBody int fondocoins) {
		return userService.updateFondoCoinsByUser(id, fondocoins);
	}

	@Override
	public ResponseEntity<Integer> getExperienceByUser(@PathVariable int id) {
		return userService.getExperienceByUser(id);
	}

	@Override
	public ResponseEntity<Map<String, String>> updateExperienceByUser(@PathVariable int id,
			@RequestBody int experience) {
		return userService.updateExperienceByUser(id, experience);
	}
	
	@Override
	public ResponseEntity<Map<String, String>> updateProfilePicture(@PathVariable int id, @RequestBody String profilePicture) {
	    return userService.updateProfilePicture(id, profilePicture);
	}

	@Override
	public ResponseEntity<String> getProfilePicture(@PathVariable int id) {
	    return userService.getProfilePicture(id);
	}

	@Override
	public ResponseEntity<Map<String, String>> deleteProfilePicture(int id) {
		return userService.deleteProfilePicture(id);
	}
	
	
	

}