package controller;

import model.Post;
import model.PostStatus;
import repository.io.JavaIOLabelRepository;
import repository.io.JavaIOPostRepository;
import repository.PostRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PostController {
    private final PostRepository postRepository;
    private final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final String updateText = "Next options are available for Post: \n"
            + "type content for updating content\n"
            + "type addlabels for adding labels\n"
            + "type removelabels for deleting labels\n"
            + "type status for updating status\n"
            + "type return for returning\n"
            + "type save for saving changes\n";

    public PostController(){
        postRepository = new JavaIOPostRepository();
    }

    public Post save (String postContent) {
        return postRepository.save(new Post(postContent));
    }

    public List<Post> getAll() {
        for (Post post : postRepository.getAll()) {
            System.out.println(post.getId() + ";" + post.getContent() +
                    ";" + post.getCreated() + ";" + post.getUpdated() + ";" +
                    postRepository.getLabelsID(post) + ";" + post.getPostStatus() );
        }
        return postRepository.getAll();
    }

    public void deleteById( Integer id) {
        postRepository.deleteById(id);

    }

    public Post getById(Integer id) {
        try {
            Post post = postRepository.getById(id);
            System.out.println(post.getId() + ";" + post.getContent() +
                    ";" + post.getCreated() + ";" + post.getUpdated() + ";" +
                    postRepository.getLabelsID(post) + ";" + post.getPostStatus() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  postRepository.getById(id);
    }

    public Post updateById(BufferedReader reader) {
        JavaIOLabelRepository labelRepository = new JavaIOLabelRepository();
        System.out.println(updateText);
        String string;
        String postId;
        try {
            postId = reader.readLine();
            Post post = postRepository.getById(Integer.parseInt(postId));
            while (true) {
                string = reader.readLine();
                if (string.equals("return")) {
                    return post;
                } else if (string.equals("content")) {
                    System.out.println("Type new content");
                    post.setContent(reader.readLine());
                } else if (string.equals("addlabels")) {
                    System.out.println("add label id that you want to add");
                    post.addLabel(labelRepository.getById(Integer.parseInt(reader.readLine())));
                } else if (string.equals("removelabels")) {
                    System.out.println("add label id that you want to remove");
                    post.removeLabel(Integer.parseInt(reader.readLine()));
                } else if (string.equals("status")) {
                    System.out.println("type  active/review/deleted");
                    String status = reader.readLine();
                    if (status.equals("active")) {
                        post.setPostStatus(PostStatus.ACTIVE);
                    } else if (status.equals("review")) {
                        post.setPostStatus(PostStatus.UNDER_REVIEW);
                    } else if (status.equals("deleted")) {
                        post.setPostStatus(PostStatus.DELETED);
                    }
                } else if (string.equals("save")) {
                    Date now = new Date();
                    post.setUpdated(sdfDate.format(now));
                    return  postRepository.update(post);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
