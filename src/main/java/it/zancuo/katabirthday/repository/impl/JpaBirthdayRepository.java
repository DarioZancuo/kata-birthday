package it.zancuo.katabirthday.repository.impl;

import it.zancuo.katabirthday.entity.PersonEntity;
import it.zancuo.katabirthday.model.Person;
import it.zancuo.katabirthday.repository.BirthdayRepository;
import it.zancuo.katabirthday.repository.JpaPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Profile("jpa")
@RequiredArgsConstructor
public class JpaBirthdayRepository implements BirthdayRepository {

    private final JpaPersonRepository jpaPersonRepository;

    @Override
    public List<Person> findAll() {
        return jpaPersonRepository.findAll()
                .stream()
                .map(this::toModel)
                .toList();
    }

    private Person toModel(PersonEntity entity) {
        return new Person(
                entity.getId(),
                entity.getSurname(),
                entity.getName(),
                entity.getBirthDate(),
                entity.getEmail());
    }
    
}