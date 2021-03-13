package repository.io;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Label;
import repository.LabelRepository;

public class JavaIOLabelRepository  implements LabelRepository {
    private final String LABELSTXT = "C:/JavaProjects/homeWork_1.3/src/main/resources/labels.txt";

    @Override
    public List<Label> getAll() {
        return getAllLabels();
    }

    @Override
    public Label save(Label label) {
        label.setId(calculateId());
        writeToFile(label);
        return label;
    }

    @Override
    public Label getById(Integer id) {
        File file = new File(LABELSTXT);
       List<Label> list = new ArrayList<>();
        StringBuffer inputBuffer = new StringBuffer();
        try(Stream<String> linesStream = Files.lines(file.toPath())) {
            Label  label = linesStream.map(s -> makeLabel(s)).filter( lab -> lab.getId() == id).findAny().orElse(null);
            list.add(label);
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return list.get(0);
    }

    @Override
    public Label update(Label label) {
        List<Label> labels = getAllLabels();
        for (int i = 0; i < labels.size(); i++) {
            if (labels.get(i).getId() == label.getId()) {
                labels.set(i, label);
                break;
            }
        }
        writeToFile(labels, true);
        return label;
    }


    @Override
    public void deleteById(Integer id) {
        List<Label> labels = getAllLabels();
        for (int i =0; i < labels.size(); i++) {
            if (id == (Integer) labels.get(i).getId()) {
                System.out.println("deleting");
                labels.remove(i);
                break;
            }
        }
        writeToFile(labels,true);
    }

    private  void writeToFile(Label label) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LABELSTXT, true))) {
            writer.write(label.getId() + ";"+ label.getName() +"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //labels.txt content example
    //1;Chapter1
    //2;Chapter2
    private List<String> split (String str, String regex){
        return Stream.of(str.split(regex))
                .map(elem -> new String(elem))
                .collect(Collectors.toList());
    }

    private  void writeToFile(List<Label> labels, boolean rewriteFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LABELSTXT, !rewriteFile))) {
            for (Label label : labels) {
                writer.write(label.getId() + ";"+ label.getName() +"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Integer calculateId() {
        Integer id = 0;
        File file = new File(LABELSTXT);
        try (Stream<String> linesStream = Files.lines(file.toPath())) {
            List<Integer> list = new ArrayList<>();
            linesStream.forEach(line -> {
                list.add(Integer.parseInt(split(line, ";").get(0)));
            });
             id = list.stream().reduce(0, (left, right) -> left < right ? right : left);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ++id;
    }

    private List<Label> getAllLabels(){
        List<Label> labels = new ArrayList();
        File file = new File(LABELSTXT);
        try (Stream<String> linesStream = Files.lines(file.toPath())) {
            labels = linesStream.map(s -> makeLabel(s)).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return labels;
    }

    private  Label makeLabel(String s) {
        Label label = new Label(Integer.parseInt(split(s,";").get(0) ),split(s,";").get(1));
        return label;
    }

}
