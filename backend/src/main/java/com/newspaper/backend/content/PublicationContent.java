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
    @Column(length = 1_000_000_000)
    private String contentJson;
    @Column(length = 1_000_000_000)
    private String styleJson;
    @Version
    Long version;

    @JsonIgnore
    @OneToOne(mappedBy = "content",
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private PublicationEntity publication;

    public PublicationContent(ContentRequest contentRequest) {
        this.contentJson = contentRequest.getContentJson();
        this.styleJson = contentRequest.getStyleJson();
        this.version=contentRequest.getVersion();
    }
}
