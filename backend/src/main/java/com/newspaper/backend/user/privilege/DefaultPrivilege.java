package com.newspaper.backend.user.privilege;

public enum DefaultPrivilege {
    ADMIN("ADMIN"),
    USER("USER");

    private final String name;

    DefaultPrivilege(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
