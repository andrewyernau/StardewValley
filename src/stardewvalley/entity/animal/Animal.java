package stardewvalley.entity.animal;

import java.util.Calendar;

public abstract class Animal {
    private String name;
    private Calendar birthdate;

    public Animal(String name, Calendar birthdate) {
        this.name = name;
        this.birthdate = birthdate;

    }

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return String.valueOf("Year: " + this.birthdate.get(Calendar.YEAR) + "Month: " + this.birthdate.get(Calendar.MONTH) + " Day:" + this.birthdate.get(Calendar.DAY_OF_MONTH));
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(Calendar date) {
        this.birthdate = backDate(date);
    }

    public Calendar backDate(Calendar date) {
        //              REDO
        //              vvv
        Calendar backD = date;
        return backD;
    }

}