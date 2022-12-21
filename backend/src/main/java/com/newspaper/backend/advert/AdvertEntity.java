package com.newspaper.backend.advert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.newspaper.backend.publication.PublicationEntity;
import com.newspaper.backend.user.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "adverts")
public class AdvertEntity {
    @SequenceGenerator(
            name = "advert_sequence",
            sequenceName = "advert_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "advert_sequence"
    )
    private Long id;
    private String title;
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "advertiser", nullable = false)
    private UserEntity advertiser;
    @JsonIgnore
    @OneToOne(mappedBy = "advert")
    private PublicationEntity publication;

    public AdvertEntity(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public void setAdvertiser(UserEntity advertiser) {
        this.advertiser = advertiser;
    }

    public void setPublication(PublicationEntity publication){
        this.publication=publication;
    }
    public UserEntity getAdvertiser(){
        return this.advertiser;
    }
}
