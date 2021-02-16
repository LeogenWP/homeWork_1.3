package model;

import java.util.List;

public class Post {
    private static int postId = 1;
    private int id;
    private String content;
    private String created;
    private String updated;
    private List<Label> labels;
}
