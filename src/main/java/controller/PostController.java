package controller;

import model.Label;
import model.Post;
import model.PostStatus;
import repository.JavaIOLabelRepository;
import repository.JavaIOPostRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PostController {
    private JavaIOPostRepository postRepository;
    private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public PostController(){
        postRepository = new JavaIOPostRepository();
    }

    public void save (String postContent) {
        Post post = postRepository.save(new Post(postContent));
        postRepository.writeToFile(post);
    }

    public void getAll() {
        for (Post post : postRepository.getAll()) {
            System.out.println(post.getId() + ";" + post.getContent() +
                    ";" + post.getCreated() + ";" + post.getUpdated() + ";" +
                    postRepository.getLabelsID(post) + ";" + post.getPostStatus() );
        }
    }

    public void deleteById( Integer id) {
        postRepository.deleteById(id);

    }

    public void getById(Integer id) {
        try {
            Post post = postRepository.getById(id);
            System.out.println(post.getId() + ";" + post.getContent() +
                    ";" + post.getCreated() + ";" + post.getUpdated() + ";" +
                    postRepository.getLabelsID(post) + ";" + post.getPostStatus() );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateById(BufferedReader reader) {
        JavaIOLabelRepository labelRepository = new JavaIOLabelRepository();
        System.out.println("Next options are available for Post: \n");
        System.out.println("First ");
        System.out.println("type content for updating content");
        System.out.println("type addlabels for adding labels");
        System.out.println("type removelabels for deleting labels");
        System.out.println("type status for updating status");
        System.out.println("type return for returning");
        System.out.println("type save for saving changes");
        String string;
        String postId;

        try {
            postId = reader.readLine();
            Post post = postRepository.getById(Integer.parseInt(postId));
            while (true) {
                string = reader.readLine();
                if (string.equals("return")) {
                    return;
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
                    List<Post> posts =  postRepository.getAll();
                    for(int i = 0; i < posts.size(); i ++) {
                        if(post.getId() == posts.get(i).getId()) {
                            posts.set(i, post);
                            break;
                        }
                    }
                    postRepository.writeToFile(posts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
