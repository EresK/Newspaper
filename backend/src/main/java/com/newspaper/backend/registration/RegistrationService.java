package com.newspaper.backend.registration;

import com.newspaper.backend.Status;
import com.newspaper.backend.user.UserEntity;
import com.newspaper.backend.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistrationService {
    private UserService userService;

    public Status register(RegistrationRequest registrationRequest) {
       return userService.signUpUser(new UserEntity(registrationRequest.getEmail(),
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                registrationRequest.getPassword()));
    }
}
