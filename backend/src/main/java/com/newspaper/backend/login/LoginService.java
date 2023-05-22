package com.newspaper.backend.login;

import com.newspaper.backend.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class LoginService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public boolean isCorrectLogin(LoginData loginData) {
        var user = userRepository.findByEmail(loginData.getEmail());

        return user.isPresent() && bCryptPasswordEncoder.matches(loginData.getPassword(), user.get().getPassword());
    }
}
