package stardewvalley;

import java.io.*;
import java.util.Scanner;

public class ResourceAccess {

    //Once used it's not necessary.
    public void callIn() throws Exception {
        // put your fileName in the place of this comment and use it in both StardewValleyLogin and File constructors
        StardewValleyLogin localStardewValley = new StardewValleyLogin("archivoUsuarios.txt");
        // this file name is not exactly right. Here, you should work with resources rather than files, since when
        // you package your game into a jar, it no longer will be there
        String fileName = "archivoUsuarios.txt";
        File file = new File(fileName);
        if (file.exists()) {
            System.out.println("Desea crear usuario(escriba 1) o lista de usuarios(escriba 2) o eliminar usuario(escriba 3) o login usuario(escriba 4)");
            Scanner login_register = new Scanner(System.in);
            String newContact = login_register.next();
            // this was your repeated code, and even if you fix it, it wouldnt be good, cause you flipped it upside down
            /*if (!newContact.equals("1") && !newContact.equals("2") && !newContact.equals("3") && !newContact.equals("4")) {
                throw new IllegalArgumentException("Wrong input parameter");*/
            // i simplified it for you. Be attentive

            // if you are taking only numbers, consider using int instead of String, and switch statement instead of
            // else if structure
            if (newContact.equals("1")) {
                //add user
                localStardewValley.add();
            } else if (newContact.equals("2")) {
                // list of users
                localStardewValley.list();
            } else if (newContact.equals("3")) {
                //remove user
                System.out.println("Qué usuario desea eliminar de la lista: ");
                localStardewValley.list();
                Scanner deleteuserinput = new Scanner(System.in);
                String user = deleteuserinput.next();
                localStardewValley.remove(user);
            } else if (newContact.equals("4")) {
                //login
                Scanner deleteuserinput = new Scanner(System.in);
                String user = deleteuserinput.next();
                localStardewValley.login(user);
            } else {
                throw new IllegalArgumentException("Wrong input parameter");
            }
        } else {
            localStardewValley.create();
        }


    }
}
// for review
