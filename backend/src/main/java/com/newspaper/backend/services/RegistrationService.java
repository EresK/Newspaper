package com.newspaper.backend.services;

import com.newspaper.backend.requests.RegistrationRequest;
import com.newspaper.backend.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private UserService userService;
    public String register(RegistrationRequest registrationRequest) {
        userService.signUpUser(new UserEntity(registrationRequest.getEmail(),
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getPassword()));
        return "+";
    }
}
