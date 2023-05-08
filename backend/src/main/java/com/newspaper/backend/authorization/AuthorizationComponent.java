package com.newspaper.backend.authorization;

import com.newspaper.backend.user.UserEntity;
import org.springframework.stereotype.Component;

@Component("Auth")
public class AuthorizationComponent implements IAuthorizationComponent {
    public boolean isSameUser(UserEntity principal, Long id) {
        return principal.getId().equals(id);
    }

    public boolean isSameUser(UserEntity principal, String email) {
        return principal.getUsername().equals(email);
    }
}
