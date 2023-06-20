package com.newspaper.backend.content;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newspaper.backend.publication.PublicationService;
import com.newspaper.backend.user.Status;
import com.newspaper.backend.user.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
public class EditorLogic {
    PublicationService publicationService;
    @MessageMapping("/upd")
    @SendTo("/editor/public")
    public Long updateContent(@Payload  Long id) {
        return id;
    }

}
