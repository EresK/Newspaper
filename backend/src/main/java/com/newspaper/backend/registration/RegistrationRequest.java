package com.newspaper.backend.registration;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    String email;
    String firstName;
    String lastName;
    String password;
}
