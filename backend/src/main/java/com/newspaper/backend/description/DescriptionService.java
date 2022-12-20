package com.newspaper.backend.description;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class DescriptionService {
    private final DescriptionRepository descriptionRepository;

    public Optional<DescriptionEntity> getDescription(Authentication auth, Long publicationId) {
        return auth.isAuthenticated() ? descriptionRepository.findDescription(publicationId) : Optional.empty();
    }

    public void updateDescription(Authentication auth, Long publicationId, DescriptionEntity description) {
        if (auth.isAuthenticated()) {
            Optional<DescriptionEntity> descriptionEntity = descriptionRepository.findDescription(publicationId);

            if (descriptionEntity.isPresent()) {
                description.setId(descriptionEntity.get().getId());
                description.setPublication(descriptionEntity.get().getPublication());
                description.setIssueDate(descriptionEntity.get().getIssueDate());

                descriptionRepository.save(description);
            }
        }
    }
}
