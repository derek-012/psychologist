package sample.models;

public class Group {
    int id;
    String name;
    int year;
    String curator;

    public Group(int id, String name, int year, String curator) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.curator = curator;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getCurator() {
        return curator;
    }
}
