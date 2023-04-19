package com.newspaper.backend.permissions;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<UserPublicationPermission, Long> {
}
