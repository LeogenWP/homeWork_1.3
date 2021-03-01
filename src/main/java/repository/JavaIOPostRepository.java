package repository;

import model.Label;
import model.Post;
import model.PostStatus;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaIOPostRepository implements PostRepository<Post,Integer> {
    private static final String POSTSTXT = "C:/JavaProjects/homeWork_1.3/src/main/resources/posts.txt";
    private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date now = new Date();
    String strDate = sdfDate.format(now);

    @Override
    public List<Post> getAll() {
        List <Post> posts = new ArrayList();
        File file = new File(POSTSTXT);
        try (Stream<String> linesStream = Files.lines(file.toPath())) {
            linesStream.forEach(line -> {
                posts.add(new Post(split(line, ";").get(0),split(line, ";").get(1),split(line, ";").get(2),split(line, ";").get(3), getLabels(split(line, ";").get(4)), PostStatus.valueOf(split(line, ";").get(5))));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post save(Post post) {
        post.setId(calculateId());
        Date now = new Date();
        String strDate = sdfDate.format(now);
        post.setCreated(strDate);
        post.setUpdated(strDate);
        post.setPostStatus(PostStatus.ACTIVE);
        return post;
    }

    @Override
    public Post getById(Integer integer) {
        return null;
    }

    @Override
    public Post updateById(Integer integer) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    public  void writeToFile(Post post) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(POSTSTXT, true))) {
            writer.write(post.getId() + ";" + post.getContent() +
                    ";" + post.getCreated() + ";" + post.getUpdated() + ";" +
                    getLabelsID(post) + ";" + post.getPostStatus() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getLabelsID(Post post) {
        String labelsId = "";
        for(Label label : post.getLabels()){
            labelsId +=label.getId() + ",";
        }
        return labelsId;
    }

    private List<Label> getLabels(String string) {
        List<Label> postLabels = new ArrayList<>();
        if(string.isEmpty()){
            return postLabels;
        } else {
            JavaIOLabelRepository  labelRepository = new JavaIOLabelRepository();
            List<Label> allLabels = labelRepository.getAll();
            List<String> postLabelsID = split(string, ",");
            for (int i = 0; i < postLabelsID.size(); i++) {
                for(int k = 0; k < allLabels.size(); k++) {
                    if (Integer.parseInt(postLabelsID.get(i)) == allLabels.get(k).getId()) {
                        postLabels.add(allLabels.get(k));
                    }
                }
            }
            return postLabels;
        }
    }

    private int calculateId() {
        int id = 1;
        File file = new File(POSTSTXT);
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
}
