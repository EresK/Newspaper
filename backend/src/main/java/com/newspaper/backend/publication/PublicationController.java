package com.newspaper.backend.publication;

import com.newspaper.backend.description.DescriptionEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/publications")
public class PublicationController {
    private final PublicationService publicationService;

    @PostMapping
    public ResponseEntity<PublicationDto>
    postPublication(Authentication auth, @RequestBody PublicationDto publicationDto) {
        HttpStatus status = publicationService.createPublication(auth, publicationDto) ?
                HttpStatus.CREATED : HttpStatus.NOT_MODIFIED;

        return new ResponseEntity<>(publicationDto, status);
    }

    @GetMapping
    public Iterable<PublicationEntity> getPublications(Authentication auth) {
        return publicationService.getPublications(auth);
    }

    @GetMapping("/{id}")
    public Optional<PublicationEntity> getPublicationById(Authentication auth, @PathVariable Long id) {
        return publicationService.getPublicationById(auth, id);
    }

    @GetMapping("/{id}/description")
    public Optional<DescriptionEntity> getDescription(Authentication auth, @PathVariable Long id) {
        return publicationService.getDescription(auth, id);
    }

    @PutMapping("{id}")
    public void putPublicationById(Authentication auth,
                                   @PathVariable Long id,
                                   @RequestBody PublicationDto publicationDto) {
        publicationService.updatePublication(auth, id, publicationDto);
    }

    @DeleteMapping("/{id}")
    public void deletePublication(Authentication auth, @PathVariable Long id) {
        publicationService.deletePublication(auth, id);
    }
}
