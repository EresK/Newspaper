package com.newspaper.backend.user;

import com.newspaper.backend.publication.PublicationEntity;
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
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/me")
    public String getMe(Authentication auth) {
        return auth.getName();
    }

    @GetMapping
    public Iterable<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> getUser(@PathVariable Long id) {
        return userRepository.findById(id);
    }
    @GetMapping("/login")
    public Boolean login(@RequestBody UserLogRequest user){
        return userService.loginCheck(user.getEmail(),user.getPassword());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> putUser(@PathVariable Long id, @RequestBody UserEntity user) {
        Boolean updated = userService.updateUserInformation(id, user);

        HttpStatus status = updated ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(user, status);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
