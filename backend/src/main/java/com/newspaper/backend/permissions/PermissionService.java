package com.newspaper.backend.permissions;

import com.newspaper.backend.permissions.role.DefaultRole;
import com.newspaper.backend.permissions.role.UserRoleRepository;
import com.newspaper.backend.publication.PublicationRepository;
import com.newspaper.backend.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Transactional
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

            List<UserPublicationPermission> permissionList =
                    permissionRepository.findAllByIdUserAndIdPublication(userId, publicationId);

            if (permissionList.stream().noneMatch(p -> p.getRole().getName().equalsIgnoreCase(role.toString()))) {
                permissionRepository.save(permission);
            }
        }
    }

    public List<UserPublicationPermission> getPermissions() {
        return permissionRepository.findAll();
    }

    @Transactional
    public void revokePermission(Long userId, Long publicationId, DefaultRole role) {
        var user = userRepository.findById(userId);
        var publication = publicationRepository.findById(publicationId);

        if (user.isPresent() && publication.isPresent()) {
            List<UserPublicationPermission> permissionList =
                    permissionRepository.findAllByIdUserAndIdPublication(userId, publicationId);

            var matches = permissionList.stream()
                    .filter(p -> p.getRole()
                            .getName()
                            .equalsIgnoreCase(role.toString()))
                    .toList();
            permissionRepository.deleteAll(matches);
        }
    }
}
