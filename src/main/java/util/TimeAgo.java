package util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeAgo {

    private static String[] outputStr = new String[]{"year", "month", "week", "day", "hour", "minute"};
    private static long[] minisArray = new long[]{
            365 * 24 * 60 * 60 * 1000L,
            30 * 24 * 60 * 60 * 1000L,
            7 * 24 * 60 * 60 * 1000L,
            24 * 60 * 60 * 1000L,
            60 * 60 * 1000L,
            60 * 1000L
    };

    public static String timeAgo(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse(dateString);
        long duration = System.currentTimeMillis() - date.getTime();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < minisArray.length - 1; i++) {
            long temp = duration / minisArray[i];
            if (temp > 0) {
                sb.append(temp)
                        .append(" ")
                        .append(outputStr[i])
                        .append(temp > 1 ? "s" : "")
                        .append(" ago");
                break;
            }
        }

        return sb.toString().isEmpty() ? "just now" : sb.toString();
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = simpleDateFormat.parse("2013-11-18 12:43:20");
        long startTime = System.currentTimeMillis();
        System.out.println(timeAgo(simpleDateFormat.format(start)));
        System.out.println("used: " + (System.currentTimeMillis() - startTime));
    }
}
