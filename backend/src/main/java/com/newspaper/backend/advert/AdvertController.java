package com.newspaper.backend.advert;

import com.newspaper.backend.publication.PublicationEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/adverts")
public class AdvertController {
    private final AdvertService advertService;
    @PostMapping
    public void postAdvert(Authentication auth, @RequestBody AdvertEntity advert) {
        advertService.createAdvert(auth, advert);
    }
    @DeleteMapping("/{id}")
    public void deleteAdvert(Authentication auth, @PathVariable Long id) {
        advertService.deleteAdvert(auth, id);
    }
}
