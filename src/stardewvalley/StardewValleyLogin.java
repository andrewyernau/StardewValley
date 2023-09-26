package stardewvalley;

import java.io.*;
import java.util.Scanner;

public class StardewValleyLogin {
    private String filename;

    public StardewValleyLogin(String filename) {
        this.filename = filename;
    }

    //Once used it's not necessary.
    public void create() throws IOException, FileNotFoundException {
        ObjectOutputStream userArchive = new ObjectOutputStream(new FileOutputStream(this.filename));
        userArchive.close();
        System.out.println("File created correctly.Restart code.");
    }

    public void add() throws Exception {
        ObjectOutputStream AuxCopy = new ObjectOutputStream(new FileOutputStream("auxCopy.txt"));
        ObjectInputStream archive = new ObjectInputStream(new FileInputStream(this.filename));
        Scanner sc = new Scanner(System.in);
        String newContact = sc.next();
        boolean userExists = false;
        try {
            while (true) {
                String usuario = (String) archive.readObject();
                AuxCopy.writeObject(usuario);
                if (usuario.equals(newContact)) {
                    userExists = true;
                }
            }
        } catch (Exception e) {//when archive doesn't have the new user, starts to write the new user
            // Reopen the archive and reset position
            archive.close();
            archive = new ObjectInputStream(new FileInputStream(this.filename));
            // CHECK IF ALREADY EXISTS
            try {
                while (true) {
                    String checker = (String) archive.readObject();
                    if (checker.equals(newContact)) {
                        throw new IllegalArgumentException("User already exists");
                    }
                }
            } catch (Exception n) {
                //User does not exist. Continue
            }
            if (!userExists) {

                AuxCopy.writeObject(newContact);
                AuxCopy.close();
                archive.close();


                File fArchive = new File(this.filename);
                File fArchiveCopy = new File("auxCopy.txt");


                fArchive.delete();
                fArchiveCopy.renameTo(fArchive);
                System.out.println("Registered user");
                String userStringTxt = newContact.toString() + ".txt";
                ObjectOutputStream userFile = new ObjectOutputStream(new FileOutputStream(userStringTxt));
                userFile.close();

                System.out.println("Done!");
            } else {
                throw new IllegalArgumentException("User already exists!");
            }
        }
    }

    public void list() throws IOException, FileNotFoundException {
        ObjectOutputStream AuxCopy = new ObjectOutputStream(new FileOutputStream("auxCopy.txt"));
        ObjectInputStream archive = new ObjectInputStream(new FileInputStream(this.filename));
        try {
            System.out.println("Lista de usuarios:");
            while (true) {
                String usuario = (String) archive.readObject();
                System.out.println(usuario);
                AuxCopy.writeObject(usuario);
            }

        } catch (Exception e) {

            AuxCopy.close();
            archive.close();

            File fArchive = new File(this.filename);
            File fArchiveCopy = new File("auxCopy.txt");

            fArchive.delete();
            fArchiveCopy.renameTo(fArchive);

        }
    }

    public void remove(String userToRemove) throws IOException, FileNotFoundException {
        ObjectOutputStream auxCopy = new ObjectOutputStream(new FileOutputStream("auxCopy.txt"));
        ObjectInputStream archive = new ObjectInputStream(new FileInputStream(this.filename));

        try {
            while (true) {
                String usuario = (String) archive.readObject();
                if (!usuario.equals(userToRemove)) {
                    auxCopy.writeObject(usuario);
                }
            }
        } catch (EOFException e) {
            auxCopy.close();
            archive.close();
            String userToRemoveTxt = (String) userToRemove + ".txt";
            File fArchive = new File(this.filename);
            File fArchiveCopy = new File("auxCopy.txt");
            File fNameArchive = new File(userToRemoveTxt);

            fArchive.delete();
            fArchiveCopy.renameTo(fArchive);

            System.out.println("User removed");
            fNameArchive.delete();
            System.out.println("Done!");

        } catch (Exception e) {

        }
    }

    public void login(String username) throws Exception {
        boolean userExists = true;
        ObjectInputStream userArchive = new ObjectInputStream(new FileInputStream((String) username + ".txt"));
        try {
            while (true) {
                String comprobador = (String) userArchive.readObject();
                if (!comprobador.equals(username)) {
                    throw new FileNotFoundException("User does not exist");//unreachable state, program closes before it happens
                }


            }
        } catch (Exception n) {
            //User does exist. Continue
        } finally {

            if (userExists) {
                UserGame userGame = new UserGame(username);
                userGame.mainGame();
            } else {
                throw new FileNotFoundException("User does not exist!");//unreachable state, program closes before it happens
            }
        }

    }
}
