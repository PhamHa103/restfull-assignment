package util;

import java.util.Date;

public class DateUtil {
    public static Integer getYear(Date date){
        String dateToString = date.toString();
        return Integer.parseInt(dateToString.substring(dateToString.length() - 4));
    }
}