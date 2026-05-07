package it.zancuo.katabirthday.repository;

import it.zancuo.katabirthday.model.Person;
import java.util.List;

public interface BirthdayRepository {

    List<Person> findAll();
}