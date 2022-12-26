package com.newspaper.backend.content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContentRepository extends JpaRepository<ContentEntity, Long> {
//    @Query("SELECT c FROM ContentEntity c WHERE" +
//            "(c.publication.id = ?1) AND" +
//            "((c.publication.publicationOwner.id = ?#{principal?.id}) OR (c.publication.isHide = false))")
    @Query("SELECT c FROM ContentEntity c WHERE" +
            "(c.publication.id = ?1)")
    Optional<ContentEntity> findContent(Long publicationId);
}
