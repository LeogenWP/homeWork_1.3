package model;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Label  {
    private static int labelId ;
    private static boolean hasId =false;
    private int id;
    private String name;

    public static boolean isHasId() {
        return hasId;
    }

    public static void setHasId(boolean hasId) {
        Label.hasId = hasId;
    }

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
        System.out.println("isHasId  "  + isHasId());
        if(!hasId){
            String fileName = "C:/JavaProjects/homeWork_1.3/src/main/resources/labels.txt";
            File file = new File(fileName);
            try (Stream<String> linesStream = Files.lines(file.toPath())) {
                List<Integer> list = new ArrayList<>();
                linesStream.forEach(line -> {
                    System.out.println(line);
                    System.out.println("split get0   " + split(line).get(0));
                    list.add(Integer.parseInt(split(line).get(0)));
                });
                Stream<Integer> myStream = list.stream();
                Optional<Integer> maxVal = myStream.max(Integer::compare);
                if(maxVal.isPresent()){
                    labelId = maxVal.get() + 1;
                }else {
                    labelId = 1;
                }
                setHasId(true);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        this.name = name;
        this.id = labelId;
        labelId++;
    }

    public static List<String> split(String str){
        return Stream.of(str.split(";"))
                .map (elem -> new String(elem))
                .collect(Collectors.toList());
    }

}
