package com.newspaper.backend.publication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<PublicationEntity, Long> {
}
