package com.newspaper.backend.permissions;

import com.newspaper.backend.permissions.role.DefaultRole;
import com.newspaper.backend.permissions.role.UserRoleRepository;
import com.newspaper.backend.publication.PublicationRepository;
import com.newspaper.backend.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public void createPermission(Long userId, Long publicationId, DefaultRole role) {
        PermissionKey key = new PermissionKey(userId, publicationId);

        var user = userRepository.findById(userId);
        var publication = publicationRepository.findById(publicationId);
        var userRole = userRoleRepository.findRoleByName(role.toString());

        if (user.isPresent() && publication.isPresent() && userRole.isPresent()) {
            UserPublicationPermission permission = new UserPublicationPermission(key,
                    user.get(),
                    publication.get(),
                    userRole.get());

            permissionRepository.save(permission);
        }
    }

    public List<UserPublicationPermission> getPermissions() {
        return permissionRepository.findAll();
    }
}
