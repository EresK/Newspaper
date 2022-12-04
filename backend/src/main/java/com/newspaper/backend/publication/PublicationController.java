package com.newspaper.backend.publication;

import com.newspaper.backend.description.DescriptionEntity;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/publications")
public class PublicationController {
    private final PublicationService publicationService;

    @GetMapping
    public Iterable<PublicationEntity> getPublications() {
        return publicationService.getPublications();
    }

    @GetMapping("/{id}")
    public Optional<PublicationEntity> getPublicationById(@PathVariable Long id) {
        return publicationService.getPublicationById(id);
    }

    @GetMapping("/{id}/description")
    public Optional<DescriptionEntity> getDescription(@PathVariable Long id) {
        return publicationService.getDescription(id);
    }

    // TODO: complete post mapping
    // @PostMapping
    public void postPublication() {
    }

    @DeleteMapping("/{id}")
    public void deletePublication(@PathVariable Long id) {
        publicationService.deletePublication(id);
    }
}
