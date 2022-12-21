package com.newspaper.backend.publication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PublicationRepository extends JpaRepository<PublicationEntity, Long> {
    @Query("SELECT p FROM PublicationEntity p WHERE p.publicationOwner.id = ?#{principal?.id}")
    Iterable<PublicationEntity> findUserPublications();

    @Query("SELECT p FROM PublicationEntity p WHERE" +
            "(p.id = ?1) AND" +
            "((p.publicationOwner.id = ?#{principal?.id}) OR (p.isHide = false))")
    Optional<PublicationEntity> findPublication(Long id);
}
