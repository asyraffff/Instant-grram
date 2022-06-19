package AppOperations;

import java.io.File;
import java.util.Scanner;

public class CreateNewFolders {
    String folder;

    public CreateNewFolders(String folder) {
        this.folder = folder;
    }

    public void run() {
        File file = new File(folder);

        //Creating a folder using mkdirs() method
        boolean bool2 = file.mkdirs();
        if(bool2) {
            System.out.println("Folder is created successfully");
        } else {
            System.out.println("Error Found!");
        }
    }
}
