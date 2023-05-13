package com.newspaper.backend.authorization.permissions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<UserPublicationPermission, Long> {
    List<UserPublicationPermission> findAllByIdUserAndIdPublication(Long userId, Long publicationId);

    List<UserPublicationPermission> findAllByIdUser(Long userId);

    List<UserPublicationPermission> findAllByIdPublication(Long publicationId);

    void deleteByIdUserAndIdPublicationAndIdRole(Long userId, Long publicationId, Long roleId);
}
