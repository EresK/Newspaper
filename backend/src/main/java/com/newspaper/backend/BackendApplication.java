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
		PublicationEntity publication3 = new PublicationEntity(true);

		DescriptionEntity description1 = new DescriptionEntity("The Math",
				"John Smith",
				"All about math.",
				10L,
				new Date(),
				"image.com/1");

		DescriptionEntity description2 = new DescriptionEntity("AI research",
				"Maria Rosa",
				"All about AI.",
				15L,
				new Date(),
				"image.com/2");

		DescriptionEntity description3 = new DescriptionEntity("Project X",
				"Maria Rosa",
				"In process.",
				16L,
				new Date(),
				"");

		publication1.setDescription(description1);
		publication2.setDescription(description2);
		publication3.setDescription(description3);

		publication1.setPublicationOwner(user1);
		publication2.setPublicationOwner(user2);
		publication3.setPublicationOwner(user2);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		user1.setPassword(encoder.encode(user1.getPassword()));
		user2.setPassword(encoder.encode(user2.getPassword()));

		userRepository.saveAll(List.of(user1, user2));

		advert.setAdvertiser(user1);
		advertRepository.save(advert);

		publicationRepository.saveAll(List.of(publication1, publication2, publication3));
	}
}
