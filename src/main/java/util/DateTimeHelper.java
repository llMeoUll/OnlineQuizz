package util;

import java.sql.Timestamp;
import java.util.Date;

public class DateTimeHelper {
    public Timestamp convertUtilDateToTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }
}
