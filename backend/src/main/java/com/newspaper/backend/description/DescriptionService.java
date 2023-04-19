package com.newspaper.backend.description;

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
public class DescriptionService {
    private final DescriptionRepository descriptionRepository;
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;

    @Transactional
    public Optional<DescriptionEntity> getDescription(Authentication auth, Long publicationId) {
        if (auth.isAuthenticated()) {
            Optional<UserEntity> user = userRepository.findByEmail(auth.getName());
            Optional<PublicationEntity> publication = publicationRepository.findById(publicationId);

            if (user.isEmpty() || publication.isEmpty() || publication.get().getDescription() == null)
                return Optional.empty();

            DescriptionEntity description = publication.get().getDescription();

            if (Objects.equals(user.get().getId(), publication.get().getPublicationOwner().getId()) ||
                    !publication.get().getIsHide()) {
                return Optional.of(description);
            }
        }

        return Optional.empty();
    }

    public void updateDescription(Authentication auth, Long publicationId, DescriptionEntity description) {
        if (auth.isAuthenticated()) {
            Optional<UserEntity> user = userRepository.findByEmail(auth.getName());
            Optional<PublicationEntity> publication = publicationRepository.findById(publicationId);

            if (user.isEmpty() || publication.isEmpty() || publication.get().getDescription() == null)
                return;

            DescriptionEntity descriptionEntity = publication.get().getDescription();

            if (Objects.equals(user.get().getId(), publication.get().getPublicationOwner().getId())) {
                descriptionEntity.setTitle(description.getTitle());
                descriptionEntity.setDescription(description.getDescription());
                descriptionEntity.setAuthor(description.getAuthor());
                descriptionEntity.setCoverImageLink(description.getCoverImageLink());
                descriptionRepository.save(descriptionEntity);
            }
        }
    }
}
