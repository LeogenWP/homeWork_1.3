package repository;

import model.Post;

public interface PostRepository<Post,Integer> extends GenericRepository<Post,Integer> {
    String getLabelsID (Post post);
}
