package com.newspaper.backend.publication;

import com.newspaper.backend.content.ContentRequest;
import com.newspaper.backend.content.PublicationContent;
import com.newspaper.backend.user.Status;
import com.newspaper.backend.user.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Status> postPublication(Authentication auth,
                                                  @RequestBody PublicationEntity publication) {
        var principal = (UserEntity) auth.getPrincipal();

        var response = new ResponseEntity<>(Status.NO_AUTH, HttpStatus.NOT_FOUND);

        if (principal != null) {
            Status res = publicationService.createPublication(principal, publication);
            response = new ResponseEntity<>(res, HttpStatus.CREATED);
        }

        return response;
    }

    @GetMapping(value = "/content", params = {"id"})
    @PreAuthorize("isAuthenticated()")
    //id of a publication
    public @ResponseBody ContentRequest getContentById(Authentication auth, @RequestParam(name = "id") Long id) {
        var principal = (UserEntity) auth.getPrincipal();

        if (principal == null) {
            return null;
        }
        return publicationService.getContentById(id);
    }

    @PutMapping(value = "/update", params = {"id"})
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Status> updateContent(Authentication auth, @RequestParam(name = "id") Long id,
                                                @RequestBody ContentRequest content) {
        var principal = (UserEntity) auth.getPrincipal();

        var response = new ResponseEntity<>(Status.NO_AUTH, HttpStatus.UNAUTHORIZED);

        if (principal != null) {
            Status res = publicationService.updateContent(principal, id, new PublicationContent(content));
            response = new ResponseEntity<>(res, HttpStatus.OK);
        }

        return response;
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
        var principal = (auth != null) ? (UserEntity) auth.getPrincipal() : null;
        return publicationService.getSpecifiedPublication(principal, id);
    }

    @GetMapping(value = "/me", params = {"!id"})
    public Iterable<PublicationEntity> getAllUserPublications(Authentication auth) {
        var principal = (UserEntity) auth.getPrincipal();

        return (principal != null) ? publicationService.getAllUserPublications(principal) : null;
    }

    @PutMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Status> putPublication(Authentication auth,
                                                 @RequestParam(name = "id") Long id,
                                                 @RequestBody PublicationEntity publication) {
        var principal = (UserEntity) auth.getPrincipal();
        var response = new ResponseEntity<>(Status.NO_AUTH, HttpStatus.NOT_FOUND);
        if (principal != null) {
            Status res = publicationService.updatePublication(principal, id, publication);
            response = new ResponseEntity<>(res, HttpStatus.OK);
        }
        return response;
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
