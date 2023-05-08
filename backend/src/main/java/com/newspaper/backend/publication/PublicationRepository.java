package com.newspaper.backend.publication;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PublicationRepository extends JpaRepository<PublicationEntity, Long> {
    @Query("SELECT p FROM PublicationEntity p WHERE p.owner.id = ?#{principal?.id}")
    Iterable<PublicationEntity> findUserPublications();

    @Query("SELECT p FROM PublicationEntity p WHERE" +
            "(p.id = ?1) AND" +
            "((p.owner.id = ?#{principal?.id}) OR (p.isHide = false))")
    Optional<PublicationEntity> findPublication(Long id);

    @Query("SELECT p FROM PublicationEntity p WHERE (p.isHide = false)")
    Page<PublicationEntity> findAll(Pageable pageable);
}
