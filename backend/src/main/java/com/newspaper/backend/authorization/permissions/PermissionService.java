package com.newspaper.backend.authorization.permissions;

import com.newspaper.backend.authorization.AuthorizationComponent;
import com.newspaper.backend.authorization.role.DefaultRole;
import com.newspaper.backend.authorization.role.UserRoleRepository;
import com.newspaper.backend.publication.PublicationRepository;
import com.newspaper.backend.user.UserEntity;
import com.newspaper.backend.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
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
    public void createPermission(@NonNull UserEntity principal, Long anotherUserId, Long publicationId, DefaultRole role) {
        var anotherUser = userRepository.findById(anotherUserId);
        var publication = publicationRepository.findById(publicationId);
        var userRole = userRoleRepository.findRoleByName(role.toString());

        if (anotherUser.isPresent() && publication.isPresent() && userRole.isPresent() &&
                AuthorizationComponent.isOwnerOf(principal, publication.get())) {
            PermissionKey key = new PermissionKey(anotherUserId, publicationId, userRole.get().getId());

            PermissionEntity permission = new PermissionEntity(key,
                    anotherUser.get(),
                    publication.get(),
                    userRole.get());

            var permissionList = permissionRepository.findAllByIdUserAndIdPublication(anotherUserId, publicationId);

            if (permissionList.stream().noneMatch(p -> p.getRole().getName().equalsIgnoreCase(role.toString())))
                permissionRepository.save(permission);
        }
    }

    public List<PermissionEntity> getPermissions() {
        return permissionRepository.findAll();
    }

    public List<PermissionEntity> getUserPermissions(@NonNull UserEntity principal) {
        return permissionRepository.findAllByIdUser(principal.getId());
    }

    @Transactional
    public List<PermissionEntity> getPublicationPermissions(@NonNull UserEntity principal,
                                                            Long publicationId) {
        var publication = publicationRepository.findById(publicationId);

        if (publication.isPresent() && AuthorizationComponent.isOwnerOf(principal, publication.get()))
            return permissionRepository.findAllByIdPublication(publicationId);

        return null;
    }

    @Transactional
    public void revokePermission(@NonNull UserEntity principal, Long anotherUserId, Long publicationId, DefaultRole role) {
        var anotherUser = userRepository.findById(anotherUserId);
        var publication = publicationRepository.findById(publicationId);

        if (anotherUser.isPresent() && publication.isPresent() &&
                AuthorizationComponent.isOwnerOf(principal, publication.get())) {
            var permissionList = permissionRepository.findAllByIdUserAndIdPublication(anotherUserId, publicationId);

            var matches = permissionList
                    .stream()
                    .filter(p -> p.getRole()
                            .getName()
                            .equalsIgnoreCase(role.toString()))
                    .toList();

            permissionRepository.deleteAll(matches);
        }
    }
}
