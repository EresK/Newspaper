package com.newspaper.backend.registration;

import com.newspaper.backend.Status;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<Status> register(@RequestBody RegistrationRequest registrationRequest) {
        return ResponseEntity.ok()
                .body(registrationService.register(registrationRequest));
    }
}
