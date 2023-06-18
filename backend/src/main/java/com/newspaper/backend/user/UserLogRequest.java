package com.newspaper.backend.user;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@EqualsAndHashCode
@ToString
public class UserLogRequest {
    String email;
    String password;
}
