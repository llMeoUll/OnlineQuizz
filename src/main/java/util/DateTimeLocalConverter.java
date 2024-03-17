package util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeLocalConverter {
    public static Timestamp DateTimeLocalToTimestamp(String localDateTime) {
        // Parse the timestamp into LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(localDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        // Define the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Format the LocalDateTime into the desired format
        String formattedDateTime = dateTime.format(formatter);
        return Timestamp.valueOf(formattedDateTime);
    }
}
