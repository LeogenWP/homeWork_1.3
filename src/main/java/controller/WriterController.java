package controller;

import model.Writer;
import repository.JavaIOPostRepository;
import repository.JavaIOWriterRepository;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.List;

public class WriterController {
    JavaIOWriterRepository writerRepository;

    public WriterController() {
        writerRepository = new JavaIOWriterRepository();
    }

    public void save (String firstName, String lastName) {
        Writer writer = writerRepository.save(new Writer(firstName, lastName));
        writerRepository.writeToFile(writer);
    }

    public void getAll() {
        for (Writer writer : writerRepository.getAll()) {
            System.out.println(writer.getId() + ";" + writer.getFirstName() +
                    ";" + writer.getLastName() + ";" +
                    writerRepository.getPostsID(writer));
        }
    }

    public void deleteById( Integer id) {
        writerRepository.deleteById(id);

    }

    public void getById(Integer id) {
        try {
            Writer writer = writerRepository.getById(id);
            System.out.println(writer.getId() + ";" + writer.getFirstName() +
                    ";" + writer.getLastName() + ";" +
                    writerRepository.getPostsID(writer));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateById(BufferedReader reader) {
        JavaIOPostRepository postRepository = new JavaIOPostRepository();
        System.out.println("Next options are available for Writer: \n");
        System.out.println("type firsname for updating firs name");
        System.out.println("type lastname for updating last name");
        System.out.println("type addpost for adding post");
        System.out.println("type removepost for deleting post");
        System.out.println("type return for returning");
        System.out.println("type save for saving changes");
        String string;
        String postId;

        try {
            postId = reader.readLine();
            Writer writer = writerRepository.getById(Integer.parseInt(postId));
            while (true) {
                string = reader.readLine();
                if (string.equals("return")) {
                    return;
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
                    List<Writer> writers =  writerRepository.getAll();
                    for(int i = 0; i < writers.size(); i ++) {
                        if(writer.getId() == writers.get(i).getId()) {
                            writers.set(i, writer);
                            break;
                        }
                    }
                    writerRepository.writeToFile(writers);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
