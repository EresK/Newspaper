package com.newspaper.backend.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final static String USER_NOT_FOUND = "user with email %s not found";

    @Transactional
    public Status deleteUser(Long id, String email) {
        if (id != null)
            userRepository.deleteById(id);
        else if (email != null)
            userRepository.deleteByEmail(email);

        return Status.SUCCESS;
    }

    @Override
    public UserEntity loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    @Transactional
    public boolean isCorrectLogin(UserLoginRequest userLogin) {
        var user = userRepository.findByEmail(userLogin.getEmail());

        return user.isPresent() && bCryptPasswordEncoder.matches(userLogin.getPassword(), user.get().getPassword());
    }

    public Status signUpUser(UserEntity user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return Status.ERROR;
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return Status.SUCCESS;
    }

    public Boolean updateUserInformation(Long id, UserEntity user) {
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
