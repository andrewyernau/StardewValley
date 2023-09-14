package stardewvalley;

import java.io.*;
import java.util.Scanner;

public class StardewValleyLogin {
   private String filename;

    public StardewValleyLogin(String filename){
        this.filename=filename;
    }
    //Once used it's not necessary.
    public void create() throws IOException,FileNotFoundException {
        ObjectOutputStream userArchive= new ObjectOutputStream(new FileOutputStream(this.filename));
        userArchive.close();
        System.out.println("Función 1 realizada correctamente, archivo creado");
   }
    public void add() throws Exception {
        ObjectOutputStream AuxCopy=new ObjectOutputStream(new FileOutputStream("auxCopy.txt"));
        ObjectInputStream archive=new ObjectInputStream(new FileInputStream(this.filename));
        Scanner sc= new Scanner(System.in);
        String newContact=sc.next();
        boolean userExists=false;
        try {
            while(true){
             String usuario=(String)archive.readObject();
             AuxCopy.writeObject(usuario);
             if(usuario.equals(newContact)){
                 userExists=true;
             }
        // lets just agree indentations here make no sense. This curly bracket below sits in a place as if its the one
        // that closes try block, while in reality, its for while block. Use Ctrl + Alt + L
        }
        // using exceptions as a way tp exit endless loop is a bad practice. There are dozens of ways to do it correctly
        }catch (Exception e){//when archive doesn't have the new user, starts to write the new user

            // Reopen the archive and reset position
            archive.close();
            archive = new ObjectInputStream(new FileInputStream(this.filename));


            // CHECK IF ALREADY EXISTS

            try{
                while (true){
                    // idk what comprobador means, hence, cant tell what exactly you are doing here, therefore, cant
                    // review it
                    String comprobador=(String)archive.readObject();
                    if (comprobador.equals(newContact)){
                        throw new IllegalArgumentException("User already exists");
                    };

                }
                // that is NOT how try-catch-finally supposed to be used
                // please stick to boolean variables, expressions and if-else statements, it will be both more
                // memory efficient, simpler and faster
            }catch (Exception n){
                //User does not exist. Continue
            }finally {

                if(!userExists){

                    AuxCopy.writeObject(newContact);
                    AuxCopy.close();
                    archive.close();


                    File fArchive=new File(this.filename);
                    File fArchiveCopy=new File("auxCopy.txt");

                    // delete and rename operations here could fail for multiple non related to program reasons.
                    // thats why both methods return boolean that tells if operation was successful
                    fArchive.delete();
                    fArchiveCopy.renameTo(fArchive);
                    System.out.println("Registered user");
                    String userStringTxt=newContact.toString()+".txt";
                    // if you want to create new file, just use File#createNewFile()
                    ObjectOutputStream userFile= new ObjectOutputStream(new FileOutputStream(userStringTxt));
                    userFile.close();

                        System.out.println("Done!");
            }
                else {
                    throw new IllegalArgumentException("User already exists!");
                }
            }
        }
   }
   // please keep only exceptions that are actually thrown in throws list
    public void list() throws IOException,FileNotFoundException {
        ObjectOutputStream AuxCopy=new ObjectOutputStream(new FileOutputStream("auxCopy.txt"));
        ObjectInputStream archive=new ObjectInputStream(new FileInputStream(this.filename));
        try {
            System.out.println("Lista de usuarios:");
            while(true){
                String usuario=(String)archive.readObject();
                System.out.println(usuario);
                AuxCopy.writeObject(usuario);
        }

        }catch (Exception e){

            AuxCopy.close();
            archive.close();

            File fArchive=new File(this.filename);
            File fArchiveCopy=new File("auxCopy.txt");

            fArchive.delete();
            fArchiveCopy.renameTo(fArchive);

        }
   }
   // once again, this all could be simplified a lot by not using exceptions to exit the loop
    public void remove(String userToRemove) throws IOException, FileNotFoundException {
        ObjectOutputStream auxCopy = new ObjectOutputStream(new FileOutputStream("auxCopy.txt"));
        ObjectInputStream archive = new ObjectInputStream(new FileInputStream(this.filename));
        // you can use try-with-resources here. Look that up
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
            String userToRemoveTxt=(String)userToRemove+".txt";
            File fArchive = new File(this.filename);
            File fArchiveCopy = new File("auxCopy.txt");
            File fNameArchive= new File(userToRemoveTxt);

            fArchive.delete();
            fArchiveCopy.renameTo(fArchive);

            System.out.println("User removed");
            fNameArchive.delete();
            System.out.println("Done!");

        } catch (Exception e) {

        }
    }
    public void login(String username) throws Exception {
        boolean userExists=true;
        ObjectInputStream userArchive = new ObjectInputStream(new FileInputStream((String) username + ".txt"));
        try{
            while (true){
                String comprobador=(String)userArchive.readObject();
                if (!comprobador.equals(username)){
                    throw new FileNotFoundException("User does not exist");// no llega a ejecutarse puesto que el archivo no existe para Java y salta el error antes.
                };

            }
        }catch (Exception n){
            //User does exist. Continue
        }finally {
            // btw, if you remove finally keyword here and curly brackets attached to it, you would get exactly the same result
            if(userExists){
                UserGame userGame=new UserGame(username);
                userGame.mainGame();
            }
            else{
                throw new FileNotFoundException("User does not exist!");// no llega a ejecutarse puesto que el archivo no existe para Java y salta el error antes.
            }
        }

    }
}
// for review
