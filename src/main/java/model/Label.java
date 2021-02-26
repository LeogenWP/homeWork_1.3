package model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Label  {
    private int id;
    private String name;
    private String fileName = "C:/JavaProjects/homeWork_1.3/src/main/resources/labels.txt";
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Label setName(String name) {
        this.name = name;
        return this;
    }

    public Label(String name) {
        this.name = name;
        this.id = calculateId();

    }

    public Label(String id, String name) {
        this.name = name;
        this.id = Integer.parseInt(id);
    }

    private  List<String> split(String str){
        return Stream.of(str.split(";"))
                .map (elem -> new String(elem))
                .collect(Collectors.toList());
    }

    private int calculateId() {
        int id = 1;
        File file = new File(fileName);
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

