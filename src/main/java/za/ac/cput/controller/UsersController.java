package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.User;
import za.ac.cput.service.UserService;

import java.time.LocalDate;
import java.util.List;

/**
 * UsersController.java
 *
 * @author Rethabile Ntsekhe
 * Student Num: 220455430
 * @date 24-Jul-24
 */


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.create(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.read(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        User existingUser = userService.read(id);
        if (existingUser != null) {
            // Update the fields that can be changed
            User updatedUser = new User.Builder()
                    .copy(existingUser) // Copy existing user
                    .setUsername(user.getUsername()) // Update fields
                    .setPassword(user.getPassword())
                    .setEmail(user.getEmail())
                    .setFirstName(user.getFirstName())
                    .setLastName(user.getLastName())
                    .setCreatedAt(existingUser.getCreatedAt()) // Preserve creation date
                    .setUpdatedAt(LocalDate.now()) // Set current date as update date
                    .build();
            User savedUser = userService.update(updatedUser);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        User existingUser = userService.read(id);
        if (existingUser != null) {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<User>> getUsersByUsername(@PathVariable("username") String username) {
        List<User> users = userService.findByUsername(username);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<User>> getUsersByEmail(@PathVariable("email") String email) {
        List<User> users = userService.findByEmail(email);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/avatar/{avatar}")
    public ResponseEntity<List<User>> getUsersByAvatar(@PathVariable("avatar") String avatar) {
        List<User> users = userService.findByAvatar(avatar);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/roles/{role}")
    public ResponseEntity<List<User>> getUsersByRoles(@PathVariable("role") String role) {
        List<User> users = userService.findByRoles(role);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<List<User>> getUsersByLastName(@PathVariable("lastName") String lastName) {
        List<User> users = userService.findByLastName(lastName);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/createdAfter/{createdAt}")
    public ResponseEntity<List<User>> getUsersCreatedAfter(@PathVariable("createdAt") String createdAt) {
        LocalDate date;
        try {
            date = LocalDate.parse(createdAt);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<User> users = userService.findUsersCreatedAfter(date);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateUser(@RequestBody User user) {
        User validUser = userService.validateUser(user.getEmail(), user.getPassword());
        if (validUser != null) {
            return ResponseEntity.ok(validUser);
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
}
