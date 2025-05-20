package FondoRoyaleApplication.controllers;

import FondoRoyaleApplication.constants.commons.ApiPathVariables;
import FondoRoyaleApplication.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RequestMapping(ApiPathVariables.USER_BASE)
public interface UserApi {
    @PostMapping(ApiPathVariables.REGISTER)
    ResponseEntity<User> createUser(@RequestBody User newUser);

    @PostMapping(ApiPathVariables.LOGIN)
    ResponseEntity<User> validateLogin(@RequestParam String username, @RequestParam String password);

    @GetMapping(ApiPathVariables.INDEX)
    ResponseEntity<List<User>> getAll();

    @GetMapping(ApiPathVariables.FIND_BY_NAME)
    ResponseEntity<User> findByName(@PathVariable String name);

    @PutMapping(ApiPathVariables.DELETE_USER)
    ResponseEntity<Map<String, String>> deleteUser(@PathVariable int id);

    @GetMapping(ApiPathVariables.READ_USER)
    ResponseEntity<User> getUserById(@PathVariable int id);

    @PutMapping(ApiPathVariables.UPDATE_USER)
    ResponseEntity<Map<String, String>> updateUser(@PathVariable int id, @RequestBody User updatedUser);
    
    @GetMapping(ApiPathVariables.GET_USER_FONDOCOINS)
    ResponseEntity<Integer> getFondoCoinsByUser(@PathVariable int id);
    
    @PutMapping(ApiPathVariables.UPDATE_USER_FONDOCOINS)
    ResponseEntity<Map<String, String>> updateFondoCoinsByUser(@PathVariable int id, @RequestBody int fondocoins);
    
    @GetMapping(ApiPathVariables.GET_USER_EXPERIENCE)
    ResponseEntity<Integer> getExperienceByUser(@PathVariable int id);
    
    @PutMapping(ApiPathVariables.UPDATE_USER_EXPERIENCE)
    ResponseEntity<Map<String, String>> updateExperienceByUser(@PathVariable int id, @RequestBody int experience);
    
    @PutMapping(ApiPathVariables.UPDATE_PROFILE_PICTURE)
    ResponseEntity<Map<String, String>> updateProfilePicture(@PathVariable int id, @RequestBody String profilePicture);

    @GetMapping(ApiPathVariables.GET_PROFILE_PICTURE)
    ResponseEntity<String> getProfilePicture(@PathVariable int id);
    
    @DeleteMapping(ApiPathVariables.DELETE_PROFILE_PICTURE)
    ResponseEntity<Map<String, String>> deleteProfilePicture(@PathVariable int id);
    
    @PutMapping(ApiPathVariables.DAILY_REWARD)
    ResponseEntity<Map<String, String>> claimDailyReward(@PathVariable int id);
    
    @GetMapping(ApiPathVariables.LAST_DAILY_REWARD)
    ResponseEntity<Map<String, String>> getLastDailyReward(@PathVariable int id);
}