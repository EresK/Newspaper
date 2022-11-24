package com.newspaper.backend.controller;

import com.newspaper.backend.entity.CommentEntity;

import com.newspaper.backend.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/publications")
public class PublicationController {
    private final CommentRepository commentRepository;

    @GetMapping
    public Iterable<CommentEntity> getComments() {
        return commentRepository.findAll();
    }

    @PostMapping
    public CommentEntity postComment(@RequestBody CommentEntity comment) {
        return commentRepository.save(comment);
    }

    private String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}