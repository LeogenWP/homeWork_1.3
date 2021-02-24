package model;

import java.io.Serializable;
import java.util.List;

public class Post implements Serializable {
    private static int postId = 1;
    private int id;
    private String content;
    private String created;
    private String updated;
    private List<Label> labels;
    private PostStatus postStatus;

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Post setContent(String content) {
        this.content = content;
        return this;
    }

    public String getCreated() {
        return created;
    }

    public Post setCreated(String created) {
        this.created = created;
        return this;
    }

    public String getUpdated() {
        return updated;
    }

    public Post setUpdated(String updated) {
        this.updated = updated;
        return this;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public Post setLabels(List<Label> labels) {
        this.labels = labels;
        return this;
    }

    public Post(String content) {
        this.id = postId;
        this.content = content;
        postId++;
    }

    public PostStatus getPostStatus() {
        return postStatus;
    }

    public Post setPostStatus(PostStatus postStatus) {
        this.postStatus = postStatus;
        return this;
    }
}
