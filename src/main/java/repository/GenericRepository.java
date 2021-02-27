package repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T,ID> {
    List<T> getAll();
    T save(T t);
    T getById(ID id);
    T updateById(ID id);
    void deleteById(ID id);

}
