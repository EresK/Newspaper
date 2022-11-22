package com.newspaper.backend.controller;

import com.newspaper.backend.entity.UserEntity;
import com.newspaper.backend.repository.CommentRepository;
import com.newspaper.backend.repository.UserRepository;
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
    private final CommentRepository commentRepository;
    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
    @GetMapping
    public String/*Iterable<UserEntity>*/ getUsers() {
        return this.getCurrentUsername();
        //return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);

    }

    @PostMapping()
    public UserEntity postUser(@RequestBody UserEntity user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> putUser(@PathVariable Long id, @RequestBody UserEntity user) {
        HttpStatus status = userRepository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity<>(userRepository.save(user), status);
    }
}
