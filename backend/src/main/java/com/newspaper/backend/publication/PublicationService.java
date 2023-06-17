package com.newspaper.backend.publication;

import com.newspaper.backend.advert.AdvertRepository;
import com.newspaper.backend.authorization.AuthorizationComponent;
import com.newspaper.backend.user.UserEntity;
import com.newspaper.backend.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;
    private final AdvertRepository advertRepository;

    @Transactional
    public boolean createPublication(@NonNull UserEntity principal, PublicationEntity publication) {
        var user = userRepository.findById(principal.getId());

        // TODO: add correct check for password
        if (user.isPresent() && user.get().getPassword().equalsIgnoreCase(principal.getPassword())) {
            publication.setOwner(user.get());
            publicationRepository.save(publication);
            return true;
        }

        return false;
    }

    public Iterable<PublicationEntity> getAllPublications(Pageable pageable) {
        return publicationRepository.findAll(pageable).getContent();
    }

    @Transactional
    public Optional<PublicationEntity> getSpecifiedPublication(@Nullable UserEntity principal, Long id) {
        var publication = publicationRepository.findById(id);

        if (publication.isPresent() && !publication.get().getIsHide())
            return publication;

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

        if (user.isPresent())
            return user.get().getPublications().stream().filter(p -> !p.getIsHide()).toList();

        return null;
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
        }
    }

    @Transactional
    public void updatePublication(@NonNull UserEntity principal, Long id, PublicationEntity publicationNew) {
        var user = userRepository.findById(principal.getId());
        var publication = publicationRepository.findById(id);

        if (user.isPresent() &&
                publication.isPresent() &&
                AuthorizationComponent.isOwnerOf(user.get(), publication.get())) {
            publication.get().setDescription(publicationNew.getDescription());
            publicationRepository.save(publication.get());
        }
    }

    @Transactional
    public void deletePublication(@NonNull UserEntity principal, Long id) {
        var publication = publicationRepository.findById(id);

        if (publication.isPresent() &&
                AuthorizationComponent.isOwnerOf(principal, publication.get())) {
            publicationRepository.deleteById(id);
        }
    }
}
