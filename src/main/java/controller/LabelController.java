package controller;

import model.Label;
import repository.JavaIOLabelRepository;

import java.io.BufferedReader;
import java.io.IOException;

public class LabelController {
    JavaIOLabelRepository labelRepository;

    public void save (String labelName) {
        labelRepository.save(new Label(labelName));
        System.out.println("Label has been created. Label name is: " + labelName);
    }
    public void findAll(){
        labelRepository.findAll();
    }

    public LabelController() {
        this.labelRepository = new JavaIOLabelRepository();
    }
}
