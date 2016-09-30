package util;

/**
 * Created by Oceanos on 30.09.2016.
 */
public class Utills {
    public static String millisToTime(long millis){
        long second = (millis / 1000) % 60;
        long minute = (millis / (1000 * 60)) % 60;
        long hour = (millis / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d:%d", hour, minute, second);
    }
}
