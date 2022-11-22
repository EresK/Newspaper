package com.newspaper.backend.repository;

import com.newspaper.backend.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<CommentEntity, Long> {
}
