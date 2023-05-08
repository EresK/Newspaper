package com.newspaper.backend.authorization.permissions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<UserPublicationPermission, Long> {
    List<UserPublicationPermission>
    findAllByIdUserAndIdPublication(Long userId, Long publicationId);
}
