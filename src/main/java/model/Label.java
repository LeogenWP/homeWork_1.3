package model;

public class Label  {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public Label setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Label setName(String name) {
        this.name = name;
        return this;
    }

    public Label(String name) {
        this.name = name;
    }

    public Label(String id, String name) {
        this.name = name;
        this.id = Integer.parseInt(id);
    }
}

