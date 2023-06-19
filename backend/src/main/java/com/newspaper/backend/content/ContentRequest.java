package com.newspaper.backend.content;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import javax.persistence.Version;

@Value
@EqualsAndHashCode
@ToString
public class ContentRequest {
    public ContentRequest(String contentJson, String styleJson,Long version) {
        this.contentJson = contentJson;
        this.styleJson = styleJson;
        this.version=version;
    }

    Long version;
    String contentJson;
    String styleJson;
}
