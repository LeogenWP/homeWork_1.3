package controller;

import model.Writer;
import repository.io.JavaIOPostRepository;
import repository.io.JavaIOWriterRepository;
import repository.WriterRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class WriterController {
    private final WriterRepository writerRepository;
    private final String updateText = "Next options are available for Writer: \n"
            + "type firsname for updating firs name\n"
            + "type lastname for updating last name\n"
            + "type addpost for adding post\n"
            + "type removepost for deleting post\n"
            + "type return for returning\n"
            + "type save for saving changes";

    public WriterController() {
        writerRepository = new JavaIOWriterRepository();
    }

    public Writer save (String firstName, String lastName) {
        return writerRepository.save(new Writer(firstName, lastName));
    }

    public List<Writer> getAll() {
        for (Writer writer : writerRepository.getAll()) {
            System.out.println(writer.getId() + ";" + writer.getFirstName() +
                    ";" + writer.getLastName() + ";" +
                    writerRepository.getPostsID(writer));
        }
        return writerRepository.getAll();
    }

    public void deleteById( Integer id) {
        writerRepository.deleteById(id);

    }

    public Writer getById(Integer id) {
        try {
            Writer writer = writerRepository.getById(id);
            System.out.println(writer.getId() + ";" + writer.getFirstName() +
                    ";" + writer.getLastName() + ";" +
                    writerRepository.getPostsID(writer));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writerRepository.getById(id);
    }

    public Writer updateById(BufferedReader reader) {
        JavaIOPostRepository postRepository = new JavaIOPostRepository();
        System.out.println(updateText);
        String string;
        String postId;

        try {
            postId = reader.readLine();
            Writer writer = writerRepository.getById(Integer.parseInt(postId));
            while (true) {
                string = reader.readLine();
                if (string.equals("return")) {
                    return writer;
                } else if (string.equals("firsname")) {
                    System.out.println("Type new firsname");
                    writer.setFirstName(reader.readLine());
                } else if (string.equals("lastname")) {
                    System.out.println("Type new last name");
                        writer.setLastName(reader.readLine());
                } else if (string.equals("removepost")) {
                    System.out.println("write post id that you want to remove");
                    writer.removePost(Integer.parseInt(reader.readLine()));
                } else if (string.equals("addpost")) {
                    System.out.println("write post id that you want to add");
                   writer.addPost(postRepository.getById(Integer.parseInt(reader.readLine())));
                } else if (string.equals("save")) {
                    return writerRepository.update(writer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
