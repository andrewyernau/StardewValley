package stardewvalley;

import stardewvalley.thread.UpdateMenuThreadTask;

import java.util.Calendar;
import java.util.Scanner;

public class UserGame {
    public Calendar date;
    public static Calendar initialCalendarGame(){
        Calendar date= Calendar.getInstance();
        date.set(Calendar.YEAR,2010);
        date.set(Calendar.MONTH,Calendar.JANUARY);
        date.set(Calendar.DAY_OF_MONTH,1);
        date.set(Calendar.HOUR_OF_DAY,10);
        date.set(Calendar.MINUTE,0);
        return date;
    }
    public String username;
    public float money;
    public  UserGame(String username){
        this.username=username;
        this.money=money;
    }
    public String getUsername() {
        return username;
    }
    public float getMoney() {
        return money;
    }
    public void setMoney(float moneyPay) {
        this.money+=moneyPay;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
    public void mainGame() throws Exception {
        boolean reading=true;
        UpdateMenuThreadTask threadTask=new UpdateMenuThreadTask();
        threadTask.startProcess();
        threadTask.start();
        Scanner scanner=new Scanner(System.in);
        while(reading){
            char lecture=scanner.next().charAt(0);
            switch (String.valueOf(lecture)){
                case "t":
                    System.out.println("t has been pressed");
                    switchThread(threadTask);
                    break;
                case "q":
                    reading=quit(threadTask);
                    System.out.println("back to mainGame");
                    System.out.println("valor de reading "+reading);
                    break;
                case "o":
                    System.out.println("Options are: t for toggle time display, q for quit the game,b for open the merch");
                    break;
                case "b":
                    shop(money);
                    break;
            }
            System.out.println("thread state "+threadTask.isRunning());
        }
        System.out.println("value of reading your input "+reading+ ", we are not suposed to be there. this is a non contemplated place");
    }
    public void shop(float money) {
        boolean shopping = true;
        while (shopping) {
            System.out.println("Welcome to the shop! What would you like to do? Buy or Sell(B/S)");
            Scanner scanner = new Scanner(System.in);
            char lecture = scanner.next().charAt(0);
            if (lecture == 'B' || lecture == 'b') {
                System.out.println("Selecting buying menu:\nNadia(animals)\nMark(crops)\nIrene(other)\nPress 1,2 or 3");
                Scanner scannerBuy = new Scanner(System.in);
                char lecture2 = scannerBuy.next().charAt(0);

            } else if ((lecture == 'S' || lecture == 's')) {
                System.out.println("Selecting selling menu:\nNadia(animals)\nMark(crops)\nIrene(other)\nPress 1,2 or 3");
                Scanner scannerSell = new Scanner(System.in);
                char lecture3 = scannerSell.next().charAt(0);

            } else {
                System.out.println("Invalid input.");
            }
            System.out.println("Do you want to continue shopping? Y/N");
            Scanner continueScanner = new Scanner(System.in);
            char continueChoice = continueScanner.next().charAt(0);
            if (continueChoice == 'N' || continueChoice == 'n') {
                shopping = false;
            }
        }
    }
    public boolean quit(UpdateMenuThreadTask threadTask) throws Exception {
        boolean quitting = true;
        while (quitting) {
            threadTask.stopProcess();
            System.out.println("Are you sure you want to leave?Y/N");
            Scanner scanner = new Scanner(System.in);
            char lecture = scanner.next().charAt(0);
            if (lecture == 'Y' || lecture == 'y') {
                System.exit(0);
                throw new Exception("Process ended. Game closed");
            } else if (lecture == 'N' || lecture == 'n') {
                quitting = false;
                System.out.println("N has been processed");
                threadTask.startProcess();
            } else {
                System.out.println("Invalid input.");
            }
        }
        System.out.println("back to main");
        //No leave main loop
        return true;
    }
    public void switchThread(UpdateMenuThreadTask threadTask){
        if(threadTask.isActiveInScreen()==false) {
            threadTask.setActiveInScreen(true);
        }
        else {
            threadTask.setActiveInScreen(false);
        }
    }
}
