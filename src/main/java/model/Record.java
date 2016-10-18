package model;

import util.Utills;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Oceanos on 30.09.2016.
 */
public class Record {
    public enum RecordType{
        MOTION("MOTION"), GPS("GPS"), DEPTH("DEPTH"), TEMP("TEMP"), BAT("BAT"), SERVO_P("SERVO_P"), SERVO_V("SERVO_V"), ALL("ALL");

        String name;

        RecordType(String name){
            this.name = name;
        }
    }

    private long id;
    private String name;
    private String value;
    private long time;
    private String date;
    private long sessionId;


    public Record() {
    }

    public Record(long id, String name, String value, long time, String date, long sessionId) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.time = time;
        this.date = date;
        this.sessionId = sessionId;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public double[] getCoords(){
        String[] values = Utills.prepareValue(value.split("__,__"));
        double[] coords = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            coords[i] = Double.parseDouble(values[i]);
        }
        return coords;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", value:'" + value + '\'' +
                ", time:" + time +
                ", date:" + date +
                ", sessionId:" + sessionId +
                '}';
    }
}
