package com.newspaper.backend.publication;

import com.newspaper.backend.user.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/publications")
public class PublicationController {
    private final PublicationService publicationService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public void postPublication(Authentication auth,
                                @RequestBody PublicationEntity publication) {
        var principal = (UserEntity) auth.getPrincipal();

        if (principal != null)
            publicationService.createPublication(principal, publication);
    }

    @PostMapping("/add-advert")
    @PreAuthorize("isAuthenticated()")
    public void postPublicationForAdvert(Authentication auth,
                                         @RequestParam(name = "id") Long id,
                                         @RequestBody Long advertId) {
        var principal = (UserEntity) auth.getPrincipal();

        if (principal != null)
            publicationService.setAdvert(principal, advertId, id);
    }

    @GetMapping("/all")
    public Iterable<PublicationEntity> getAllPublications(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC)
                                                          Pageable pageable) {
        return publicationService.getAllPublications(pageable);
    }

    @GetMapping(params = {"!user_id"})
    public Optional<PublicationEntity> getSpecifiedPublication(Authentication auth,
                                                               @RequestParam(name = "id") Long id) {
        var principal = (UserEntity) auth.getPrincipal();
        return publicationService.getSpecifiedPublication(principal, id);
    }

    @GetMapping(params = {"!id"})
    public Iterable<PublicationEntity> getAllUserPublications(Authentication auth,
                                                              @RequestParam(name = "user_id") Long id) {
        var principal = (UserEntity) auth.getPrincipal();
        return publicationService.getAllUserPublications(principal, id);
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public void putPublication(Authentication auth,
                               @RequestParam(name = "id") Long id,
                               @RequestBody PublicationEntity publication) {
        var principal = (UserEntity) auth.getPrincipal();

        if (principal != null)
            publicationService.updatePublication(principal, id, publication);
    }

    @DeleteMapping
    @PreAuthorize("isAuthenticated()")
    public void deletePublication(Authentication auth,
                                  @RequestParam(name = "id") Long id) {
        var principal = (UserEntity) auth.getPrincipal();

        if (principal != null)
            publicationService.deletePublication(principal, id);
    }
}
