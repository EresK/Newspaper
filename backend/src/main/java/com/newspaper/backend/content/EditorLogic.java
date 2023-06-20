package com.newspaper.backend.content;

import com.newspaper.backend.publication.PublicationService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class EditorLogic {
    PublicationService publicationService;

    @MessageMapping("/upd")
    @SendTo("/editor/public")
    public Long updateContent(@Payload Long id) {
        return id;
    }

}