package com.newspaper.backend.publication;

import com.newspaper.backend.advert.AdvertRepository;
import com.newspaper.backend.authorization.AuthorizationComponent;
import com.newspaper.backend.content.ContentRequest;
import com.newspaper.backend.content.PublicationContent;
import com.newspaper.backend.user.Status;
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
    public Status createPublication(@NonNull UserEntity principal, PublicationEntity publication) {
        var user = userRepository.findById(principal.getId());

        // TODO: add correct check for password
        if (user.isEmpty())
            return Status.NO_AUTH;
        if (!user.get().getPassword().equalsIgnoreCase(principal.getPassword()))
            return Status.DENIED;
        publication.setOwner(user.get());
        publicationRepository.save(publication);
        return Status.SUCCESS;
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

    public Iterable<PublicationEntity> getAllUserPublications(@NonNull UserEntity principal) {
        var user = userRepository.findByEmail(principal.getUsername());

        return user.<Iterable<PublicationEntity>>map(UserEntity::getPublications).orElse(null);
    }

    public ContentRequest getContentById(Long id) {
        var publication = publicationRepository.findById(id);
        if (publication.isEmpty())
            return null;
        return new ContentRequest(publication.get().getContent().getContentJson(), publication.get().getContent().getStyleJson(), publication.get().getContent().getId());
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
    public Status updateContent(@NonNull UserEntity principal, Long id, PublicationContent content) {
        var user = userRepository.findById(principal.getId());
        var publication = publicationRepository.findById(id);
        if (user.isEmpty())
            return Status.NO_AUTH;
        if (publication.isEmpty())
            return Status.ERROR;
        if (!AuthorizationComponent.isOwnerOf(user.get(), publication.get()))
            return Status.DENIED;
        publication.get().setContent(content);
        publicationRepository.save(publication.get());

        return Status.SUCCESS;


    }

    @Transactional
    public Status updatePublication(@NonNull UserEntity principal, Long id, PublicationEntity publicationNew) {
        var user = userRepository.findById(principal.getId());
        var publication = publicationRepository.findById(id);

        if (user.isEmpty())
            return Status.NO_AUTH;
        if (publication.isEmpty())
            return Status.ERROR;
        if (!AuthorizationComponent.isOwnerOf(user.get(), publication.get()))
            return Status.DENIED;
        publication.get().setDescription(publicationNew.getDescription());
        publicationRepository.save(publication.get());

        return Status.SUCCESS;

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
