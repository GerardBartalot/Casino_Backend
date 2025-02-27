package FondoRoyaleApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import FondoRoyaleApplication.controllers.impl.UserController;
import FondoRoyaleApplication.entities.User;
import FondoRoyaleApplication.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUserSuccess() {
        // Arrange
        User newUser = new User();
        newUser.setName("Jane Doe");
        newUser.setUsername("jdoe");
        newUser.setPassword("password123");

        when(userRepository.save(any(User.class))).thenReturn(newUser);

        // Act
        ResponseEntity<User> response = userController.createUser(newUser);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User added successfully with ID: " + newUser.getUser_id(), response.getBody());
    }

    @Test
    void testCreateUserFailureMissingFields() {
        // Arrange
        User newUser = new User();

        // Act
        ResponseEntity<User> response = userController.createUser(newUser);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("All fields (id, name, username, and password) are required.", response.getBody());
    }

    @Test
    void testValidateLoginSuccess() {
        // Arrange
        String username = "jdoe";
        String password = "password123";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        when(userRepository.findByUsernameAndPassword(username, password)).thenReturn(user);

        // Act
        ResponseEntity<User> response = userController.validateLogin(username, password);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login Successful", response.getBody());
    }

    @Test
    void testValidateLoginFailure() {
        // Arrange
        String username = "jdoe";
        String password = "wrongpassword";
        when(userRepository.findByUsernameAndPassword(username, password)).thenReturn(null);

        // Act
        ResponseEntity<User> response = userController.validateLogin(username, password);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid username or password", response.getBody());
    }

    @Test
    void testGetAll() {
        // Arrange
        User user1 = new User();
        user1.setName("Jane Doe");
        User user2 = new User();
        user2.setName("John Smith");
        List<User> user = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(user);

        // Act
        ResponseEntity<List<User>> response = userController.getAll();

        // Assert
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testFindByNameFound() {
        // Arrange
        String name = "Jane Doe";
        User user = new User();
        user.setName(name);
        when(userRepository.findByName(anyString())).thenReturn(user);

        // Act
        ResponseEntity<User> response = userController.findByName(name);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(name, response.getBody().getName());
    }

    @Test
    void testFindByNameNotFound() {
        // Arrange
        when(userRepository.findByName(anyString())).thenReturn(null);

        // Act
        ResponseEntity<User> response = userController.findByName("NonExistentName");

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    void testDeleteNurseSuccess() {
        // Arrange
        int id = 1;
        User user = new User();
        user.setUser_id(id);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteById(id);

        // Act
        ResponseEntity<Map<String, String>> response = userController.deleteUser(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Nurse deleted", response.getBody());
    }

    @Test
    void testDeleteUserNotFound() {
        // Arrange
        int id = 1;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Map<String, String>> response = userController.deleteUser(id);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Id not found", response.getBody());
    }

    @Test
    void testGetUserByIdFound() {
        // Arrange
        int id = 1;
        User user = new User();
        user.setUser_id(id);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        // Act
        ResponseEntity<User> response = userController.getUserById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getUser_id());
    }

    @Test
    void testGetUserByIdNotFound() {
        // Arrange
        int id = 1;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<User> response = userController.getUserById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    void testUpdateUserSuccess() {
        // Arrange
        int id = 1;
        User existingUser = new User();
        existingUser.setUser_id(id);
        existingUser.setName("Jane Doe");

        User updatedUser = new User();
        updatedUser.setName("Jane Smith");
        updatedUser.setUsername("jsmith");
        updatedUser.setPassword("newpassword");

        when(userRepository.findById(anyInt())).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        // Act
        ResponseEntity<Map<String, String>> response = userController.updateUser(id, updatedUser);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Nurse updated successfully", response.getBody());
    }

    @Test
    void testUpdateUserNotFound() {
        // Arrange
        int id = 1;
        User updatedUser = new User();
        updatedUser.setName("Jane Smith");

        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Map<String, String>> response = userController.updateUser(id, updatedUser);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Nurse not found", response.getBody());
    }
}
