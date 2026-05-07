package it.zancuo.katabirthday.model;

import java.time.LocalDate;

//per implementare messaggi si può aggiungere campo per numero di cellulare
public record Person(Long id, 
					 String surname, 
					 String name, 
					 LocalDate birthDate,
					 String email,
					 String phoneNumber) {
	
}