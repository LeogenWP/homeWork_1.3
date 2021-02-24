package repository;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public interface WriterRepository<Writer,Integer> extends GenericRepository<Writer,Integer>{

    default void save(Writer writer){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:/JavaProjects/homeWork_1.3/src/main/resources/writers.txt")))
        {
            oos.writeObject(writer);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
