package com.newspaper.backend.publication;

import com.newspaper.backend.description.DescriptionEntity;
import com.newspaper.backend.mapper.DescriptionMapper;
import com.newspaper.backend.user.UserAuthorities;
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
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final UserAuthorities userAuthorities;
    private final UserRepository userRepository;
    private final DescriptionMapper descriptionMapper;

    // TODO: get & update content methods

    @Transactional
    public boolean createPublication(Authentication auth, PublicationDto publicationDto) {
        if (auth.isAuthenticated()) {
            Optional<UserEntity> user = userRepository.findByEmail(auth.getName());

            if (user.isPresent()) {
                PublicationEntity publication = new PublicationEntity();
                publication.setPublicationOwner(user.get());

                DescriptionEntity description = descriptionMapper.dtoToEntity(publicationDto.getDescriptionDto());
                publication.setDescription(description);

                publicationRepository.save(publication);

                return true;
            }
        }
        return false;
    }

    /**
     * @return publications which are visible for all
     */
    @Transactional
    public Iterable<PublicationEntity> getPublications(Authentication auth) {
        if (auth != null && auth.isAuthenticated()) {
            Optional<UserEntity> user = userRepository.findByEmail(auth.getName());

            if (user.isPresent()) {
                return publicationRepository
                        .findAll()
                        .parallelStream()
                        .filter(p -> !p.getIsHide() ||
                                (Objects.equals(p.getPublicationOwner().getId(), user.get().getId())))
                        .toList();
            }
        }

        return publicationRepository.findAll().parallelStream().filter(p -> !p.getIsHide()).toList();
    }

    public Optional<PublicationEntity> getPublicationById(Authentication auth, Long id) {
        Optional<PublicationEntity> publication = publicationRepository.findById(id);

        if (publication.isPresent() && !publication.get().getIsHide())
            return publication;
        else if (publication.isPresent() && publication.get().getIsHide() && isOwnerOfPublication(auth, id))
            return publication;

        return Optional.empty();
    }

    /**
     * @param id publication id
     * @return Optional description
     */
    public Optional<DescriptionEntity> getDescription(Authentication auth, Long id) {
        Optional<PublicationEntity> publication = publicationRepository.findById(id);

        if (publication.isPresent() && !publication.get().getIsHide())
            return Optional.of(publication.get().getDescription());
        else if (publication.isPresent() && publication.get().getIsHide() && isOwnerOfPublication(auth, id))
            return Optional.of(publication.get().getDescription());

        return Optional.empty();
    }

    @Transactional
    public void updatePublication(Authentication auth, Long id, PublicationDto publicationDto) {
        if (auth != null && auth.isAuthenticated()) {
            userRepository
                    .findByEmail(auth.getName())
                    .ifPresent(user ->
                            publicationRepository
                                    .findById(id)
                                    .ifPresent(publication ->
                                    {
                                        if (Objects.equals(publication.getPublicationOwner().getId(), user.getId())) {
                                            descriptionMapper.updateEntity(
                                                    publication.getDescription(),
                                                    publicationDto.getDescriptionDto()
                                            );
                                            publicationRepository.save(publication);
                                        }
                                    }));
        }
    }

    // TODO : change publication visibility

    /**
     * @param id     publication id
     * @param isHide true: publication is hidden, false: publication is visible for all users
     */
    public void setPublicationIsHide(Authentication auth, Long id, Boolean isHide) {
        if (isOwnerOfPublication(auth, id))
            publicationRepository.findById(id)
                    .ifPresent(publicationEntity -> publicationEntity.setIsHide(isHide));
    }

    public void deletePublication(Authentication auth, Long id) {
        if (isOwnerOfPublication(auth, id))
            publicationRepository.deleteById(id);
    }

    private boolean isOwnerOfPublication(Authentication auth, Long id) {
        return (auth != null) && auth.isAuthenticated() && userAuthorities.isOwnerOfPublication(auth.getName(), id);
    }
}
