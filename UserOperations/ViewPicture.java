package UserOperations;

import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class ViewPicture {
    // attributes
    String filename;

    // constructor
    public ViewPicture() {
        filename = "";
    }

    public ViewPicture(String filename) {
        this.filename = filename;
    }

    public void display() {
        try {

            File file = new File(filename);

            //check if Desktop is supported by Platform or not
            if(!Desktop.isDesktopSupported())
            {
                System.out.println("not supported");
                return;
            }

            Desktop desktop = Desktop.getDesktop();

            //checks file exists or not
            if(file.exists())
                System.out.println("Opening the file.....");
                desktop.open(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void likeOrDislike() {
        Scanner sc = new Scanner(System.in);
        String like;

        System.out.print("Like or Dislike [L / D] : ");
        like = sc.nextLine();

        switch (like) {
            case "L":
                System.out.println("You like this picture");
                break;
            case "D":
                System.out.println("You dislike this picture");
                break;
            default:
                System.out.println("Wrong input");
        }
    }
}
