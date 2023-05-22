package com.newspaper.backend.login;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody LoginData loginData) {
        System.out.println(loginData);

        var response = ResponseEntity.notFound().build();

        if (loginData.getEmail() != null && loginData.getPassword() != null && loginService.isCorrectLogin(loginData))
            response = ResponseEntity.ok(loginData);

        return response;
    }
}
