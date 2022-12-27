package com.newspaper.backend.description;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.newspaper.backend.publication.PublicationEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "descriptions")
public class DescriptionEntity {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "description",
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private PublicationEntity publication;
    private String title;
    private String author;
    private String description;
    private Long issueNumber;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date issueDate;
    private String coverImageLink;

    public DescriptionEntity(String title,
                             String author,
                             String description,
                             Long issueNumber,
                             String coverImageLink) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.issueNumber = issueNumber;
        this.issueDate = new Date();
        this.coverImageLink = coverImageLink;
    }

    public DescriptionEntity(String title,
                             String author,
                             String description,
                             Long issueNumber,
                             Date issueDate,
                             String coverImageLink) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.issueNumber = issueNumber;
        this.issueDate = issueDate;
        this.coverImageLink = coverImageLink;
    }
}
