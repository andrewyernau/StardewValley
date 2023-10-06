package stardewvalley;

import stardewvalley.entity.animal.Animal;
import stardewvalley.entity.animal.Cow;
import stardewvalley.entity.item.Bucket;
import stardewvalley.entity.item.Item;
import stardewvalley.thread.UpdateMenuThreadTask;
import stardewvalley.user.farmLand.FarmLand;
import stardewvalley.user.inventory.Inventory;
import stardewvalley.user.inventory.Money;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class UserGame {

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


    public UserGame(String username) {
        this.username = username;

    }

    public String getUsername() {
        return username;
    }






    public void mainGame() throws Exception {

        //SETTING UP YOUR CHARACTER
        Inventory userInventory = new Inventory();
        //  Bucket bucket = new Bucket("bucket");
        // userInventory.addItem(bucket); CHECKED, inventory works.

        FarmLand farmLand = new FarmLand();
        Money money=new Money(2000);//Initial money

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
                    System.out.println("\rOptions are: t for toggle time display, q for quit the game,b for open the shop, m for check your money,i to open your inventory, f see your farm land");
                    break;
                case 's'://shop
                    shop(money, date, threadTask, userInventory, farmLand);
                    break;
                case 'm':
                    System.out.println("Your money" + money);
                    break;
                case 'i': //Open your inventory
                    userInventory.listItems();
                    break;
                case 'f': //Open your inventory
                    farmLand.listAnimals();
                    break;
            }
        }
        System.out.println("value of reading your input " + reading + ", therefore we are not suposed to be there, this is a non contemplated place");
    }

    public void shop(Money money, Calendar date, UpdateMenuThreadTask threadTask, Inventory inventory, FarmLand farmLand) {
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
                                buyCow(money, date, inventory, farmLand);
                                break;
                            case 2:
                                buyChicken(money, date, inventory);
                                break;
                            case 3:
                                buySheep(money, date, inventory);
                                break;
                            default:
                                shop(money, date, threadTask, inventory, farmLand);
                        }
                        break;
                    case 2:
                        break;
                    case 3:
                        System.out.println("Irene's shop: Bucket-10$(1), future implementation");
                        int lecture4 = scanner.nextInt();
                        switch (lecture4) {
                            case 1:
                                buyBucket(money, inventory);
                                break;
                            case 2:

                                break;
                            case 3:

                                break;
                            default:
                                shop(money, date, threadTask, inventory, farmLand);
                        }
                        break;
                    default:
                        shop(money, date, threadTask, inventory, farmLand);
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

    public void buyCow(Money money, Calendar date, Inventory userInventory, FarmLand farmLand) {
        float costCow = 150.00f;
        buyAnimal(money, date, userInventory, farmLand, Cow.class, costCow);
    }


    public void buyChicken(Money money, Calendar date, Inventory inventory) {
        float costCow = 150.00f;

    }

    public void buySheep(Money money, Calendar date, Inventory inventory) {
        float costCow = 150.00f;


    }

    /**
     * Here is an example for reading items inside a List
     * List<Animal> animals = farmLand.getAnimals();
     * for (Animal animal : animals) {
     * String name = animal.getName();
     * System.out.println("Name: " + name);
     * }
     **/
    public void buyAnimal(Money money, Calendar date, Inventory userInventory, FarmLand farmLand, Class<? extends Animal> animalClass, float cost) {
        if (money.getAmount() >= cost) {
            money.subtract(cost);
            System.out.println("You just bought an animal! What name will you give it?");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.next();
            Calendar birthdate = (Calendar) date.clone();
            birthdate.add(Calendar.DAY_OF_MONTH, -30);

            try {
                Constructor<? extends Animal> constructor = animalClass.getConstructor(String.class, Calendar.class);//we use constructor to get a way that allow us to create the animal with String and Calendar
                Animal newAnimal = constructor.newInstance(name, birthdate);
                farmLand.addAnimal(newAnimal);
                System.out.println("Added " + animalClass.getSimpleName() + " named " + name + " to your farm.");
            } catch (Exception e) {
                System.out.println("Error adding animal: " + e.getMessage());
            }
        } else {
            System.out.println("You don't have enough money, Shop closed.");
        }
    }

    public void buyBucket(Money money, Inventory userInventory) {
        float costBucket = 20.00f;
        buyItem(money, userInventory, Bucket.class, costBucket);


    }

    public void buyItem(Money money, Inventory userInventory, Class<? extends Item> itemClass, float costItem) {
        if (money.getAmount() >= costItem) {
            money.subtract(costItem);
            System.out.println("You just bought an " + itemClass.getName() + "!");


            try {
                Constructor<? extends Item> constructor = itemClass.getConstructor(String.class);
                Item newItem = constructor.newInstance(itemClass.getName());
                userInventory.addItem(newItem);
            } catch (Exception e) {
                System.out.println("Error buying item: " + e.getMessage());
            }
        } else {
            System.out.println("You don't have enough money, Shop closed.");
        }
    }
}
