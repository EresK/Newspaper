package com.newspaper.backend.user;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@EqualsAndHashCode
@ToString
public class LoginRequest {
    String email;
    String password;
}
