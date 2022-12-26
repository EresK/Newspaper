package com.newspaper.backend.description;

import lombok.Data;

@Data
public class DescriptionDto {
    private String title;
    private String description;
    private Long issueNumber;
    private String coverImageLink;
}
