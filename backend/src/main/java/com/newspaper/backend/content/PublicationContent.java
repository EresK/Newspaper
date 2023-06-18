package com.newspaper.backend.content;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.newspaper.backend.publication.PublicationEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "contents")
public class PublicationContent {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue
    private Long id;

    private String contentJson;
    private String styleJson;

    @JsonIgnore
    @OneToOne(mappedBy = "content",
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private PublicationEntity publication;

    public PublicationContent(String contentJson, String styles) {
        this.contentJson = contentJson;
        this.styleJson = styles;
    }
}
