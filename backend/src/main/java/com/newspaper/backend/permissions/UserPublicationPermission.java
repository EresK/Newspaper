package com.newspaper.backend.permissions;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.newspaper.backend.permissions.role.UserRole;
import com.newspaper.backend.publication.PublicationEntity;
import com.newspaper.backend.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "permissions")
public class UserPublicationPermission {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @EmbeddedId
    private PermissionKey id;

    @JsonIgnore
    @ManyToOne
    @MapsId("publicationId")
    @JoinColumn(name = "publication_id")
    private PublicationEntity publication;

    @JsonIgnore
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "role")
    private UserRole role;

    public UserPublicationPermission(PermissionKey key,
                                     UserEntity user,
                                     PublicationEntity publication,
                                     UserRole role) {
        this.id = key;
        this.user = user;
        this.publication = publication;
        this.role = role;
    }

    @JsonGetter("userId")
    public Long getUserId() {
        return user.getId();
    }

    @JsonGetter("publicationId")
    public Long getPublicationId() {
        return publication.getId();
    }

    @JsonGetter("role")
    public String roleToString() {
        return role.getName();
    }
}
