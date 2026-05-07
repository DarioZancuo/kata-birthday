package it.zancuo.katabirthday;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import it.zancuo.katabirthday.model.Person;
import it.zancuo.katabirthday.repository.BirthdayRepository;
import it.zancuo.katabirthday.service.BirthdayServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BirthdayServiceImplTest {

    @Mock
    private BirthdayRepository birthdayR;

    @InjectMocks
    private BirthdayServiceImpl birthdayS;

    @Test
    void birthdayMatchTest() {

        LocalDate currentDate = LocalDate.of(2026, 5, 6);

        Person p1Test = new Person(null,
                				   "Zancuo",
                				   "Dario",
                				   LocalDate.of(1995, 5, 6),
                				   "dario.zancuo@mail.com"
        );

        when(birthdayR.findAll()).thenReturn(List.of(p1Test));

        List<Person> result = birthdayS.findBirthdays(currentDate);

        assertThat(result)
                .hasSize(1)
                .containsExactly(p1Test);
    }
   
    
}