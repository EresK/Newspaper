package com.newspaper.backend.authorization.permissions;

import com.newspaper.backend.authorization.role.DefaultRole;
import com.newspaper.backend.user.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/permissions")
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public void createPermission(Authentication auth,
                                 @RequestParam(name = "user_id") Long anotherUserId,
                                 @RequestParam(name = "publication_id") Long publicationId,
                                 @RequestParam(name = "role") String role) {
        var principal = (UserEntity) auth.getPrincipal();

        if (principal != null) {
            Optional<DefaultRole> optionalRole = DefaultRole.toRole(role);
            optionalRole.ifPresent(defaultRole ->
                    permissionService.createPermission(principal, anotherUserId, publicationId, defaultRole));
        }
    }

    // For debug
    @GetMapping("/all")
    public List<PermissionEntity> getPermissions() {
        return permissionService.getPermissions();
    }

    @GetMapping(params = {"!id"})
    @PreAuthorize("isAuthenticated()")
    public List<PermissionEntity> getUserPermissions(Authentication auth) {
        var principal = (UserEntity) auth.getPrincipal();

        if (principal != null)
            return permissionService.getUserPermissions(principal);

        return null;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<PermissionEntity> getPublicationPermissions(Authentication auth,
                                                            @RequestParam(name = "id") Long publicationId) {
        var principal = (UserEntity) auth.getPrincipal();

        if (principal != null)
            return permissionService.getPublicationPermissions(principal, publicationId);

        return null;
    }

    @DeleteMapping
    @PreAuthorize("isAuthenticated()")
    public void revokePermission(Authentication auth,
                                 @RequestParam(name = "user_id") Long anotherUserId,
                                 @RequestParam(name = "publication_id") Long publicationId,
                                 @RequestParam(name = "role") String role) {
        var principal = (UserEntity) auth.getPrincipal();

        if (principal != null) {
            Optional<DefaultRole> optionalRole = DefaultRole.toRole(role);
            optionalRole.ifPresent(defaultRole ->
                    permissionService.revokePermission(principal, anotherUserId, publicationId, defaultRole));
        }
    }
}
