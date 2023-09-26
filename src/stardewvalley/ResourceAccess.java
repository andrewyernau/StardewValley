package stardewvalley;

import java.io.*;
import java.util.Scanner;

public class ResourceAccess {


    //Once used it's not necessary.
    public void callIn() throws Exception {
        StardewValleyLogin localStardewValley = new StardewValleyLogin("archivoUsuarios.txt");
        String fileName = "archivoUsuarios.txt";
        File file = new File(fileName);
        if (file.exists()) {
            System.out.println("Would you like to create user(1), check the users list(2),delete user(3) or login user(4)");
            Scanner scanner = new Scanner(System.in);
            int newContact = scanner.nextInt();
            switch (newContact) {
                case 1:
                    //add user
                    localStardewValley.add();
                case 2:
                    // list of users
                    localStardewValley.list();
                case 3:
                    //remove user
                    System.out.println("What username would you like to delete: ");
                    localStardewValley.list();

                    String user = scanner.next();
                    localStardewValley.remove(user);
                case 4:
                    localStardewValley.list();
                    //login

                    String userlog = scanner.next();
                    localStardewValley.login(userlog);
                default:
                    throw new IllegalArgumentException("Wrong input parameter.");
            }
        } else {
            localStardewValley.create();
        }
    }
}
