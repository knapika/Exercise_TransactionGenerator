import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class RandomsGenerator {
    int getInteger(int a, int b) {
        int randomNum = ThreadLocalRandom.current().nextInt(a, b);
        return randomNum;
    }

    LocalDateTime getTimestamp(String dateRange) {
        String[] range = dateRange.split("-0100");
        range[1] = range[1].substring(1);

        LocalDateTime startDate = changeStringToLocalDateTime(range[0]);
        LocalDateTime endDate = changeStringToLocalDateTime(range[1]);

        LocalDate randomDate = randomDate(startDate, endDate);
        LocalTime randomTime = randomTime(startDate, endDate, randomDate);
        return LocalDateTime.of(randomDate, randomTime);
    }

    private LocalDateTime changeStringToLocalDateTime(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime date = LocalDateTime.parse(s, formatter);
        return date;
    }

    private LocalDate randomDate(LocalDateTime start, LocalDateTime end) {
        int days = (int) Duration.between(start, end).toDays();
        int randomDaysInc =  ThreadLocalRandom.current().nextInt(Integer.valueOf(0), Integer.valueOf(days) + 1);
        return start.plusDays(randomDaysInc).toLocalDate();
    }

    private LocalTime randomTime(LocalDateTime start, LocalDateTime end, LocalDate newDate) {
        LocalTime newTime;
        int hour;
        int min;
        int sec;
        int nano;
        if(start.toLocalDate().equals(newDate) && end.toLocalDate().equals(newDate)) {
            hour = ThreadLocalRandom.current().nextInt(start.getHour() + 1, end.getHour());
        } else if(start.toLocalDate().equals(newDate) && !end.toLocalDate().equals(newDate)) {
            hour = ThreadLocalRandom.current().nextInt(start.getHour() + 1, 24);
        } else if(!start.toLocalDate().equals(newDate) && end.toLocalDate().equals(newDate)) {
            hour = ThreadLocalRandom.current().nextInt(0, end.getHour());
        } else {
            hour = ThreadLocalRandom.current().nextInt(0, 24);
        }
        min = ThreadLocalRandom.current().nextInt(0,60);
        sec = ThreadLocalRandom.current().nextInt(0,60);
        nano = ThreadLocalRandom.current().nextInt(999000000, 999999999);
        newTime = LocalTime.of(hour, min,(int) sec,nano);
        return newTime;
    }
}
