package repository;

import model.Writer;

public interface WriterRepository<Writer,Integer> extends GenericRepository<Writer,Integer>{
    String getPostsID(model.Writer writer);
}
