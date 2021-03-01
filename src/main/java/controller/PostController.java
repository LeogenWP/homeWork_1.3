package controller;

import model.Label;
import model.Post;
import repository.JavaIOPostRepository;

public class PostController {
    private JavaIOPostRepository postRepository;

    public PostController(){
        postRepository = new JavaIOPostRepository();
    }

    public void save (String postContent) {
        Post post = postRepository.save(new Post(postContent));
        postRepository.writeToFile(post);
    }

    public void getAll() {
        for (Post post : postRepository.getAll()) {
            System.out.println("Post content  " + post.getContent() );
        }
    }

}
