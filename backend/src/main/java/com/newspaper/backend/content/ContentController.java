package com.newspaper.backend.content;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/publications/content")
public class ContentController {
    private final ContentService contentService;

    @PostMapping("/{publicationId}")
    public void postContent(Authentication auth,
                            @PathVariable Long publicationId,
                            HttpEntity<String> http) {
        contentService.createAndUpdateContent(auth, publicationId, http.getBody());
    }

    @GetMapping("/{publicationId}")
    public Optional<ContentEntity> getContent(Authentication auth, @PathVariable Long publicationId) {
        return contentService.getContent(auth, publicationId);
    }

    @PutMapping("/{publicationId}")
    public void putContent(Authentication auth,
                           @PathVariable Long publicationId,
                           HttpEntity<String> http) {
        contentService.createAndUpdateContent(auth, publicationId, http.getBody());
    }

    @DeleteMapping("/{publicationId}")
    public void deleteContent(Authentication auth, @PathVariable Long publicationId) {
        contentService.deleteContent(auth, publicationId);
    }
}
