package controller;

import model.Label;
import repository.io.JavaIOLabelRepository;
import repository.LabelRepository;

import java.util.List;

public class LabelController {
    private final LabelRepository labelRepository;

    public Label save (String labelName) {
        return labelRepository.save(new Label(labelName));
    }
    public List<Label> getAll(){
        for (Label label : labelRepository.getAll()) {
            System.out.println("Label id: " + label.getId() + " Label name: " + label.getName());
        }
        return labelRepository.getAll();
    }

    public void deleteById(Integer id) {
        labelRepository.deleteById(id);
    }

    public Label getById(Integer id) {

       try {
           Label label = labelRepository.getById(id);
           System.out.println("Label id: " + label.getId() + " Label name: " + label.getName());
       } catch (NullPointerException e) {
           System.out.println("Label not found");
       }
       return labelRepository.getById(id);
    }

    public Label updateById(Integer id, String labelName) {
            Label label = labelRepository.getById(id);
            label.setName(labelName);
            return labelRepository.update(label);
    }

    public LabelController() {
        this.labelRepository = new JavaIOLabelRepository();
    }
}
