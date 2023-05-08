package com.newspaper.backend.authorization.privilege;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.newspaper.backend.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
public class Privilege {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "privileges")
    private Set<UserEntity> users;

    public Privilege(String name) {
        this.name = name;
    }
}
