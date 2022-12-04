package com.newspaper.backend.publication;

import com.newspaper.backend.description.DescriptionEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PublicationService {
    private final PublicationRepository publicationRepository;

    // TODO: create publications methods
    // TODO: get & update content methods

    /**
     * @return publications which are visible for all
     */
    public Iterable<PublicationEntity> getPublications() {
        return publicationRepository.findAll().parallelStream().filter(p -> !p.getIsHide()).toList();
    }

    public Optional<PublicationEntity> getPublicationById(Long id) {
        Optional<PublicationEntity> publication = publicationRepository.findById(id);

        return (publication.isEmpty() || publication.get().getIsHide()) ?
                Optional.empty() : publication;
    }

    /**
     * @param id publication id
     * @return Optional description
     */
    public Optional<DescriptionEntity> getDescription(Long id) {
        Optional<PublicationEntity> publication = publicationRepository.findById(id);

        return (publication.isEmpty() || publication.get().getIsHide()) ?
                Optional.empty() : Optional.of(publication.get().getDescription());
    }

    /**
     * @param id publication id
     * @param isHide true: publication is hidden, false: publication is visible for all users
     */
    public void setPublicationIsHide(Long id, Boolean isHide) {
        publicationRepository.findById(id)
                .ifPresent(publicationEntity -> publicationEntity.setIsHide(isHide));
    }

    public void deletePublication(Long id) {
        publicationRepository.deleteById(id);
    }
}
