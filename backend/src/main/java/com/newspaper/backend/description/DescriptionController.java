package com.newspaper.backend.description;

import com.newspaper.backend.user.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/publications/description")
public class DescriptionController {
    private final DescriptionService descriptionService;

    @GetMapping
    public Optional<DescriptionEntity> getDescription(Authentication auth,
                                                      @RequestParam(name = "id") Long publicationId) {
        var principal = (UserEntity) auth.getPrincipal();

        return descriptionService.getDescription(principal, publicationId);
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public void putDescription(Authentication auth,
                               @RequestParam(name = "id") Long publicationId,
                               @RequestBody DescriptionEntity description) {
        var principal = (UserEntity) auth.getPrincipal();

        if (principal != null)
            descriptionService.updateDescription(principal, publicationId, description);
    }
}
