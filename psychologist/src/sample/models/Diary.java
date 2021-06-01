package sample.models;

import java.sql.Date;

public class Diary {
    int id;
    String student;
    String group;
    Date date;
    String notate;

    public Diary(int id, String student, String group, Date date, String notate) {
        this.id = id;
        this.student = student;
        this.group = group;
        this.date = date;
        this.notate = notate;
    }

    public int getId() {
        return id;
    }

    public String getStudent() {
        return student;
    }

    public String getGroup() {
        return group;
    }

    public Date getDate() {
        return date;
    }

    public String getNotate() {
        return notate;
    }
}
