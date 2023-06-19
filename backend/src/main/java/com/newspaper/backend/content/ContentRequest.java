package com.newspaper.backend.content;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@EqualsAndHashCode
@ToString
public class ContentRequest {
    public ContentRequest(String contentJson, String styleJson) {
        this.contentJson = contentJson;
        this.styleJson = styleJson;
    }

    String contentJson;
    String styleJson;
}
