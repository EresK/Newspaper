package com.newspaper.backend.content;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Version;


@EqualsAndHashCode
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ContentRequest {
    public ContentRequest(Long version,String contentJson, String styleJson) {
        this.contentJson = contentJson;
        this.styleJson = styleJson;
        this.version=version;
    }
    @JsonProperty
    Long version;
    @JsonProperty
    String contentJson;
    @JsonProperty
    String styleJson;
}
