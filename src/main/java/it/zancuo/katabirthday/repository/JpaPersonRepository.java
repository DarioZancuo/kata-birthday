package it.zancuo.katabirthday.repository;

import it.zancuo.katabirthday.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPersonRepository extends JpaRepository<PersonEntity, Long> {
	
}