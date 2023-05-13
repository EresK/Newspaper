package com.newspaper.backend.authorization.permissions;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Embeddable
public class PermissionKey implements Serializable {
    @Column(name = "user_id")
    Long user;

    @Column(name = "publication_id")
    Long publication;

    @Column(name = "role_id")
    Long role;

    public PermissionKey(Long userId, Long publicationId, Long roleId) {
        this.user = userId;
        this.publication = publicationId;
        this.role = roleId;
    }
}