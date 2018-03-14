import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class RandomGeneratorTest {
    @Test
    public void getIntegerFromString() {
        //given
        RandomsGenerator uut = new RandomsGenerator();
        String range = "1:20";

        //when
        int randomValue = uut.getIntegerFromString(range);

        //then
        Assert.assertTrue(1 <= randomValue && randomValue <= 20);
    }

    @Test
    public void getIntegerFromRange() {
        //given
        RandomsGenerator uut = new RandomsGenerator();
        int range_start = 333;
        int range_end = 451;
        //when
        int randomValue = uut.getInteger(333, 451);

        //then
        Assert.assertTrue(333 <= randomValue && randomValue <= 451);
    }

    @Test
    public void getIntegerFromBadRange() {
        //given
        RandomsGenerator uut = new RandomsGenerator();
        int range_start = 333;
        int range_end = 334;
        //when
        int randomValue = uut.getInteger(333, 334);
        //then
        Assert.assertTrue(333 <= randomValue && randomValue <= 334);
    }

    @Test
    public void getRandomDate() {
        //given
        String date = "2018-03-08T00:00:00.000-0100:2018-03-08T23:59:59.999-0100";
        RandomsGenerator uut = new RandomsGenerator();
        LocalDateTime start = LocalDateTime.of(LocalDate.of(2018, 03, 8),
                LocalTime.of(0,0,0,0));
        LocalDateTime end = LocalDateTime.of(LocalDate.of(2018, 03, 8), LocalTime.of(23,59,59,999));
        //when
        LocalDateTime randomDate1 = uut.getTimestamp(date);
        LocalDateTime randomDate2 = uut.getTimestamp(date);
        //then
        Assert.assertTrue(start.isBefore(randomDate1) && end.isAfter(randomDate1));
        Assert.assertFalse(randomDate1.equals(randomDate2));
    }

    @Test
    public void getRandomDateStartOrEnd() {
        //given
        String date = "2018-03-08T00:00:00.000-0100:2018-03-09T23:59:59.999-0100";
        RandomsGenerator uut = new RandomsGenerator();
        LocalDateTime start = LocalDateTime.of(LocalDate.of(2018, 03, 8),
                LocalTime.of(0,0,0,0));
        LocalDateTime end = LocalDateTime.of(LocalDate.of(2018, 03, 9), LocalTime.of(23,59,59,999));
        //when
        LocalDateTime randomDate1 = uut.getTimestamp(date);
        LocalDateTime randomDate2 = uut.getTimestamp(date);
        //then
        Assert.assertTrue(start.isBefore(randomDate1) && end.isAfter(randomDate1));
        Assert.assertFalse(randomDate1.equals(randomDate2));
    }

    @Test
    public void getRandomDateBetween() {
        //given
        String date = "2018-03-08T00:00:00.000-0100:2018-04-25T23:59:59.999-0100";
        RandomsGenerator uut = new RandomsGenerator();
        LocalDateTime start = LocalDateTime.of(LocalDate.of(2018, 03, 8),
                LocalTime.of(0,0,0,0));
        LocalDateTime end = LocalDateTime.of(LocalDate.of(2018, 04, 25), LocalTime.of(23,59,59,999));
        //when
        LocalDateTime randomDate1 = uut.getTimestamp(date);
        LocalDateTime randomDate2 = uut.getTimestamp(date);
        //then
        Assert.assertTrue(start.isBefore(randomDate1) && end.isAfter(randomDate1));
        Assert.assertFalse(randomDate1.equals(randomDate2));
    }
}
