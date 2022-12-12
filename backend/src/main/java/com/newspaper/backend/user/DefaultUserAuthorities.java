package com.newspaper.backend.user;

public abstract class DefaultUserAuthorities {
    public abstract boolean isOwnerOfPublication(String username, Long id);
}
