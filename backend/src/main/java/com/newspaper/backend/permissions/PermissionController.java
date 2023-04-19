package com.newspaper.backend.permissions;

import com.newspaper.backend.permissions.role.DefaultRole;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/permissions")
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping("/create")
    public void createPermission(@RequestParam Long userId,
                                 @RequestParam Long publicationId,
                                 @RequestParam String role) {
        Optional<DefaultRole> optionalRole = DefaultRole.toRole(role);
        optionalRole.ifPresent(defaultRole -> permissionService.createPermission(userId, publicationId, defaultRole));
    }

    @GetMapping
    public List<UserPublicationPermission> getPermissions() {
        return permissionService.getPermissions();
    }

    @DeleteMapping
    public void revokePermission(@RequestParam Long userId,
                                 @RequestParam Long publicationId,
                                 @RequestParam String role) {
    }
}
