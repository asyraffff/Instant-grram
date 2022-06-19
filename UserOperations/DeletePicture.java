package UserOperations;

import java.io.File;

public class DeletePicture {

    public static void run(String filename) {
        File file = new File(filename);

        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
}