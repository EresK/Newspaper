package com.newspaper.backend.fullPublication;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.newspaper.backend.publication.PublicationEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "full_publications")
public class FullPublicationEntity {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "publication_id")
    private Set<PublicationEntity> publicationEntity;

    @Version
    private Integer version;
}
