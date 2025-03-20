package FondoRoyaleApplication.services.impl;

import FondoRoyaleApplication.entities.User;
import FondoRoyaleApplication.repositories.UserRepository;
import FondoRoyaleApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<User> createUser(User newUser) {
        if (newUser.getName() == null || newUser.getUsername() == null || newUser.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userRepository.save(newUser), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<User> validateLogin(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok((List<User>) userRepository.findAll());
    }

    @Override
    public ResponseEntity<User> findByName(String name) {
        User user = userRepository.findByName(name);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @Override
    public ResponseEntity<Map<String, String>> deleteUser(int id) {
        Optional<User> existingUser = userRepository.findById(id);
        Map<String, String> response = new HashMap<>();
        if (existingUser.isPresent()) {
            userRepository.deleteById(id);
            response.put("message", "User deleted");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("message", "Id not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<User> getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Map<String, String>> updateUser(int id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            if (updatedUser.getName() != null) user.setName(updatedUser.getName());
            if (updatedUser.getUsername() != null) user.setUsername(updatedUser.getUsername());
            if (updatedUser.getPassword() != null) user.setPassword(updatedUser.getPassword());
            userRepository.save(user);
            return ResponseEntity.ok(Map.of("message", "User updated successfully"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "User not found"));
    }
    
    @Override
    public ResponseEntity<Integer> getFondoCoinsByUser(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> ResponseEntity.ok(value.getFondocoins()))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Map<String, String>> updateFondoCoinsByUser(int id, int fondocoins) {
        Optional<User> userOptional = userRepository.findById(id);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setFondocoins(fondocoins);
            userRepository.save(user);
            return ResponseEntity.ok(Map.of("message", "Fondocoins updated successfully"));
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "User not found"));
    }
 
}