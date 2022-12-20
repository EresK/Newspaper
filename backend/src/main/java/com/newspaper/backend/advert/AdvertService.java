package com.newspaper.backend.advert;

import com.newspaper.backend.publication.PublicationEntity;
import com.newspaper.backend.publication.PublicationRepository;
import com.newspaper.backend.user.UserEntity;
import com.newspaper.backend.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AdvertService {
    private final AdvertRepository advertRepository;
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;

    @Transactional
    public void createAdvert(Authentication auth, AdvertEntity advert) {
        if (auth.isAuthenticated()) {
            Optional<UserEntity> user = userRepository.findByEmail(auth.getName());

            if (user.isPresent()) {
                AdvertEntity newAdvert = new AdvertEntity();
                newAdvert.setAdvertiser(user.get());
                advertRepository.save(newAdvert);
            }
        }
    }

    @Transactional
    public void deleteAdvert(Authentication auth, Long id) {
        if (isOwnerOfAdvert(auth, id))
            publicationRepository.deleteById(id);
    }

    @Transactional
    private boolean isOwnerOfAdvert(Authentication auth, Long id) {
        if (auth.isAuthenticated()) {
            Optional<UserEntity> user = userRepository.findByEmail(auth.getName());
            Optional<AdvertEntity> advert = advertRepository.findById(id);

            return user.isPresent() &&
                    advert.isPresent() &&
                    Objects.equals(user.get().getId(), advert.get().getAdvertiser().getId());
        }
        return false;
    }

}
