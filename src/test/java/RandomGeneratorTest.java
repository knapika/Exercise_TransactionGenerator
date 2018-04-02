import generators.RandomsGenerator;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RandomGeneratorTest {
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
    public void changeStringToLocaleDateTime() {
        //given
        RandomsGenerator uut = new RandomsGenerator();
        String dateString = "2018-03-08T00:00:00.000";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Method method;
        LocalDateTime result = null;
        try {
            //when
            method = RandomsGenerator.class.getDeclaredMethod("changeStringToLocalDateTime", String.class);
            method.setAccessible(true);
            result = (LocalDateTime) method.invoke(uut,dateString);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        
        //then
        Assert.assertEquals(result, LocalDateTime.parse(dateString, formatter));

    }

    @Test
    public void randomDate() {
        //given
        RandomsGenerator uut = new RandomsGenerator();
        LocalDateTime start = LocalDateTime.of(LocalDate.of(2018, 03, 8),
                LocalTime.of(0,0,0,0));
        LocalDateTime end = LocalDateTime.of(LocalDate.of(2018, 03, 10),
                LocalTime.of(23,59,59,999));
        Method method;
        LocalDate result = null;
        Class[] params = new Class[]{LocalDateTime.class, LocalDateTime.class};
        //when
        try {
            method = RandomsGenerator.class.getDeclaredMethod("randomDate", params);
            method.setAccessible(true);
            result = (LocalDate) method.invoke(uut, start, end);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // then
        Boolean ifCorrect = (start.toLocalDate().isBefore(result) || start.toLocalDate().isEqual(result)) &&
                (end.toLocalDate().isAfter(result) || end.toLocalDate().isEqual(result));
        Assert.assertTrue(ifCorrect);
    }

    @Test
    public void randomTimeStartIsEndDay() {
        //given
        RandomsGenerator uut = new RandomsGenerator();
        LocalDateTime start = LocalDateTime.of(LocalDate.of(2018, 03, 8),
                LocalTime.of(0,0,0,0));
        LocalDateTime end = LocalDateTime.of(LocalDate.of(2018, 03, 8), LocalTime.of(23,59,59,999));
        LocalDate newDay = start.toLocalDate();
        Method method;
        LocalTime result = null;
        Class[] params = new Class[]{LocalDateTime.class, LocalDateTime.class, LocalDate.class};
        
        //when
        try {
            method = RandomsGenerator.class.getDeclaredMethod("randomTime", params);
            method.setAccessible(true);
            result = (LocalTime) method.invoke(uut, start, end, newDay);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        
        //then
        Assert.assertTrue(start.toLocalTime().isBefore(result) && result.isBefore(end.toLocalTime()));
    }

    @Test
    public void randomTimeStartIsNotEndDay() {
        //given
        RandomsGenerator uut = new RandomsGenerator();
        LocalDateTime start = LocalDateTime.of(LocalDate.of(2018, 03, 8),
                LocalTime.of(0,0,0,0));
        LocalDateTime end = LocalDateTime.of(LocalDate.of(2018, 03, 10), LocalTime.of(23,59,59,999));
        LocalDate newDay = LocalDate.of(2018,03,9);
        Method method;
        LocalTime result = null;
        Class[] params = new Class[]{LocalDateTime.class, LocalDateTime.class, LocalDate.class};

        //when
        try {
            method = RandomsGenerator.class.getDeclaredMethod("randomTime", params);
            method.setAccessible(true);
            result = (LocalTime) method.invoke(uut, start, end, newDay);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //then
        LocalDateTime newDate = LocalDateTime.of(newDay, result);
        Assert.assertTrue(start.isBefore(newDate) && newDate.isBefore(end));
    }

    @Test
    public void randomTimeNewDayIsStartDay() {
        //given
        RandomsGenerator uut = new RandomsGenerator();
        LocalDateTime start = LocalDateTime.of(LocalDate.of(2018, 03, 8),
                LocalTime.of(22,50,0,0));
        LocalDateTime end = LocalDateTime.of(LocalDate.of(2018, 03, 10), LocalTime.of(23,59,59,999));
        LocalDate newDay = LocalDate.of(2018,03,8);
        Method method;
        LocalTime result = null;
        Class[] params = new Class[]{LocalDateTime.class, LocalDateTime.class, LocalDate.class};

        //when
        try {
            method = RandomsGenerator.class.getDeclaredMethod("randomTime", params);
            method.setAccessible(true);
            result = (LocalTime) method.invoke(uut, start, end, newDay);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //then
        LocalDateTime newDate = LocalDateTime.of(newDay, result);
        Assert.assertTrue(start.isBefore(newDate) && newDate.isBefore(end));
    }

    @Test
    public void randomTimeNewDayIsEndDay() {
        //given
        RandomsGenerator uut = new RandomsGenerator();
        LocalDateTime start = LocalDateTime.of(LocalDate.of(2018, 03, 8),
                LocalTime.of(0,0,0,0));
        LocalDateTime end = LocalDateTime.of(LocalDate.of(2018, 03, 10), LocalTime.of(12,59,59,999));
        LocalDate newDay = LocalDate.of(2018,03,10);
        Method method;
        LocalTime result = null;
        Class[] params = new Class[]{LocalDateTime.class, LocalDateTime.class, LocalDate.class};

        //when
        try {
            method = RandomsGenerator.class.getDeclaredMethod("randomTime", params);
            method.setAccessible(true);
            result = (LocalTime) method.invoke(uut, start, end, newDay);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //then
        LocalDateTime newDate = LocalDateTime.of(newDay, result);
        Assert.assertTrue(start.isBefore(newDate) && newDate.isBefore(end));
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
}
