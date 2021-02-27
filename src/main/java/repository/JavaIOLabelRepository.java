package repository;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Label;

public class JavaIOLabelRepository  implements LabelRepository<Label,Integer> {
    private static final String LABELSTXT = "C:/JavaProjects/homeWork_1.3/src/main/resources/labels.txt";

    @Override
    public List<Label> getAll() {
        List<Label> labels = new ArrayList();
        File file = new File(LABELSTXT);
        try (Stream<String> linesStream = Files.lines(file.toPath())) {
            linesStream.forEach(line -> {
                labels.add(new Label(split(line).get(0),split(line).get(1)));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return labels;
    }

    @Override
    public Label save(Label label) {
        label.setId(calculateId());
        return label;
    }

    @Override
    public Label getById(Integer id) {

        List<Label> labels = getAll();
        for (Label label : labels)
            if (id == label.getId()) {
                return label;
            }
        return  null;
    }

    @Override
    public Label updateById(Integer id) {
        List<Label> labels = getAll();
        for (Label label : labels)
            if (id == label.getId()) {
                return label;
            }
        return  null;
    }

    @Override
    public void deleteById(Integer id) {
        List<Label> labels = getAll();
        for (int i =0; i < labels.size(); i++) {
            if (id == (Integer) labels.get(i).getId()) {
                System.out.println("deleting");
                labels.remove(i);
                break;
            }
        }
        writeToFile(labels,true);
    }

    //labels.txt content example
    //1;Chapter1
    //2;Chapter2
    private  List<String> split(String str) {
        return Stream.of(str.split(";"))
                .map (elem -> new String(elem))
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

    public  void writeToFile(Label label) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LABELSTXT, true))) {
            writer.write(label.getId() + ";"+ label.getName() +"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int calculateId() {
        int id = 1;
        File file = new File(LABELSTXT);
        try (Stream<String> linesStream = Files.lines(file.toPath())) {
            List<Integer> list = new ArrayList<>();
            linesStream.forEach(line -> {
                list.add(Integer.parseInt(split(line).get(0)));
            });
            Stream<Integer> myStream = list.stream();
            Optional<Integer> maxVal = myStream.max(Integer::compare);
            if(maxVal.isPresent()){
                id = maxVal.get() + 1;
            }else{
                id = 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }



}
