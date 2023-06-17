package com.newspaper.backend.authorization.role;

import java.util.Optional;

public enum DefaultRole {
    EDITOR("EDITOR"),
    DESIGNER("DESIGNER"),
    ADVERTISER("ADVERTISER");

    private final String name;

    DefaultRole(String name) {
        this.name = name;
    }

    public static Optional<DefaultRole> toRole(String roleName) {
        return switch (roleName.toUpperCase()) {
            case "EDITOR" -> Optional.of(EDITOR);
            case "DESIGNER" -> Optional.of(DESIGNER);
            case "ADVERTISER" -> Optional.of(ADVERTISER);
            default -> Optional.empty();
        };
    }

    @Override
    public String toString() {
        return name;
    }
}
