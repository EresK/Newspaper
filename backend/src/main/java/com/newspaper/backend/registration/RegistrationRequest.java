package com.newspaper.backend.registration;

import lombok.*;

@Value
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    String email;
    String firstName;
    String lastName;
    String password;
}
