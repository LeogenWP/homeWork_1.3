package repository;

import model.Post;

public interface PostRepository extends GenericRepository<Post,Integer> {
    String getLabelsID (Post post);
}
