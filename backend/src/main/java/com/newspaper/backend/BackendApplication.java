package com.newspaper.backend;

import com.newspaper.backend.advert.AdvertEntity;
import com.newspaper.backend.advert.AdvertRepository;
import com.newspaper.backend.description.DescriptionEntity;
import com.newspaper.backend.publication.PublicationEntity;
import com.newspaper.backend.publication.PublicationRepository;
import com.newspaper.backend.user.UserEntity;
import com.newspaper.backend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	@Autowired
	private AdvertRepository advertRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PublicationRepository publicationRepository;

	// Наполнение базы данных
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		UserEntity user1 = new UserEntity("john@gmail.com", "John", "Smith", "pwd");
		UserEntity user2 = new UserEntity("maria@gmail.com", "Maria", "Rosa", "pwd");
		AdvertEntity advert=new AdvertEntity(1L,"haha");

		PublicationEntity publication1 = new PublicationEntity(false);
		PublicationEntity publication2 = new PublicationEntity(false);
		PublicationEntity publication3 = new PublicationEntity(false);
		PublicationEntity publication4 = new PublicationEntity(false);
		PublicationEntity publication5 = new PublicationEntity(false);
		PublicationEntity publication6 = new PublicationEntity(false);
		PublicationEntity publication7 = new PublicationEntity(false);
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

		publication1.setPublicationOwner(user1);
		publication2.setPublicationOwner(user2);
		publication3.setPublicationOwner(user2);
		publication4.setPublicationOwner(user2);
		publication5.setPublicationOwner(user2);
		publication6.setPublicationOwner(user2);
		publication7.setPublicationOwner(user2);
		publication8.setPublicationOwner(user2);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		user1.setPassword(encoder.encode(user1.getPassword()));
		user2.setPassword(encoder.encode(user2.getPassword()));

		userRepository.saveAll(List.of(user1, user2));

		advert.setAdvertiser(user1);
		advertRepository.save(advert);

		publicationRepository.saveAll(List.of(publication1, publication2, publication3, publication4, publication5, publication6, publication7, publication8));
	}
}
