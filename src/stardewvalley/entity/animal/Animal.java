package stardewvalley.entity.animal;

public abstract class Animal {
    public String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void birthday() {
        this.age++;
    }

    public void returnFood() {
        System.out.println("The animal gave you Food");
    }
}
// for review
