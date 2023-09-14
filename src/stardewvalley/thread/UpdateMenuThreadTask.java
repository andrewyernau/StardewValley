package stardewvalley.thread;

import stardewvalley.UserGame;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UpdateMenuThreadTask extends Thread{
    // please press Ctrl + Alt + L more often
    private volatile boolean running=true;
    protected boolean activeInScreen=true;

    public boolean isActiveInScreen() {
        return activeInScreen;
    }

    // there are actually other more reliable ways to check whether thread is running
    public boolean isRunning() {
        return running;
    }

    public void setActiveInScreen(boolean activeInScreen) {
        this.activeInScreen = activeInScreen;
    }

    public void run(){
        UserGame userGame=new UserGame("admin");
        Calendar initialdate= UserGame.initialCalendarGame();

        // The Map collection is for sure a powerful thing, but its overcomplicating things here
        // You see, Map is a collection that uses keys instead of indexes, like any other collection
        // And here you created a map, but used keys like indexes
        // You can just make it into a constant array
        // this takes less memory and works faster. You wouldnt need to use any Map features with this anyway
        final String[] suggestedMonths = new String[] {"January", "February", "April", "March", "May", "June", "July",
                "August", "September", "October", "November", "December"};

        Map<Integer,String> months=new HashMap<>();
        months.put(0,"January");
        months.put(1,"February");
        months.put(2,"March");
        months.put(3,"April");
        months.put(4,"May");
        months.put(5,"June");
        months.put(6,"July");
        months.put(7,"August");
        months.put(8,"September");
        months.put(9,"October");
        months.put(10,"November");
        months.put(11,"December");


        // please write all the comments in your code in english, that way everyone will understand whats in there
        //el dia del juego dura 10 minutos, es decir cada minuto pasan 2,4 horas, y en cada segundo pasan 2,4 minutos o lo que es lo mismo necesitamos 0,416667 segundos para que pase un minuto en el juego
        while(running){
            // line length limit in java by recent codestyle is set to 120, please put a linebreak before reaching that
            // limit, even in comments
            if(activeInScreen==true)
            System.out.print("\rStardewValley" +
                    "     Date: " + initialdate.get(Calendar.YEAR) + " " + months.get(initialdate.get(Calendar.MONTH))
                    + " " + initialdate.get(Calendar.DAY_OF_MONTH) +
                    "     Time: " + initialdate.get(Calendar.HOUR_OF_DAY) + ":" + initialdate.get(Calendar.MINUTE) + " ");

            try {
                // what is 416? Its called a "magic number". You should definitely make it a constant in this class
                // you just create a field public static final DAY_DURATION = 416; and write your comment about what
                // is it there. Also, the constant field gives you ability to both use this value in other parts of
                // your game and if you ever need to change it, it would be a lot more easy to find it
                Thread.sleep(416);//416 un dia dura 10 minutos
                initialdate.add(Calendar.MINUTE,1);
                // typically no one calls e.printStackTrace(), its a bad practice. If you wanna do nothing when this
                // exception is caught, just change lines below to catch (InterruptedException ignored) {}
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    // ig you already realised what you need to do here
    public void stopProcess(){
        running=false;
    }
    public void startProcess(){
        running=true;
    }
}
// for review
