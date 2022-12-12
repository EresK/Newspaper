package com.newspaper.backend.user;

import com.newspaper.backend.publication.PublicationEntity;
import com.newspaper.backend.publication.PublicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserAuthorities extends DefaultUserAuthorities {
    private final UserService userService;
    private final PublicationRepository publicationRepository;

    @Override
    @Transactional
    public boolean isOwnerOfPublication(String username, Long id) {
        UserEntity user = userService.loadUserByUsername(username);
        Optional<PublicationEntity> publication = publicationRepository.findById(id);

        return publication.isPresent() && Objects.equals(user.getId(), publication.get().getPublicationOwner().getId());
    }
}
