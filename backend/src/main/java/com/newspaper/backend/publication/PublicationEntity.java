package com.newspaper.backend.publication;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.newspaper.backend.advert.AdvertEntity;
import com.newspaper.backend.content.PublicationContent;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "publication_owner_id", nullable = false)
    private UserEntity publicationOwner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "description_id")
    private DescriptionEntity description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "content_id")
    private PublicationContent content;
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "advert_id")
    private AdvertEntity advert;
    private Boolean isHide = true;

    public PublicationEntity(Boolean isHide) {
        this.isHide = isHide;
    }

    public void setAdvert(AdvertEntity advert) {
        this.advert = advert;
    }
    // TODO: fields for editors & adverts
}
