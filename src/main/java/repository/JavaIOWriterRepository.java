package repository;

import java.io.Writer;
import java.util.List;
import java.util.Optional;

public class JavaIOWriterRepository implements WriterRepository<Writer,Integer>{

    @Override
    public List<Writer> getAll() {
        return null;
    }

    @Override
    public Writer save(Writer writer) {
        return null;
    }

    @Override
    public Writer getById(Integer integer) {
        return null;
    }

    @Override
    public Writer update(Writer writer) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
