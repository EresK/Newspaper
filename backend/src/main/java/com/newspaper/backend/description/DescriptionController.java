package com.newspaper.backend.description;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/publications/description")
public class DescriptionController {
    private final DescriptionService descriptionService;

    @GetMapping("/{publicationId}")
    public Optional<DescriptionEntity> getDescription(Authentication auth, @PathVariable Long publicationId) {
        return descriptionService.getDescription(auth, publicationId);
    }

    @PutMapping("/{publicationId}")
    public void putDescription(Authentication auth,
                                                  @PathVariable Long publicationId,
                                                  @RequestBody DescriptionEntity description) {
        descriptionService.updateDescription(auth, publicationId, description);
    }
}
