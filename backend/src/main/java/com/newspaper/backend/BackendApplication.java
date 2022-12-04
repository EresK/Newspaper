package com.newspaper.backend;

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
	private UserRepository userRepository;

	@Autowired
	private PublicationRepository publicationRepository;

	// Наполнение базы данных
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		UserEntity user1 = new UserEntity("john@mail.com", "John", "Smith", "pwd");
		UserEntity user2 = new UserEntity("maria@gmail.com", "Maria", "Rosa", "pwd");

		PublicationEntity publication1 = new PublicationEntity(false);
		PublicationEntity publication2 = new PublicationEntity(false);
		PublicationEntity publication3 = new PublicationEntity(true);

		DescriptionEntity description1 = new DescriptionEntity("The Math", 10L,
				new Date(), "All about math.");

		DescriptionEntity description2 = new DescriptionEntity("AI research", 15L,
				new Date(), "All about AI.");

		DescriptionEntity description3 = new DescriptionEntity("Project X", 16L,
				new Date(), "In process.");

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

		publicationRepository.saveAll(List.of(publication1, publication2, publication3));
	}
}
