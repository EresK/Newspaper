package com.newspaper.backend.permissions;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@Embeddable
public class PermissionKey implements Serializable {
    @Column(name = "id", nullable = false)
    String id = UUID.randomUUID().toString();

    @Column(name = "user_id")
    Long user;

    @Column(name = "publication_id")
    Long publication;

    public PermissionKey(Long userId, Long publicationId) {
        this.user = userId;
        this.publication = publicationId;
    }
}