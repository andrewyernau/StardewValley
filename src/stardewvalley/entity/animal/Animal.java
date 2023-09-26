package stardewvalley.entity.animal;

import java.util.Calendar;

public class Animal {
    private String name;
    private Calendar birthdate;
    private Calendar date;

    public Animal(String name, Calendar date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public Calendar getBirthdate() {
        return birthdate;
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