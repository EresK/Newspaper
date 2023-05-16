package com.newspaper.backend.description;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DescriptionRepository extends JpaRepository<DescriptionEntity, Long> {
    @Query("SELECT d FROM DescriptionEntity d WHERE" +
            "(d.publication.id = ?1) AND" +
            "((d.publication.publicationOwner.id = ?#{principal?.id}) OR (d.publication.isHide = false))")
    Optional<DescriptionEntity> findDescription(Long publicationId);
}
