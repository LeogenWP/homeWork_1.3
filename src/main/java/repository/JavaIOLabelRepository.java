package repository;

import java.io.*;
import java.nio.file.Files;
import java.util.stream.Stream;

import model.Label;

public class JavaIOLabelRepository  implements LabelRepository<Label,Integer>{
    private static String fileName = "C:/JavaProjects/homeWork_1.3/src/main/resources/labels.txt";

    @Override
    public void findAll() {
        //String fileName = "C:/JavaProjects/homeWork_1.3/src/main/resources/labels.txt";
        File file = new File(fileName);
        System.out.println("Label.ID;Label.Name");
        try (Stream<String> linesStream = Files.lines(file.toPath())) {
            linesStream.forEach(line -> {
                System.out.println(line);

            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void save(Label label) {
        //String fileName = "C:/JavaProjects/homeWork_1.3/src/main/resources/labels.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(label.getId() + ";"+ label.getName() +"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
