package view;

import controller.LabelController;
import model.Label;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Map;

public class LabelView {
    LabelController labelController;

    public LabelView() {
        labelController = new LabelController();
    }


    public void getString(BufferedReader reader, String LABELS) {
        String command;
        while (true) {
            System.out.println("You are in a Label menu");
            System.out.println("Type one of  next commands: save,update,getall,delete,getbyid");
            System.out.println("For example type \"create\"  if you want to create a new Label");
            System.out.println("Type \"return\"  if you want to return");
            try {
                command = reader.readLine();
                if (command.equals("return")) {
                    System.out.println("Returning to main menu");
                    return;
                } else if (command.equals("save")) {
                    System.out.println("Please type a name for a label");
                    System.out.println("If you want to return type: return");
                    String labelName = reader.readLine();
                    if (labelName.equals("return")) {
                        return;
                    } else if (labelName.isEmpty()) {
                        System.out.println("sorry, invalid name");
                        return;
                    } else {
                     labelController.save(labelName);
                        System.out.println("Label has been saved");
                    }
                } else if (command.equals("getall")) {
                    labelController.getAll();
                } else if (command.equals("delete")) {
                    System.out.println("Please, write the ID of the label which should be deleted");
                    labelController.deleteById(Integer.parseInt(reader.readLine()));
                    System.out.println("Label has been deleted");
                } else if (command.equals("getbyid")) {
                    System.out.println("Please, write the ID of the label which should be found");
                    labelController.getById(Integer.parseInt(reader.readLine()));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
