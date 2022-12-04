package com.newspaper.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    private String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @GetMapping("/me")
    public String getMe(){
        return this.getCurrentUsername();
    }

    @GetMapping
    public Iterable<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRequest> putUser(@PathVariable Long id, @RequestBody UserRequest user) {
        Boolean updated = userService.updateUserInformation(id, user);

        HttpStatus status = updated ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(user, status);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
