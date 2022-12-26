package com.newspaper.backend.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private RegistrationService registrationService;

    @PostMapping
    public void register(@RequestBody RegistrationRequest registrationRequest) {
        registrationService.register(registrationRequest);
    }
}
