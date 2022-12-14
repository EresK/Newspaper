package com.newspaper.backend.publication;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/publications")
public class PublicationController {
    private final PublicationService publicationService;
    @PostMapping("/addAdvert/{id}")
    public void setPublicationForAdvert(Authentication auth, @RequestBody Long advertId,@PathVariable Long id) {
        publicationService.setAdvert(auth,advertId,id);
    }

    @PostMapping
    public void postPublication(Authentication auth, @RequestBody PublicationEntity publication) {
        publicationService.createPublication(auth, publication);
    }

    @GetMapping()
    public Iterable<PublicationEntity> getUserPublications(Authentication auth) {
        return publicationService.getUserPublications(auth);
    }

    @GetMapping("/all")
    public Iterable<PublicationEntity> getAllPublications(@PageableDefault(sort={"id"},direction = Sort.Direction.DESC) Pageable pageable) {
        return publicationService.getAllPublications(pageable);
    }

    @GetMapping("/{id}")
    public Optional<PublicationEntity> getPublication(Authentication auth, @PathVariable Long id) {
        return publicationService.getPublication(auth, id);
    }

    @PutMapping("{id}")
    public void putPublication(Authentication auth,
                               @PathVariable Long id,
                               @RequestBody PublicationEntity publication) {
        publicationService.updatePublication(auth, id, publication);
    }

    @DeleteMapping("/{id}")
    public void deletePublication(Authentication auth, @PathVariable Long id) {
        publicationService.deletePublication(auth, id);
    }
}
