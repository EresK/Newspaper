package com.newspaper.backend.services;

import com.newspaper.backend.entity.UserEntity;
import com.newspaper.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static String USER_NOT_FOUND= "user with email %s not found";
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
    }
    public String signUpUser(UserEntity user){
        boolean exists=userRepository.findByEmail(user.getEmail()).isPresent();
        if (exists){throw new IllegalStateException("email already taken");}
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "";
    }
}
