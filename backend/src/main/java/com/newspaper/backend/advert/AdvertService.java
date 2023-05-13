package com.newspaper.backend.advert;

import com.newspaper.backend.authorization.AuthorizationComponent;
import com.newspaper.backend.user.UserEntity;
import com.newspaper.backend.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class AdvertService {
    private final AdvertRepository advertRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createAdvert(@NonNull UserEntity principal, AdvertEntity advert) {
        var user = userRepository.findById(principal.getId());

        if (user.isPresent()) {
            advert.setAdvertiser(user.get());
            advertRepository.save(advert);
        }
    }

    @Transactional
    public void deleteAdvert(@NonNull UserEntity principal, Long id) {
        var advert = advertRepository.findById(id);

        if (advert.isPresent() && AuthorizationComponent.isOwnerOf(principal, advert.get()))
            advertRepository.deleteById(id);
    }
}
