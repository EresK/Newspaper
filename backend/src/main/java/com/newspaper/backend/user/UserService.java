package com.newspaper.backend.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static String USER_NOT_FOUND= "user with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public void signUpUser(UserEntity user){
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public Boolean updateUserInformation(Long id, UserRequest user) {
        Optional<UserEntity> userEntity = userRepository.findById(id);

        userEntity.ifPresent(entity -> {
            entity.setEmail(user.getEmail());
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());

            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            entity.setPassword(encodedPassword);

            userRepository.save(entity);
        });

        return userEntity.isPresent();
    }
}
