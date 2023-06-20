package com.newspaper.backend.content;

import com.newspaper.backend.publication.PublicationService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@AllArgsConstructor
@Controller
public class EditorLogic {
    PublicationService publicationService;

    @MessageMapping("/upd/{id}")
    @SendTo("/editor/public/{id}")
    public Long updateContent(@DestinationVariable Long id) {
        return id;
    }

}