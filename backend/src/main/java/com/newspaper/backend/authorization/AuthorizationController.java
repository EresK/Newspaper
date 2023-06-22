package com.newspaper.backend.authorization;

import com.newspaper.backend.authorization.permissions.PermissionEntity;
import com.newspaper.backend.authorization.permissions.PermissionService;
import com.newspaper.backend.publication.PublicationRepository;
import com.newspaper.backend.user.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    private final PublicationRepository publicationRepository;
    private final PermissionService permissionService;

    @GetMapping("/owner")
    @PreAuthorize("isAuthenticated()")
    public boolean isOwnerOf(Authentication auth, @RequestParam("publication_id") Long publicationId) {
        var principal = (UserEntity) auth.getPrincipal();

        if (principal != null) {
            var publication = publicationRepository.findById(publicationId);

            return publication.filter(p -> AuthorizationComponent.isOwnerOf(principal, p)).isPresent();
        }

        return false;
    }

    @GetMapping("/member")
    @PreAuthorize("isAuthenticated()")
    public boolean isMemberOf(Authentication auth, @RequestParam("publication_id") Long publicationId) {
        var principal = (UserEntity) auth.getPrincipal();

        if (principal != null) {
            var publication = publicationRepository.findById(publicationId);

            return publication.filter(p -> AuthorizationComponent.isMemberOf(principal, p)).isPresent();
        }

        return false;
    }

    @GetMapping("/role")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<String>> getUserRole(Authentication auth,
                                                    @RequestParam("publication_id") Long publicationId) {
        var principal = (UserEntity) auth.getPrincipal();
        ResponseEntity<List<String>> response = ResponseEntity.notFound().build();

        if (principal != null) {
            var permissions = permissionService.getUserPermissions(principal);
            response = ResponseEntity.ok(permissions
                    .stream()
                    .filter(p -> p.getPublicationId().equals(publicationId))
                    .map(PermissionEntity::getRoleAsString)
                    .toList());
        }

        return response;
    }
}
