package app;

import model.Label;
import model.Post;
import model.Writer;
import view.LabelView;
import view.PostView;
import view.WriterView;
import java.io.*;


public class Application {
    public static final String LABELS = "C:/JavaProjects/homeWork_1.3/src/main/resources/labels.txt";
    public static final String WRITERS = "C:/JavaProjects/homeWork_1.3/src/main/resources/writers.txt";
    public static final String POSTS = "C:/JavaProjects/homeWork_1.3/src/main/resources/posts.txt";

    public static void main(String[] args) {
        boolean exit = false;
        LabelView labelView = new LabelView();
        PostView  postView = new PostView();
        WriterView writerView = new WriterView();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(!exit){
            try {
                System.out.println("Hello, it is main menu");
                System.out.println("Available options: writer,post,label");
                System.out.println("If you want to finish program type: exit");
                String string = reader.readLine();
                if(string.equals("exit")){
                    exit = true;
                }else if(string.toUpperCase().equals("LABEL")){
                    System.out.println("label has been typed");
                    labelView.getString(reader,LABELS);

                }else if(string.toUpperCase().equals("POST")){
                    System.out.println("post has been typed");
                    postView.getString(reader);
                }else if(string.toUpperCase().equals("WRITER")){
                    System.out.println("writer has been typed");
                }else {
                    System.out.println("please type writer/post/label");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
