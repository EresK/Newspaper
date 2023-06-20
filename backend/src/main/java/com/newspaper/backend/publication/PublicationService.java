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

<<<<<<< Updated upstream
                newPublication.get().setDescription(publication.getDescription());
                publicationRepository.save(newPublication.get());
            }
=======
        if (publication.isPresent() &&
                principal != null &&
                AuthorizationComponent.isOwnerOf(principal, publication.get()))
            return publication;

        return Optional.empty();
    }

    public Iterable<PublicationEntity> getAllUserPublications(@Nullable UserEntity principal, Long id) {
        var user = userRepository.findById(id);

        if (user.isPresent() &&
                principal != null &&
                AuthorizationComponent.isSameUser(principal, id))
            return user.get().getPublications();

        return user.<Iterable<PublicationEntity>>map(userEntity -> userEntity.getPublications().stream().filter(p -> !p.getIsHide()).toList()).orElse(null);

    }
    public ContentRequest getContentById(Long id){
        var publication = publicationRepository.findById(id);
        if(publication.isEmpty())
            return null;
        return new ContentRequest(publication.get().getContent().getId(),publication.get().getContent().getContentJson(),publication.get().getContent().getStyleJson());
    }
    // TODO: get & update content methods
    @Transactional
    public void setAdvert(@NonNull UserEntity principal, Long advertId, Long publicationId) {
        var advert = advertRepository.findById(advertId);
        var publication = publicationRepository.findById(publicationId);

        if (advert.isPresent() &&
                publication.isPresent() &&
                AuthorizationComponent.isOwnerOf(principal, publication.get())) {
            advert.get().setPublication(publication.get());
            advertRepository.save(advert.get());
>>>>>>> Stashed changes
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
