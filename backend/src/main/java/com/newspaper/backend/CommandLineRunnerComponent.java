package com.newspaper.backend;

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

        DescriptionEntity description5 = new DescriptionEntity("IPhone news",
                "Mark Pan",
                "All about iphone.",
                18L,
                new Date(),
                "https://i.pinimg.com/736x/39/01/af/3901afb10b2f4cf2c71e21a286b5b39d--october-.jpg");

        DescriptionEntity description6 = new DescriptionEntity("Flowers",
                "Oleg Smith",
                "All about flowers.",
                19L,
                new Date(),
                "https://image.isu.pub/180720170813-6f44a322f274de1db46ef2c0060186d5/jpg/page_1.jpg");

        DescriptionEntity description7 = new DescriptionEntity("Music magazine",
                "Sasha Quenne",
                "All about music.",
                20L,
                new Date(),
                "https://files.magzter.com/resize/magazine/1423654150/1547041087/view/3.jpg");

        DescriptionEntity description8 = new DescriptionEntity("History",
                "Sophia Dormi",
                "All about history.",
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

        return new ArrayList<>(List.of(publication1, publication2,
                publication3, publication4,
                publication5, publication6,
                publication7, publication8));
    }
}
