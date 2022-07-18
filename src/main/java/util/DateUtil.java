package util;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtil {
    public static Boolean checkDateStringFormat(String s) {
        try {
            //creating the instance of LocalDate using the day, month, year info
            LocalDate.parse(s);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static Date convertStringToDate(String s) {
        //default time zone
        ZoneId defaultZoneId = ZoneId.systemDefault();

        //creating the instance of LocalDate using the day, month, year info
        LocalDate localDate = LocalDate.parse(s);

        //local date + atStartOfDay() + default time zone + toInstant() = Date
        return Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
    }

    public static LocalDate convertTypeDateToLocalDate(Date date){
        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }
    public static Integer getYear(Date date){
        String dateToString = date.toString();
        return Integer.parseInt(dateToString.substring(dateToString.length() - 4));
    }

    public static long calculateAgeByTotalDays(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return ChronoUnit.DAYS.between(birthDate, currentDate);
    }

    public static long calculateAgeByTotalYears(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

}