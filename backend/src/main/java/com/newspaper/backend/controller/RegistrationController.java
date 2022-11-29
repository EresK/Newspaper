package com.newspaper.backend.controller;

import com.newspaper.backend.requests.RegistrationRequest;
import com.newspaper.backend.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reg")
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;
    @PostMapping
    public String register(@RequestBody RegistrationRequest registrationRequest) {
        return registrationService.register(registrationRequest);
    }
}
