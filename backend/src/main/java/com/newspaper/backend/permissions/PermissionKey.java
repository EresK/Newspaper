package com.newspaper.backend.permissions;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@Embeddable
public class PermissionKey implements Serializable {
    @Column(name = "user_id")
    Long userId;

    @Column(name = "publication_id")
    Long publicationId;

    public PermissionKey(Long userId, Long publicationId) {
        this.userId = userId;
        this.publicationId = publicationId;
    }
}
