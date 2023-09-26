package stardewvalley.thread;

import stardewvalley.UserGame;

import java.util.Calendar;


public class UpdateMenuThreadTask extends Thread {
    private volatile boolean running = true;
    public static final int MINUTE_DURATION = 416;
    //every gameday takes 10 minutes
    // we need 0,416667 seconds to update a minute in the game
    protected boolean activeInScreen = true;
    private Calendar gamedate = UserGame.initialCalendarGame();
    ;

    public boolean isActiveInScreen() {
        return activeInScreen;
    }

    public boolean isRunning() {
        return running;
    }

    public void setActiveInScreen(boolean activeInScreen) {
        this.activeInScreen = activeInScreen;
    }


    public Calendar getGamedate() {
        return gamedate;
    }

    public void run() {

        final String[] suggestedMonths = new String[]{"January", "February", "April", "March", "May", "June", "July",
                "August", "September", "October", "November", "December"};


        while (running) {
            if (activeInScreen == true)
                System.out.print("\rStardewValley" +
                        "     Date: " + gamedate.get(Calendar.YEAR) + " " + suggestedMonths[gamedate.get(Calendar.MONTH)] + " " + gamedate.get(Calendar.DAY_OF_MONTH) +
                        "     Time: " + gamedate.get(Calendar.HOUR_OF_DAY) + ":" + gamedate.get(Calendar.MINUTE) + " ");

            try {
                Thread.sleep(MINUTE_DURATION);
                gamedate.add(Calendar.MINUTE, 1);

            } catch (InterruptedException ignored) {
            }
        }

    }

    public void stopProcess() {
        running = false;
    }

    public void startProcess() {
        running = true;
    }


}
