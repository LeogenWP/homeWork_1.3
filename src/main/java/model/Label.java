package model;

public class Label {
    private static int labelId = 1;
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Label setName(String name) {
        this.name = name;
        return this;
    }

    public Label(String name) {
        this.id = labelId;
        this.name = name;
        labelId++;
    }
}
