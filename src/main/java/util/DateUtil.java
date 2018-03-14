package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Evgeniy Golubtsov on 25.02.2018.
 */
public abstract class DateUtil {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String format(LocalDateTime dateTime) {
        if (dateTime == null) return "";
        return dateTime.format(DATE_FORMATTER);
    }
}
