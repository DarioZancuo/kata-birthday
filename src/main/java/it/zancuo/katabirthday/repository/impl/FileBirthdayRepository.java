package it.zancuo.katabirthday.repository.impl;

import it.zancuo.katabirthday.model.Person;
import it.zancuo.katabirthday.repository.BirthdayRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
@Profile("file")
public class FileBirthdayRepository implements BirthdayRepository {

    private List<Person> cPeopleL;
    
    @Value("${birthday.file.path}")
    private Path filePath;

//    public FileBirthdayRepository(@Value("${birthday.file.path}") String filePath) {
//        this.filePath = Path.of(filePath);
//    }

    //eseguo subito il caricamento
    @PostConstruct
    public void init() {
    	cPeopleL = loadPeopleFromFile();
    }

    @Override
    public List<Person> findAll() {
        return cPeopleL;
    }

    //carico dal file.txt la lista di persone
    private List<Person> loadPeopleFromFile() {

        log.info("Caricando persone dal file: {}", filePath);

        try {

            List<Person> peopleL = Files.readAllLines(filePath)
                    .stream()
                    .filter(line -> !line.isBlank())
                    .map(this::toPersonOrNull)
                    .filter(Objects::nonNull)
                    .toList();

            log.info("Caricato {} persone dal file.", peopleL.size());

            return peopleL;

        } catch (IOException e) {
            throw new RuntimeException("Errore lettura file birthday.txt", e);
        }
    }

    //valido la riga 
    private Person toPersonOrNull(String line) {
    	
        //split 
        String[] parts = line.split("\\s*,\\s*");
        
        //controllo che lo split sia andato a buon fine
        if (parts.length != 5) {
            log.warn("Formato linea non valido: '{}'. Linea skippata.", line);
            return null;
        }
        
        try {
        	
        	return new Person(null,
        					  parts[0],
        					  parts[1],
        					  LocalDate.parse(parts[2]),
        					  parts[3],
        					  parts[4]);

        } catch (DateTimeParseException e) {

            log.warn("Data di nascita non valida: '{}'. Linea skippata.", line);
            return null;
        }      
    }
   
    
}