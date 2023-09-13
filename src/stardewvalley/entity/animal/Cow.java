package stardewvalley.entity.animal;

public class Cow extends Animal{
    public Cow(String name, int age) {
        super(name, age);
    }

    @Override
    public void returnFood() {
        System.out.println(this.name + " cow just gave you 1 bucket of milk");
    }
}
