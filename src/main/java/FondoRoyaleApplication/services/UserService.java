package FondoRoyaleApplication.services;

import FondoRoyaleApplication.entities.User;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface UserService {
    ResponseEntity<User> createUser(User newUser);
    ResponseEntity<User> validateLogin(String username, String password);
    ResponseEntity<List<User>> getAll();
    ResponseEntity<User> findByName(String name);
    ResponseEntity<Map<String, String>> deleteUser(int id);
    ResponseEntity<User> getUserById(int id);
    ResponseEntity<Map<String, String>> updateUser(int id, User updatedUser);
    ResponseEntity<Integer> getFondoCoinsByUser(int id);
    ResponseEntity<Map<String, String>> updateFondoCoinsByUser(int id, int fondocoins);
    ResponseEntity<Integer> getExperienceByUser(int id);
    ResponseEntity<Map<String, String>> updateExperienceByUser(int id, int experience);
    ResponseEntity<Map<String, String>> updateProfilePicture(int id, String profilePicture);
    ResponseEntity<String> getProfilePicture(int id);
    ResponseEntity<Map<String, String>> deleteProfilePicture(int id);
    ResponseEntity<Map<String, String>> claimDailyReward(int id);
    ResponseEntity<Map<String, String>> getLastDailyReward(int id);
}