package repository;

import model.Writer;

public interface WriterRepository extends GenericRepository<Writer,Integer>{
    String getPostsID(model.Writer writer);
}
