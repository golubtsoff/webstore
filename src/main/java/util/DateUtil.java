package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Evgeniy Golubtsov on 25.02.2018.
 */
public abstract class DateUtil {

    public static String format(Date dateTime) {
        if (dateTime == null) return "";
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatForDateNow.format(dateTime);
    }
}
