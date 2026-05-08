package it.zancuo.katabirthday.integration.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import it.zancuo.katabirthday.model.Person;
import it.zancuo.katabirthday.repository.impl.JdbcBirthdayRepository;

@SpringBootTest
@ActiveProfiles("jdbc")
public class JdbcBirthdayRepositoryTest {
		
		@Autowired
		private JdbcBirthdayRepository birthdayR;
		
		@Test
		void loadPeopleFromH2DB() {
			
			List<Person> result = birthdayR.findAll();
			
			assertThat(result).hasSize(4);
			
			assertThat(result)
					.extracting(Person::email)
					.contains("dario.zancuo@mail.com",
							  "biffaemanuele@mail.com",
							  "luigi.verdi@mail.com",
							  "mario.rossi@mail.com");
		}
	
}