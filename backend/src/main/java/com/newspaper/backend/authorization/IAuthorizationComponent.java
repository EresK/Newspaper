package com.newspaper.backend.authorization;

import com.newspaper.backend.user.UserEntity;

public interface IAuthorizationComponent {
    boolean isSameUser(final UserEntity principal, final Long id);

    boolean isSameUser(final UserEntity principal, final String email);
}
