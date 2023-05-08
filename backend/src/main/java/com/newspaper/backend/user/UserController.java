package com.newspaper.backend.user;

import com.newspaper.backend.Status;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/me")
    public String getMe(Authentication auth) {
        return auth.getName();
    }

    @GetMapping("/all")
    public Iterable<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping
    public ResponseEntity<Optional<UserEntity>> getUser(@RequestParam(name = "id", required = false) Long id,
                                                        @RequestParam(name = "email", required = false) String email) {
        Optional<UserEntity> user = Optional.empty();

        if (id != null) {
            user = userRepository.findById(id);
        }
        else if (email != null) {
            user = userRepository.findByEmail(email);
        }

        HttpStatus status = user.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;

        return new ResponseEntity<>(user, status);
    }

    @GetMapping("/login")
    public Boolean login(@RequestBody UserLogRequest user) {
        // TODO: check for equality, probably there is a bug
        return userService.loginCheck(user.getEmail(), user.getPassword());
    }

    @PutMapping
    public ResponseEntity<UserEntity> putUser(@RequestParam(name = "id") Long id, @RequestBody UserEntity user) {
        Boolean updated = userService.updateUserInformation(id, user);

        HttpStatus status = updated ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(user, status);
    }

    @DeleteMapping
    @PreAuthorize("isAuthenticated() and @Auth.isSameUser(principal, #email)")
    public ResponseEntity<Status> deleteUser(@RequestParam(name = "email", required = false)
                                                 @Param("email")
                                                 String email) {
        ResponseEntity<Status> response = ResponseEntity.notFound().build();

        if (email != null) {
            response = ResponseEntity.ok().body(userService.deleteUser(null, email));
        }

        return response;
    }
}
