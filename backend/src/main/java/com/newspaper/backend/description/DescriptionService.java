package com.newspaper.backend.description;

import com.newspaper.backend.authorization.AuthorizationComponent;
import com.newspaper.backend.publication.PublicationRepository;
import com.newspaper.backend.user.UserEntity;
import com.newspaper.backend.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DescriptionService {
    private final DescriptionRepository descriptionRepository;
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;

    @Transactional
    public Optional<DescriptionEntity> getDescription(@Nullable UserEntity principal, Long publicationId) {
        var publication = publicationRepository.findById(publicationId);

        if (publication.isPresent() && principal == null && !publication.get().getIsHide())
            return Optional.of(publication.get().getDescription());

        if (publication.isPresent() &&
                principal != null &&
                AuthorizationComponent.isOwnerOf(principal, publication.get())) {
            return Optional.of(publication.get().getDescription());
        }

        return Optional.empty();
    }

    @Transactional
    public void updateDescription(@NonNull UserEntity principal, Long publicationId, DescriptionEntity descriptionNew) {
        var user = userRepository.findById(principal.getId());
        var publication = publicationRepository.findById(publicationId);

        if (user.isPresent() &&
                publication.isPresent() &&
                AuthorizationComponent.isOwnerOf(user.get(), publication.get())) {
            descriptionRepository.delete(publication.get().getDescription());

            descriptionNew.setPublication(publication.get());
            descriptionRepository.save(descriptionNew);
        }
    }
}
