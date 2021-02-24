package repository;

import java.util.Optional;

public interface GenericRepository<T,ID> {
    //Optional<T> findById(ID id);
    //void deleteById(ID id);
    //<S extends T> S update (S entity);

    void findAll();
    void save(T t);

}
