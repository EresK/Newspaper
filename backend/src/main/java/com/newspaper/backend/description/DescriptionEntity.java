package com.newspaper.backend.description;

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
    @SequenceGenerator(
            name = "description_sequence",
            sequenceName = "description_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "description_sequence"
    )
    private Long id;

    @OneToOne(mappedBy = "description",
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private PublicationEntity publication;
    private String title;
    private String description;
    private Long issueNumber;
    private Date issueDate;
    private String coverImageLink;

    public DescriptionEntity(String title,
                             String description,
                             Long issueNumber,
                             Date issueDate,
                             String coverImageLink) {
        this.title = title;
        this.description = description;
        this.issueNumber = issueNumber;
        this.issueDate = issueDate;
        this.coverImageLink = coverImageLink;
    }
}
