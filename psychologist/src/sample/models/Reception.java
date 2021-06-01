package sample.models;

import java.sql.Date;
import java.sql.Time;

public class Reception {
    int id;
    String groupName;
    String studentName;
    Date date;
    Time time;

    public Reception(){}

    public Reception(int id, String group, String student, Date date, Time time) {
        this.id = id;
        this.groupName = group;
        this.studentName = student;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getGroup() {
        return groupName;
    }

    public void setGroup(String group) {
        this.groupName = group;
    }

    public String getName() {
        return studentName;
    }

    public void setName(String name) {
        this.studentName = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
