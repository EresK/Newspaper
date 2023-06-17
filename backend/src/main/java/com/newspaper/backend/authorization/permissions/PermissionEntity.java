package com.newspaper.backend.authorization.permissions;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.newspaper.backend.authorization.role.UserRole;
import com.newspaper.backend.publication.PublicationEntity;
import com.newspaper.backend.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "permissions")
public class PermissionEntity {
    @JsonIgnore
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

    public PermissionEntity(PermissionKey key,
                            UserEntity user,
                            PublicationEntity publication,
                            UserRole role) {
        this.id = key;
        this.user = user;
        this.publication = publication;
        this.role = role;
    }

    @JsonGetter("user")
    public UserEntity getUser() {
        return user;
    }

    @JsonGetter("publication")
    public Long getPublicationId() {
        return publication.getId();
    }

    @JsonGetter("role")
    public String getRoleAsString() {
        return role.getName();
    }
}