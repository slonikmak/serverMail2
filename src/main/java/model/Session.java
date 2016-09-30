package model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * Created by Oceanos on 30.09.2016.
 */
public class Session {
    private long id;
    private String name;
    private String type;
    private long time;
    private String date;

    public Session() {
    }

    public Session(long id, String name, String type, long time, String date) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.time = time;
        this.date = date;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                ", type:'" + type + '\'' +
                ", time:" + time +
                ", date:'" + date + '\'' +
                '}';
    }
}
