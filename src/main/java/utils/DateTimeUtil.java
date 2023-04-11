package utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static String getCurrentDate() {
        String date = DateTimeFormatter.ofPattern("MM/dd/yyyy").format(LocalDate.now());
        return date;
    }

    public static String getCurrentTime() {
        String time = DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalTime.now());
        return time;
    }
}
