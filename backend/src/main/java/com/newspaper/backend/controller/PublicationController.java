package com.newspaper.backend.controller;

import com.newspaper.backend.entity.CommentEntity;

import com.newspaper.backend.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RequiredArgsConstructor
@RestController
@RequestMapping("/publication")
public class PublicationController {
    final CommentRepository commentRepository;
    private String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
    @GetMapping
    public Iterable<CommentEntity> getComments() {
        return commentRepository.findAll();
    }
    @PostMapping
    public CommentEntity postComment(@RequestBody String text){
        return commentRepository.save(new CommentEntity(1,text,new Date(),getCurrentUsername(),1,1));
    }
}
