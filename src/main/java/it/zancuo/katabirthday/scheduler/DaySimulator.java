package it.zancuo.katabirthday.scheduler;

import it.zancuo.katabirthday.service.BirthdayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class DaySimulator {

    private static final LocalDate START_DATE = LocalDate.of(2026, 1, 1);
    private static final LocalDate END_DATE = LocalDate.of(2026, 12, 31);

    private final BirthdayService birthdayService;

    private LocalDate simulatedDate = START_DATE;

    @Scheduled(fixedRate = 100, initialDelay = 1000)
    public void simulateNextDay() {

        if (simulatedDate.isAfter(END_DATE)) {
            return;
        }

        log.info("------------------------------");
        log.info("Data simulata: {}", simulatedDate);

        birthdayService.processBirthdays(simulatedDate);

        if (simulatedDate.equals(END_DATE)) {

            log.info("------------------------------");
            log.info("Simulazione anno completata.");
        }

        simulatedDate = simulatedDate.plusDays(1);
    }
 
    
}