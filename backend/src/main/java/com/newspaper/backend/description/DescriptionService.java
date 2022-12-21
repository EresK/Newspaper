package com.newspaper.backend.description;

import com.newspaper.backend.publication.PublicationEntity;
import com.newspaper.backend.publication.PublicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DescriptionService {
    private final DescriptionRepository descriptionRepository;
    private final PublicationRepository publicationRepository;

    public Iterable<DescriptionEntity> getAllDescriptions() {
        return descriptionRepository.findAll()
                .parallelStream()
                .filter(d -> !d.getPublication().getIsHide())
                .toList();
    }

    public Optional<DescriptionEntity> getDescription(Authentication auth, Long publicationId) {
        return auth.isAuthenticated() ? descriptionRepository.findDescription(publicationId) : Optional.empty();
    }

    @Transactional
    public void updateDescription(Authentication auth, Long publicationId, DescriptionEntity description) {
        if (auth.isAuthenticated()) {
            Optional<PublicationEntity> publication = publicationRepository.findById(publicationId);
            if (publication.isPresent()) {
                description.setId(publication.get().getDescription().getId());
                description.setPublication(publication.get());
                description.setIssueDate(publication.get().getDescription().getIssueDate());

                publication.get().setDescription(description);

                publicationRepository.save(publication.get());
            }
        }
    }
}
