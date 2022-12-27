package com.newspaper.backend.publication;

import com.newspaper.backend.advert.AdvertEntity;
import com.newspaper.backend.advert.AdvertRepository;
import com.newspaper.backend.user.UserEntity;
import com.newspaper.backend.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;
    private final AdvertRepository advertRepository;

    // TODO: get & update content methods
    @Transactional
    public void setAdvert(Authentication auth, Long advertId, Long publicationId) {
        if (auth.isAuthenticated()) {
            Optional<UserEntity> user = userRepository.findByEmail(auth.getName());
            Optional<AdvertEntity> advert= advertRepository.findById(advertId);
            Optional<PublicationEntity> publication = publicationRepository.findById(publicationId);
            if (isOwnerOfPublication(auth,publicationId)) {
                publication.get().setAdvert(advert.get());
                publicationRepository.save(publication.get());

            }
        }
    }

    @Transactional
    public void createPublication(Authentication auth, PublicationEntity publication) {
        if (auth.isAuthenticated()) {
            Optional<UserEntity> user = userRepository.findByEmail(auth.getName());

            if (user.isPresent()) {
                PublicationEntity newPublication = new PublicationEntity();

                newPublication.setPublicationOwner(user.get());
                newPublication.setDescription(publication.getDescription());

                publicationRepository.save(newPublication);
            }
        }
    }

    public Iterable<PublicationEntity> getUserPublications(Authentication auth) {
        return auth.isAuthenticated() ? publicationRepository.findUserPublications() : null;
    }

    public Iterable<PublicationEntity> getAllPublications(Pageable pageable) {
        return publicationRepository.findAll(pageable).getContent();
    }

    public Optional<PublicationEntity> getPublication(Authentication auth, Long id) {
        return auth.isAuthenticated() ? publicationRepository.findPublication(id) : Optional.empty();
    }

    @Transactional
    public void updatePublication(Authentication auth, Long id, PublicationEntity publication) {
        if (auth.isAuthenticated()) {
            Optional<UserEntity> user = userRepository.findByEmail(auth.getName());
            Optional<PublicationEntity> newPublication = publicationRepository.findById(id);

            if (user.isPresent() &&
                    newPublication.isPresent() &&
                    Objects.equals(newPublication.get().getPublicationOwner().getId(), user.get().getId())) {

                newPublication.get().setDescription(publication.getDescription());
                publicationRepository.save(newPublication.get());
            }
        }
    }

    @Transactional
    public void deletePublication(Authentication auth, Long id) {
        if (isOwnerOfPublication(auth, id))
            publicationRepository.deleteById(id);
    }

    @Transactional
    boolean isOwnerOfPublication(Authentication auth, Long id) {
        if (auth.isAuthenticated()) {
            Optional<UserEntity> user = userRepository.findByEmail(auth.getName());
            Optional<PublicationEntity> publication = publicationRepository.findById(id);

            return user.isPresent() &&
                    publication.isPresent() &&
                    Objects.equals(user.get().getId(), publication.get().getPublicationOwner().getId());
        }
        return false;
    }
}
