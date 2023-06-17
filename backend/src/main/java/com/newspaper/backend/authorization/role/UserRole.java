package com.newspaper.backend.authorization.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.newspaper.backend.authorization.permissions.PermissionEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class UserRole {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<PermissionEntity> permissions;

    public UserRole(DefaultRole role) {
        name = role.toString();
    }
}
