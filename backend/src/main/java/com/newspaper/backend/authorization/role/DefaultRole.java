package com.newspaper.backend.authorization.role;

import java.util.Optional;

public enum DefaultRole {
    ROLE_EDITOR("ROLE_EDITOR"),
    ROLE_DESIGNER("ROLE_DESIGNER"),
    ROLE_ADVERTISER("ROLE_ADVERTISER");

    private final String name;

    DefaultRole(String name) {
        this.name = name;
    }

    public static Optional<DefaultRole> toRole(String roleName) {
        return switch (roleName.toUpperCase()) {
            case "ROLE_EDITOR" -> Optional.of(ROLE_EDITOR);
            case "ROLE_DESIGNER" -> Optional.of(ROLE_DESIGNER);
            case "ROLE_ADVERTISER" -> Optional.of(ROLE_ADVERTISER);
            default -> Optional.empty();
        };
    }

    @Override
    public String toString() {
        return name;
    }
}