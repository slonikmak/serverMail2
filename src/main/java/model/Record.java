package model;

import util.Utills;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Oceanos on 30.09.2016.
 */
public class Record {
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

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", time=" + time +
                ", date=" + date +
                ", sessionId=" + sessionId +
                '}';
    }
}
