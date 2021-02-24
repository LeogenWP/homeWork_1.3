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


    public void getString(BufferedReader reader, String LABELS){
        String command;
        while(true){
            System.out.println("You are in a Label menu");
            System.out.println("Type one of  next commands: create, read,update,findall");
            System.out.println("For example type \"create\"  if you want to create a new Label");
            System.out.println("Type \"return\"  if you want to return");
            try {
                command = reader.readLine();
                if(command.equals("return")){
                    System.out.println("Returning to main menu");
                    return;
                }else if(command.equals("create")){
                    System.out.println("Please type a name for a label");
                    System.out.println("If you want to return type: return");
                    String labelName = reader.readLine();
                    if(labelName.equals("return")){
                        return;
                    }else if (labelName.isEmpty()){
                        System.out.println("sorry, invalid name");
                        return;
                    }else{
                     labelController.save(labelName);
                    }
                }else if (command.equals("findall")){
                    labelController.findAll();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
