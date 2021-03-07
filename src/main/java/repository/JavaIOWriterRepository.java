package repository;

import model.Post;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import model.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaIOWriterRepository implements WriterRepository<Writer,Integer>{
    private static final String WRITERSTXT = "C:/JavaProjects/homeWork_1.3/src/main/resources/writers.txt";

    @Override
    public List<Writer> getAll() {
        return getAllWriters();
    }

    @Override
    public Writer save(Writer writer) {
        writer.setId(calculateId());
        return writer;
    }

    @Override
    public Writer getById(Integer id) {
        for (Writer writer : getAllWriters()) {
            if (writer.getId() == id) {
                return writer;
            }
        }
        return null;
    }

    @Override
    public Writer update(Writer writer) {
        List<Writer> writers = getAllWriters();
        for(int i = 0; i < writers.size(); i ++) {
            if(writer.getId() == writers.get(i).getId()) {
                writers.set(i, writer);
                break;
            }
        }
        writeToFile(writers);
        return writer;
    }




    @Override
    public void deleteById(Integer id) {
        List<Writer> writers = getAllWriters();
        for (int i = 0; i < writers.size(); i ++) {
            if(writers.get(i).getId() == id) {
                writers.remove(i);
                break;
            }
        }
        writeToFile(writers);

    }

    public  void writeToFile(Writer writer) {
        try (BufferedWriter bufWriter = new BufferedWriter(new FileWriter(WRITERSTXT, true))) {
            bufWriter.write(writer.getId() + ";" + writer.getFirstName() +
                    ";" + writer.getLastName() + ";" + getPostsID(writer) + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(List<Writer> writers) {
        try (BufferedWriter bufWriter = new BufferedWriter(new FileWriter(WRITERSTXT, false))) {
            for (Writer writer : writers) {
                bufWriter.write(writer.getId() + ";" + writer.getFirstName() +
                        ";" + writer.getLastName() + ";" + getPostsID(writer) + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getPostsID(Writer writer) {
        String labelsId = "";
        for(Post post : writer.getPosts()){
            labelsId += post.getId() + ",";
        }
        return labelsId;
    }

    private List<Post> getPosts(String string) {
        List<Post> writerPosts = new ArrayList<>();
        if(string.isEmpty()){
            return writerPosts;
        } else {
            JavaIOPostRepository  postRepository = new JavaIOPostRepository();
            List<Post> allPosts = postRepository.getAll();
            List<String> postLabelsID = split(string, ",");
            for (int i = 0; i < postLabelsID.size(); i++) {
                for(int k = 0; k < allPosts.size(); k++) {
                    if (Integer.parseInt(postLabelsID.get(i)) == allPosts.get(k).getId()) {
                        writerPosts.add(allPosts.get(k));
                    }
                }
            }
            return writerPosts;
        }
    }

    private int calculateId() {
        int id = 1;
        File file = new File(WRITERSTXT);
        try (Stream<String> linesStream = Files.lines(file.toPath())) {
            List<Integer> list = new ArrayList<>();
            linesStream.forEach(line -> {
                list.add(Integer.parseInt(split(line,";").get(0)));
            });
            Stream<Integer> myStream = list.stream();
            Optional<Integer> maxVal = myStream.max(Integer::compare);
            if(maxVal.isPresent()){
                id = maxVal.get() + 1;
            }else{
                id = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    //id;content;created;updated;1,2,3,4,5;postStatus
    private  List<String> split(String str, String regex) {
        return Stream.of(str.split(regex))
                .map (elem -> new String(elem))
                .collect(Collectors.toList());
    }

    private List<Writer> getAllWriters() {
        List <Writer> writers = new ArrayList();
        File file = new File(WRITERSTXT);
        try (Stream<String> linesStream = Files.lines(file.toPath())) {
            linesStream.forEach(line -> {
                writers.add(new Writer(split(line, ";").get(0),split(line, ";").get(1),split(line, ";").get(2),getPosts(split(line, ";").get(3))));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writers;
    }
}
