package com.newspaper.backend.advert;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertRepository extends JpaRepository<AdvertEntity, Long> {
}
