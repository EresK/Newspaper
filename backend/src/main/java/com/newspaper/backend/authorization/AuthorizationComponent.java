package com.newspaper.backend.authorization;

import com.newspaper.backend.advert.AdvertEntity;
import com.newspaper.backend.publication.PublicationEntity;
import com.newspaper.backend.user.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("Auth")
public class AuthorizationComponent {
    public static boolean isSameUser(UserEntity principal, Long id) {
        return principal.getId().equals(id);
    }

    public static boolean isSameUser(UserEntity principal, String email) {
        return principal.getUsername().equals(email);
    }

    public static boolean isOwnerOf(UserEntity user, Object resource) {
        if (resource instanceof PublicationEntity) {
            return Objects.equals(user.getId(), ((PublicationEntity) resource).getOwner().getId());
        }
        else if (resource instanceof AdvertEntity) {
            return Objects.equals(user.getId(), ((AdvertEntity) resource).getAdvertiser().getId());
        }
        else
            return false;
    }
}