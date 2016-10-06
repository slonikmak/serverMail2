package util;

import context.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

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

    public static double coordsConverter(double value){
        int degree = (int) (value/100);
        double minute = value%100;
        return degree + (minute/60);
    }

    public static void copyDb(String from, String to) throws IOException {
        Path pathTo = Paths.get(to);
        if (Files.exists(pathTo)) Files.delete(pathTo);
        Files.copy(Paths.get(from), pathTo);
        System.out.println("Copy from:"+from+" to: "+to);
    }

    public static void setProperties(String fileName) throws IOException {
        InputStream inputStream = Files.newInputStream(Paths.get(fileName));
        Properties properties = new Properties();

        properties.load(inputStream);
        Context.setDBPath(properties.getProperty("DBPath"));
        Context.setPublicHtmlPath(properties.getProperty("publicHtmlPath"));

    }
}
