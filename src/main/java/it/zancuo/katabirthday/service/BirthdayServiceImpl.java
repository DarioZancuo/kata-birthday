package it.zancuo.katabirthday.service;

import it.zancuo.katabirthday.model.Person;
import it.zancuo.katabirthday.repository.BirthdayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BirthdayServiceImpl implements BirthdayService {

	private final BirthdayRepository birthdayRepository;

	@Override
	public List<Person> findBirthdays(LocalDate currentDate) {

		List<Person> personL = birthdayRepository.findAll();

		return personL.stream()
				.filter(person -> isBirthday(person, currentDate))
				.toList();
	}

	@Override
    public void processBirthdays(LocalDate currentDate) {

        List<Person> birthdayPeople = findBirthdays(currentDate);

        if (birthdayPeople.isEmpty()) {
            log.info("Nessun compleanno oggi.");
            return;
        }

        birthdayPeople.forEach(person -> log.info(
        	        "Happy birthday, dear {} {}!",
        	        person.name(),
        	        person.surname()
        		));
        
        birthdayPeople.forEach(person -> log.info(
        			"Email sent to: {}",
        			person.email()
    		));
        
        birthdayPeople.forEach(person -> log.info(
        			"SMS sent to: {}",
        			person.phoneNumber()
    		));
        
   	 
    }

	// helper
	private boolean isBirthday(Person person, LocalDate currentDate) {

		LocalDate birthDate = person.birthDate();

		// caso standard
		if (birthDate.getMonth() == currentDate.getMonth()
				&& birthDate.getDayOfMonth() == currentDate.getDayOfMonth()) {
			return true;
		}

		// caso corner (compleanno 29/02 anno bisestile)
		return birthDate.getMonthValue() == 2 
				&& birthDate.getDayOfMonth() == 29 
				&& !currentDate.isLeapYear()
				&& currentDate.getMonthValue() == 2 
				&& currentDate.getDayOfMonth() == 28;
	}

}