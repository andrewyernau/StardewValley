package stardewvalley.entity.animal;

public class Chicken extends Animal{
    public Chicken(String name, int age) {
        super(name, age);
    }

    @Override
    public void returnFood() {
        System.out.println(this.name + " chicken just gave you 1 egg");
    }
}
// for review
