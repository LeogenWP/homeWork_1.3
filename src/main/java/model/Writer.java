package model;

import java.io.Serializable;
import java.util.List;

public class Writer implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private List<Post> posts;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Writer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Writer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Writer setPosts(List<Post> posts) {
        this.posts = posts;
        return this;
    }

    public Writer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
