package sample.models;

import java.sql.Date;

public class Student {
    int id;
    String name;
    Date date;
    int group;
    boolean special;
    boolean chummery;



    public Student(int id, String name, Date date, int group, boolean special, boolean chummery) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.group = group;
        this.special = special;
        this.chummery = chummery;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public int getGroup() {
        return group;
    }

    public boolean isSpecial() {
        return special;
    }

    public boolean isChummery() {
        return chummery;
    }
}
