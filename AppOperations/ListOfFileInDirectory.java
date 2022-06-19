package AppOperations;

import java.io.File;
import java.io.IOException;

public class ListOfFileInDirectory {
    String filename;

    public ListOfFileInDirectory(String filename) {
        this.filename = filename;
    }

    public void display() {
        //Creating a File object for directory
        File directoryPath = new File(filename);
        //List of all files and directories
        String contents[] = directoryPath.list();
        System.out.println("List of files and directories in the specified directory:");

        for(int i = 0; i < contents.length; i++) {
            System.out.println(contents[i]);
        }
    }
}
