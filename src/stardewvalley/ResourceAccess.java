package stardewvalley;

import java.io.*;
import java.util.Scanner;

public class ResourceAccess{


    //Once used it's not necessary.
    public void callIn() throws Exception {
        StardewValleyLogin localStardewValley= new StardewValleyLogin("archivoUsuarios.txt");
        String fileName="archivoUsuarios.txt";
        File file=new File(fileName);
        if(file.exists()){
            System.out.println("Desea crear usuario(escriba 1) o lista de usuarios(escriba 2) o eliminar usuario(escriba 3) o login usuario(escriba 4)");
            Scanner login_register= new Scanner(System.in);
            String newContact=login_register.next();
            if (!newContact.equals("1")&&!newContact.equals("2")&&!newContact.equals("3")&&!newContact.equals("4")) {
                throw new IllegalArgumentException("Wrong input parameter");

            }else {

                if(newContact.equals("1")){
                    //add user
                    localStardewValley.add();
                }
                else if (newContact.equals("2")) {
                    // list of users
                    localStardewValley.list();
                }
                else if (newContact.equals("3")){
                    //remove user
                    System.out.println("Qué usuario desea eliminar de la lista: ");
                    localStardewValley.list();
                    Scanner deleteuserinput= new Scanner(System.in);
                    String user=deleteuserinput.next();
                    localStardewValley.remove(user);
                }
                else {
                    //login
                    Scanner deleteuserinput= new Scanner(System.in);
                    String user=deleteuserinput.next();
                    localStardewValley.login(user);
                }
            }
        }
        else{
            localStardewValley.create();
        }


    }
}
