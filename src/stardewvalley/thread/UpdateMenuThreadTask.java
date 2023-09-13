package stardewvalley.thread;

import stardewvalley.UserGame;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UpdateMenuThreadTask extends Thread{
    private volatile boolean running=true;
    protected boolean activeInScreen=true;

    public boolean isActiveInScreen() {
        return activeInScreen;
    }

    public boolean isRunning() {
        return running;
    }

    public void setActiveInScreen(boolean activeInScreen) {
        this.activeInScreen = activeInScreen;
    }

    public void run(){
        UserGame userGame=new UserGame("admin");
        Calendar initialdate= UserGame.initialCalendarGame();
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



        //el dia del juego dura 10 minutos, es decir cada minuto pasan 2,4 horas, y en cada segundo pasan 2,4 minutos o lo que es lo mismo necesitamos 0,416667 segundos para que pase un minuto en el juego
        while(running){
            if(activeInScreen==true)
            System.out.print("\rStardewValley" +
                    "     Date: " + initialdate.get(Calendar.YEAR) + " " + months.get(initialdate.get(Calendar.MONTH)) + " " + initialdate.get(Calendar.DAY_OF_MONTH) +
                    "     Time: " + initialdate.get(Calendar.HOUR_OF_DAY) + ":" + initialdate.get(Calendar.MINUTE) + " ");

            try {
                Thread.sleep(416);//416 un dia dura 10 minutos
                initialdate.add(Calendar.MINUTE,1);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void stopProcess(){
        running=false;
    }
    public void startProcess(){
        running=true;
    }
}
// for review
