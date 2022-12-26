package com.newspaper.backend.content;

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
public class ContentService {
    private final ContentRepository contentRepository;
    private final PublicationRepository publicationRepository;

    @Transactional
    public void createAndUpdateContent(Authentication auth, Long publicationId, String contentJson) {
//        if (!auth.isAuthenticated())
//            return;

//         Optional<PublicationEntity> publication = publicationRepository.findPublication(publicationId);
        Optional<PublicationEntity> publication = publicationRepository.findById(publicationId);

        if (publication.isPresent()) {
            if (publication.get().getContent() == null)
                publication.get().setContent(new ContentEntity(contentJson));
            else
                publication.get().getContent().setContentJson(contentJson);
        }
    }

    public Optional<ContentEntity> getContent(Authentication auth, Long publicationId) {
        return contentRepository.findContent(publicationId);
    }

    @Transactional
    public void deleteContent(Authentication auth, Long publicationId) {
//        if (auth.isAuthenticated()) {
//            Optional<PublicationEntity> publication = publicationRepository.findPublication(publicationId);
//
//            if (publication.isPresent() && (publication.get().getContent() != null)) {
//                publication.get().getContent().setContentJson("");
//            }
//        }
        Optional<PublicationEntity> publication = publicationRepository.findById(publicationId);

        if (publication.isPresent() && (publication.get().getContent() != null)) {
            publication.get().getContent().setContentJson("");
        }
    }
}
