import AppOperations.CreateNewFolders;
import UserOperations.UploadPicture;

import java.io.BufferedReader;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class Menu {

    static Random rd = new Random();
    static String directory = "/Users/amirulasyraf/Documents/UM/Sem_4/FOP/Lab_Ans/G1_FOP_Project/src/";
    static String userDirectory = directory + "user" + rd.nextInt(101);
    static String currentUserEmail;

    private static void register() throws  IOException
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();
        currentUserEmail = email;

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.print("Confirm password: ");
        String confirmPassword = sc.nextLine();

        name = name.trim();
        password = password.trim();
        confirmPassword = confirmPassword.trim();

        if(password.equals(confirmPassword))
        {
            File f = new File("UserList.txt");
            Scanner content = new Scanner(f);

            int flag = 0;
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String s;

            CreateNewFolders cnf = new CreateNewFolders(userDirectory);
            cnf.run();

            while((s = br.readLine()) != null) {
                String[] st = s.split(" ");
                String nm = st[0];
                String em = st[1];
                String ps = st[2];
                String udr = st[3];

                if(nm.equals(name) & (em.equals(em))){
                    flag = 1;
                    System.out.println("\nUSER ALREADY EXIST. PLEASE LOGIN.");
                    System.out.println("-------------------------------------------------");
                    flag = 1;

                    MenuOption();
                }
            }

            if (flag == 0) {
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter("UserList.txt", true));
                    out.write(name + " " + email.toLowerCase() + " " + password + " " + userDirectory + "\n");
                    out.close();
                }
                catch (IOException e) {
                    System.out.println("Exception occurred" + e);
                }

                System.out.println("\nSUCCESSFULLY REGISTERED.");
                System.out.println("PLEASE LOGIN");
                System.out.println("-------------------------------------------------");
                login();
            }
        } else {
            System.out.println("\n\nYOUR PASSWORD DID NOT MATCH. PLEASE TRY AGAIN.");
            System.out.println("-------------------------------------------------\n");

            MenuOption();
        }
        sc.close();
    }

    public static void login() throws IOException
    {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter email: ");
            String email = sc.nextLine();
            currentUserEmail = email;

            System.out.print("Enter password: ");
            String password = sc.nextLine();

            email = email.trim();
            password = password.trim();

            try {
                File f = new File("UserList.txt");
                try (Scanner content = new Scanner(f)) {
                    int flag = 0;

                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    String s;
//                    String checkEmail = email;

                    while((s = br.readLine()) != null)
                    {
                        String[] st = s.split(" ");
                        String name = st[0];
                        String em = st[1];
                        String ps = st[2];
                        String udr = st[3];

                        if(em.equals(email) & (ps.equals(password))){
                            flag = 1;
                           LoginMenuOption();
                        }

                        if(em.equals(email) & (!ps.equals(password)) | (!em.equals(email)&(ps.equals(password))))  {
                            System.out.println();
                            System.out.println("ACCOUNT NOT EXIST! PLEASE REGISTER FIRST.");
                            System.out.println("-------------------------------------------------");
                            System.out.println();

                            MenuOption();
                        }
                    }
                }
            } catch (FileNotFoundException e) {

                System.out.println("Error.");
            }
        }
    }

    public static void logout() throws IOException{

        Scanner sc = new Scanner(System.in);

        System.out.println("Are u sure want to logout?");
        System.out.println("A : Yes");
        System.out.println("B : No");
        String logout = sc.nextLine();

        if(logout.equals("A")) {
            System.out.println("You have been logout from Instant-Gram");
            System.out.flush();
        } else {
            MenuOption();
        }
    }

    public static void MenuOption() throws IOException {

        Scanner sc = new Scanner(System.in);

        System.out.println("--------------WELCOME TO INSTANT-GRAM------------\n");
        System.out.println("Instant-gram is a place where you can instantly make your own personal gallery like Instagram");
        System.out.println("It works only on IntelliJ :P\n");
        System.out.println("A. Register");
        System.out.println("B. Login");
        System.out.println("C. Wanna see Trending Pictures?");
        System.out.print("Select [A-C] : ");
        String choice = sc.nextLine();

        switch (choice) {
            case "A":
                register();
                break;
            case "B":
                login();
                break;
            case "C":
                // trending
                break;
            default:
                MenuOption();
        }
        sc.close();
    }

    public static void LoginMenuOption() throws IOException {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        System.out.println("--------------WELCOME TO INSTANT-GRAM------------\n");
        System.out.println("A. Upload Pictures");
        System.out.println("B. Wanna see Trending Pictures?");
        System.out.println("C. Change Password and Email");
        System.out.println("D. Logout\n");

        System.out.print("Select [A-D] : ");

        switch (input) {
            case "A":
                Scanner scanner = new Scanner(System.in);

                System.out.print("Enter a file name: ");
                System.out.flush();

                String filename = scanner.nextLine();

                UploadPicture up = new UploadPicture(filename, currentUserEmail);
                up.run();

                LoginMenuOption();

                break;
            case "B":
                System.out.println("Wanna see Trending Pictures?");
                break;
            case "C":
                ChangePassword();
                break;
            case "D":
                logout();
                break;
            default:
                LoginMenuOption();
        }
        sc.close();
    }

    public static void ChangePassword() throws IOException{

        String newPassword = " ";
        boolean checked = true;

        File f = new File("UserList.txt"); // path to your file
        File tempFile = new File("UserList2.txt"); // create a temp file in same path

        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        Scanner sc = new Scanner(f);

        System.out.print("Enter account username and email that you want to edit : ");
        Scanner sc2 = new Scanner(System.in);
        String username = sc2.next();

        while(sc.hasNextLine())
        {
            String currentLine = sc.nextLine();

            String[] tokens = currentLine.split(" ");
            if(Objects.equals(tokens[0], username) && checked)
            {
                sc2.nextLine();
                System.out.print("Enter your New Email (Space) and your New Password :");
                newPassword= sc2.nextLine();

                currentLine = tokens[0] + " " + newPassword;
                checked = false;
            }
            writer.write(currentLine + System.getProperty("line.separator"));

        }
        writer.close();
        sc.close();
        f.delete();
        boolean successful = tempFile.renameTo(f);
    }


    public static void main(String[] args) throws IOException{

        // create UserList.txt to store user credentials
        try {
            File obj = new File("UserList.txt");
            obj.createNewFile();

        }
        catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();

        }

        Menu user = new Menu();
        user.MenuOption();

    }

}
