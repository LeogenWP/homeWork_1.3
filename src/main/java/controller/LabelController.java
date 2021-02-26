package controller;

import model.Label;
import repository.JavaIOLabelRepository;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LabelController {
    private JavaIOLabelRepository labelRepository;
    private String fileName = "C:/JavaProjects/homeWork_1.3/src/main/resources/labels.txt";

    public void save (String labelName) {
        Label label = labelRepository.save(new Label(labelName));
        System.out.println("Label has been created. Label name is: " + labelName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(label.getId() + ";"+ label.getName() +"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getAll(){
        for (Label label : labelRepository.getAll()) {
            System.out.println("Label id: " + label.getId() + " Label name: " + label.getName());
        }
    }

    public void deleteById(Integer id) {
        labelRepository.deleteById(id);
    }

    public void getById(Integer id) {
       try {
           System.out.println("Label id: " + labelRepository.getById(id).getId() + " Label name: " + labelRepository.getById(id).getName());
       } catch (NullPointerException e) {
           System.out.println("Label not found");
       }
    }

    public void updateById(Integer id, String labelName) {
        try {
            Label label = labelRepository.getById(id);
            label.setName(labelName);
            labelRepository.deleteById(id);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                writer.write(label.getId() + ";"+ label.getName() +"\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LabelController() {
        this.labelRepository = new JavaIOLabelRepository();
    }
}
