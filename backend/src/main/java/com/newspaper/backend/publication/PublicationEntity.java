package com.newspaper.backend.publication;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.newspaper.backend.description.DescriptionEntity;
import com.newspaper.backend.user.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "publications")
public class PublicationEntity {
    @SequenceGenerator(
            name = "publication_sequence",
            sequenceName = "publication_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "publication_sequence"
    )
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "publication_owner_id", nullable = false)
    private UserEntity publicationOwner;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "description_id")
    private DescriptionEntity description;
    private Boolean isHide = true;

    public PublicationEntity(Boolean isHide) {
        this.isHide = isHide;
    }

    // TODO: fields for content, editors & advertisers
}
