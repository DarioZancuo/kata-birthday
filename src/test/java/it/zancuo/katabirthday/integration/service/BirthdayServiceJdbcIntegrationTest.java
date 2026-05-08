package it.zancuo.katabirthday.integration.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import it.zancuo.katabirthday.model.Person;
import it.zancuo.katabirthday.service.BirthdayService;

@SpringBootTest
@ActiveProfiles("jdbc")
public class BirthdayServiceJdbcIntegrationTest {
	
	@Autowired
	private BirthdayService birthdayS;
	
	@Test
	void birthdayMatchIntegrationTest() {
		
        LocalDate currentDate = LocalDate.of(2026, 5, 6);

        List<Person> result = birthdayS.findBirthdays(currentDate);

        assertThat(result).hasSize(2);

        assertThat(result).extracting(Person::email)
                		  .contains("dario.zancuo@mail.com",
                				  	"biffaemanuele@mail.com");
	}
	
	@Test
	void noBirthdayFoundTest() {
		
        LocalDate currentDate = LocalDate.of(2026, 10, 6);

        List<Person> result = birthdayS.findBirthdays(currentDate);

        assertThat(result).isEmpty();	
	}
	
	
}