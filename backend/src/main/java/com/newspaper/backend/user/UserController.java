package com.newspaper.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public String getMe(Authentication auth) {
        return auth.getName();
    }

    @GetMapping
    public Iterable<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> putUser(@PathVariable Long id, @RequestBody UserEntity user) {
        Boolean updated = userService.updateUserInformation(id, user);

        HttpStatus status = updated ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(user, status);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(Authentication auth, @PathVariable Long id) {
        userService.deleteUser(auth, id);
    }
}
