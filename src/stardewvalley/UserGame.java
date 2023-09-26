package stardewvalley;

import stardewvalley.entity.animal.Animal;
import stardewvalley.thread.UpdateMenuThreadTask;
import stardewvalley.user.inventory.Inventory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class UserGame {
    public Calendar date;

    public static Calendar initialCalendarGame() {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, 2010);
        date.set(Calendar.MONTH, Calendar.JANUARY);
        date.set(Calendar.DAY_OF_MONTH, 1);
        date.set(Calendar.HOUR_OF_DAY, 10);
        date.set(Calendar.MINUTE, 0);
        return date;
    }

    public String username;
    public float money;

    public UserGame(String username) {
        this.username = username;
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float moneyPay) {
        this.money += moneyPay;
    }


    public void mainGame() throws Exception {

        //SETTING UP YOUR CHARACTER
        Inventory userInventory = new Inventory();
        List<Animal> farmLand = new ArrayList<>();


        setMoney(2000);//Initial money
        boolean reading = true;

        //STARTING TIME RUN

        UpdateMenuThreadTask threadTask = new UpdateMenuThreadTask();
        threadTask.startProcess();
        threadTask.start();

        //GAME STARTED

        Scanner scanner = new Scanner(System.in);
        while (reading) {
            Calendar date = threadTask.getGamedate();//need to get calendar from ThreadTask for the up to date date.
            char lecture = scanner.next().charAt(0);
            switch (lecture) {
                case 't'://show or hide time
                    switchThread(threadTask);
                    break;
                case 'q'://quit game
                    reading = quit(threadTask);
                    System.out.println("\rback to mainGame");
                    break;
                case 'o'://options
                    System.out.println("\rOptions are: t for toggle time display, q for quit the game,b for open the merch");
                    break;
                case 's'://shop
                    shop(money, date, threadTask, (List) userInventory);
                    break;
                case 'm':
                    System.out.println("Your money" + money);
                    break;
            }
        }
        System.out.println("value of reading your input " + reading + ", therefore we are not suposed to be there, this is a non contemplated place");
    }

    public void shop(float money, Calendar date, UpdateMenuThreadTask threadTask, List inventory) {
        if (threadTask.isActiveInScreen()) switchThread(threadTask);
        boolean shopping = true;
        while (shopping) {
            System.out.println("\rWelcome to the shop! What would you like to do? Buy or Sell(B/S)");
            Scanner scanner = new Scanner(System.in);
            char lecture = scanner.next().toLowerCase().charAt(0);
            if (lecture == 'b') {
                System.out.println("\rSelecting buying menu:\nNadia(animals)\nMark(crops)\nIrene(other)\nPress 1,2 or 3");
                int lecture2 = scanner.nextInt();
                switch (lecture2) {
                    case 1:
                        System.out.println("Nadia's shop: Cow-150$(1), Chicken-40$(2), Sheep-135$(3)");
                        int lecture3 = scanner.nextInt();
                        switch (lecture3) {
                            case 1:
                                buyCow(money, date, inventory);
                                break;
                            case 2:
                                buyChicken(money, date, inventory);
                                break;
                            case 3:
                                buySheep(money, date, inventory);
                                break;
                            default:
                                shop(money, date, threadTask, inventory);
                        }
                        break;
                    case 2:
                        int lecture4 = scanner.nextInt();
                        switch (lecture4) {
                            case 1:
                                buyCow(money, date, inventory);
                                break;
                            case 2:
                                buyChicken(money, date, inventory);
                                break;
                            case 3:
                                buySheep(money, date, inventory);
                                break;
                            default:
                                shop(money, date, threadTask, inventory);
                        }
                        break;
                    case 3:
                        break;
                    default:
                        shop(money, date, threadTask, inventory);
                }

            } else if ((lecture == 's')) {
                System.out.println("\rSelecting selling menu:\nNadia(animals)\nMark(crops)\nIrene(other)\nPress 1,2 or 3");
                int lecture2 = scanner.nextInt();

            } else {
                System.out.println("Invalid input.");
            }
            System.out.println("\rDo you want to continue shopping? Y/N");
            char continueChoice = scanner.next().toLowerCase().charAt(0);
            if (continueChoice == 'n') {
                shopping = false;
            }
        }
    }

    public boolean quit(UpdateMenuThreadTask threadTask) throws Exception {
        if (threadTask.isActiveInScreen())
            switchThread(threadTask);

        boolean quitting = true;
        while (quitting) {

            System.out.println("\rAre you sure you want to leave?Y/N");
            Scanner scanner = new Scanner(System.in);
            char lecture = scanner.next().toLowerCase().charAt(0);
            if (lecture == 'y') {
                threadTask.stop();
                throw new Exception("Game closed.");
            } else if (lecture == 'n') {
                return true;
            } else {
                System.out.println("Invalid input.");
            }
        }
        //No leave main loop
        return false;
    }

    public void switchThread(UpdateMenuThreadTask threadTask) {
        threadTask.setActiveInScreen(!threadTask.isActiveInScreen());
    }

    public void buyCow(float money, Calendar date, List inventory) {
        float costCow = 150.00f;
        if (money >= costCow) {
            money = money - costCow;
            System.out.println("\rYou just bought a cow!What name will you give her?");

            Scanner scanner = new Scanner(System.in);
            String lecture = scanner.next();
            Calendar birthdate = (Calendar) date.clone();
            birthdate.add(Calendar.DAY_OF_MONTH, -30);
            //ADD COW. NOT IMPLEMENTED YET


        } else {
            System.out.println("\rYou don't have enough money, Shop closed.");
        }
    }

    public void buyChicken(float money, Calendar date, List inventory) {
        float costCow = 150.00f;
        if (money >= costCow) {
            money = money - costCow;
            System.out.println("\rYou just bought a cow!What name will you give her?");

            Scanner scanner = new Scanner(System.in);
            String lecture = scanner.next();
            Calendar birthdate = (Calendar) date.clone();
            birthdate.add(Calendar.DAY_OF_MONTH, -30);
            //ADD CHICKEN. NOT IMPLEMENTED YET.

        } else {
            System.out.println("\rYou don't have enough money, Shop closed.");
        }
    }

    public void buySheep(float money, Calendar date, List inventory) {
        float costCow = 150.00f;
        if (money >= costCow) {
            money = money - costCow;
            System.out.println("\rYou just bought a cow!What name will you give her?");

            Scanner scanner = new Scanner(System.in);
            String lecture = scanner.next();
            Calendar birthdate = (Calendar) date.clone();
            birthdate.add(Calendar.DAY_OF_MONTH, -30);
            //ADD SHEEP. NOT IMPLEMENTED YET


        } else {
            System.out.println("\rYou don't have enough money, Shop closed.");
        }
    }
}
