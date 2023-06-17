package com.newspaper.backend.authorization.permissions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
    List<PermissionEntity> findAllByIdUserAndIdPublication(Long userId, Long publicationId);

    List<PermissionEntity> findAllByIdUser(Long userId);

    List<PermissionEntity> findAllByIdPublication(Long publicationId);

    void deleteByIdUserAndIdPublicationAndIdRole(Long userId, Long publicationId, Long roleId);
}
