package app;

import view.LabelView;
import view.PostView;
import view.WriterView;
import java.io.*;


public class Application {

    public static void main(String[] args) {
        boolean exit = false;
        LabelView labelView = new LabelView();
        PostView  postView = new PostView();
        WriterView writerView = new WriterView();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String greet = "Hello, it is main menu \n"
                + "Available options: writer,post,label\n"
                + "If you want to finish program type: exit";

        while(!exit){
            try {
                System.out.println(greet);

                String string = reader.readLine();
                if(string.equals("exit")){
                    exit = true;
                }else if(string.toUpperCase().equals("LABEL")){
                    System.out.println("label has been typed");
                    labelView.getString(reader);

                }else if(string.toUpperCase().equals("POST")){
                    System.out.println("post has been typed");
                    postView.getString(reader);
                }else if(string.toUpperCase().equals("WRITER")){
                    System.out.println("writer has been typed");
                    writerView.getString(reader);
                }else {
                    System.out.println("please type writer/post/label");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
