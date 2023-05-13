package com.newspaper.backend.advert;

import com.newspaper.backend.user.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/adverts")
public class AdvertController {
    private final AdvertService advertService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public void postAdvert(Authentication auth, @RequestBody AdvertEntity advert) {
        var principal = (UserEntity) auth.getPrincipal();

        if (principal != null)
            advertService.createAdvert(principal, advert);
    }

    @DeleteMapping
    @PreAuthorize("isAuthenticated()")
    public void deleteAdvert(Authentication auth, @RequestParam(name = "id") Long id) {
        var principal = (UserEntity) auth.getPrincipal();

        if (principal != null)
            advertService.deleteAdvert(principal, id);
    }
}
