package UserOperations;

import AppOperations.CreateNewFolders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class UploadPicture {

    // attributes
    String filename, currentUserEmail;
    final int MIN = 0, MAX = 100;

    // constructor
    public UploadPicture(String filename, String email) {
        this.filename = filename;
        this.currentUserEmail = email;
    }

    public void run() {
        try {
            Random rd = new Random();
            File file = new File(filename);

            File f = new File("UserList.txt");
            Scanner content = new Scanner(f);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String s;

            while((s = br.readLine()) != null) {
                String[] st = s.split(" ");
                String nm = st[0];
                String em = st[1];
                String ps = st[2];
                String udr = st[3];

                if(em.equals(currentUserEmail)){
                    System.out.println("Uploading........");

                    String newFileName = udr + "/pic" + rd.nextInt(101) + ".png";

                    CreateNewFolders cnf = new CreateNewFolders(udr);
                    cnf.run();

                    if (file.renameTo(new File(newFileName))) {
                        file.delete();
                        System.out.println("Image uploaded successfully");
                    } else {
                        System.out.println("Failed to upload the image");
                    }

                    // open picture straight away after uploading the pic
                    ViewPicture vp = new ViewPicture(newFileName);
                    vp.display();
                    vp.likeOrDislike();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
