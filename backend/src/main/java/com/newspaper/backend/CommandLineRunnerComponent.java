package com.newspaper.backend;

import com.newspaper.backend.authorization.role.DefaultRole;
import com.newspaper.backend.authorization.role.UserRole;
import com.newspaper.backend.authorization.role.UserRoleRepository;
import com.newspaper.backend.content.PublicationContent;
import com.newspaper.backend.description.DescriptionEntity;
import com.newspaper.backend.publication.PublicationEntity;
import com.newspaper.backend.publication.PublicationRepository;
import com.newspaper.backend.user.UserEntity;
import com.newspaper.backend.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Component
public class CommandLineRunnerComponent implements CommandLineRunner {
    @Autowired
    private final com.newspaper.backend.security.PasswordEncoder passwordEncoder;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PublicationRepository publicationRepository;

    private final UserRoleRepository userRoleRepository;

    @Override
    @Transactional
    public void run(String... args) {
        PasswordEncoder encoder = passwordEncoder.bCryptPasswordEncoder();

        String password = encoder.encode("pwd");

        UserEntity userJohn = new UserEntity("john@gmail.com", "John", "Smith", password);
        UserEntity userMaria = new UserEntity("maria@gmail.com", "Maria", "Rosa", password);
        UserEntity userStan = new UserEntity("stan@gmail.com", "Stan", "Pines", password);
        UserEntity userVasya = new UserEntity("vasya@mail.ru", "Vasya", "Pupkin", password);

        List<PublicationEntity> publications = getPublications();

        for (int i = 0; i < publications.size(); i++) {
            switch (i) {
                case 0 -> publications.get(i).setOwner(userJohn);
                case 1, 2 -> publications.get(i).setOwner(userMaria);
                case 3, 4 -> publications.get(i).setOwner(userStan);
                default -> publications.get(i).setOwner(userVasya);
            }
        }

        userRepository.saveAll(List.of(userJohn, userMaria, userStan, userVasya));
        publicationRepository.saveAll(publications);

        saveRoles();
    }

    private void saveRoles() {
        UserRole editor = new UserRole(DefaultRole.EDITOR);
        UserRole advertiser = new UserRole(DefaultRole.ADVERTISER);
        UserRole designer = new UserRole(DefaultRole.DESIGNER);

        userRoleRepository.saveAll(List.of(editor, advertiser, designer));
    }

    private List<PublicationEntity> getPublications() {
        PublicationEntity publication1 = new PublicationEntity(false);
        PublicationEntity publication2 = new PublicationEntity(false);
        PublicationEntity publication3 = new PublicationEntity(false);
        PublicationEntity publication4 = new PublicationEntity(false);
        PublicationEntity publication5 = new PublicationEntity(false);
        PublicationEntity publication6 = new PublicationEntity(false);
        PublicationEntity publication7 = new PublicationEntity(true);
        PublicationEntity publication8 = new PublicationEntity(true);

        DescriptionEntity description1 = new DescriptionEntity("The Math",
                "John Smith",
                "All about math.",
                10L,
                new Date(),
                "https://avatars.mds.yandex.net/i?id=775afec050cb50978da0e62e390c0c04-5246290-images-thumbs&n=13");

        DescriptionEntity description2 = new DescriptionEntity("AI research",
                "Maria Rosa",
                "All about AI.",
                15L,
                new Date(),
                "https://avatars.mds.yandex.net/i?id=e57c9c7b4669828f98254d5d2dd5de1f-5105949-images-thumbs&n=13");

        DescriptionEntity description3 = new DescriptionEntity("Project X",
                "Maria Rosa",
                "In process.",
                16L,
                new Date(),
                "https://forex-brokers.pro/Foto/Forex/Otzyvi/836000/836521_dannyh_pro_rukovoditeley_aferistov_kristal_kazino_net.jpg");

        DescriptionEntity description4 = new DescriptionEntity("Sport day",
                "Peter Pen",
                "All about sport.",
                17L,
                new Date(),
                "https://avatars.mds.yandex.net/i?id=9c501ac4e0161635df8234de2f2113d0-5234070-images-thumbs&n=13");

        DescriptionEntity description5 = new DescriptionEntity("Music",
                "Mark Pan",
                "Music in depth",
                18L,
                new Date(),
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMJuTGReQa0KVn4KR6u6QQugjuWLVotsRjWg&usqp=CAU");

        DescriptionEntity description6 = new DescriptionEntity("Chess Master",
                "Oleg Smith",
                "All about Chess.",
                19L,
                new Date(),
                "https://chessily.com/wp-content/uploads/2022/04/chess-board-setup-the-chess-starting-position.png");

        DescriptionEntity description7 = new DescriptionEntity("Chess",
                "Sasha Quenne",
                "All about chess.",
                20L,
                new Date(),
                "https://files.magzter.com/resize/magazine/1423654150/1547041087/view/3.jpg");

        DescriptionEntity description8 = new DescriptionEntity("Music Day",
                "Sophia Dormi",
                "Music in depth.",
                21L,
                new Date(),
                "");

        publication1.setDescription(description1);
        publication2.setDescription(description2);
        publication3.setDescription(description3);
        publication4.setDescription(description4);
        publication5.setDescription(description5);
        publication6.setDescription(description6);
        publication7.setDescription(description7);
        publication8.setDescription(description8);

        try {
            String musicContent = new String(Files.readAllBytes(Paths.get("backend/src/main/resources/music-content")));
            String musicStyle = new String(Files.readAllBytes(Paths.get("backend/src/main/resources/music-style")));
            String chessContent = new String(Files.readAllBytes(Paths.get("backend/src/main/resources/chess-content")));
            String chessStyle = new String(Files.readAllBytes(Paths.get("backend/src/main/resources/chess-style")));

            var musicPublicationContent = new PublicationContent();
            musicPublicationContent.setContentJson(musicContent);
            musicPublicationContent.setStyleJson(musicStyle);
            musicPublicationContent.setVersion(1L);

            var chessPublicationContent = new PublicationContent();
            chessPublicationContent.setContentJson(chessContent);
            chessPublicationContent.setStyleJson(chessStyle);
            chessPublicationContent.setVersion(1L);

            publication5.setContent(musicPublicationContent);
            publication6.setContent(chessPublicationContent);
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return new ArrayList<>(List.of(publication1, publication2,
                publication3, publication4,
                publication5, publication6,
                publication7, publication8));
    }
}
