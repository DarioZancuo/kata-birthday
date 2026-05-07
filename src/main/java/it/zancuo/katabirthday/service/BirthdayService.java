package it.zancuo.katabirthday.service;

import java.time.LocalDate;
import java.util.List;
import it.zancuo.katabirthday.model.Person;

public interface BirthdayService {

	List<Person> findBirthdays(LocalDate currentDate);
	
    void processBirthdays(LocalDate currentDate);
}