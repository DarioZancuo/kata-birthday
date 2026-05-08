package it.zancuo.katabirthday.integration.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import it.zancuo.katabirthday.model.Person;
import it.zancuo.katabirthday.repository.impl.FileBirthdayRepository;

@SpringBootTest
@ActiveProfiles({"file", "test"})
public class FileBirthdayRepositoryTest {
	
	@Autowired
	private FileBirthdayRepository birthdayR;
	
	@Test
	void loadPeopleFromFileTest() {
		
		List<Person> result = birthdayR.findAll();
		
		assertThat(result).hasSize(2);
		
		assertThat(result)
				.extracting(Person::email)
				.contains("dario.zancuo@mail.com",
						  "biffaemanuele@mail.com");
	}

}