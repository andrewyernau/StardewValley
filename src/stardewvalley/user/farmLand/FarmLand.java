package stardewvalley.user.farmLand;

import stardewvalley.entity.animal.Animal;
import stardewvalley.entity.animal.Chicken;
import stardewvalley.entity.animal.Cow;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FarmLand {
    private List<Animal> animals;

    public FarmLand() {
        animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }


    public void removeAllAnimalsOfType(Animal animal) {
        animals.remove(animal);
    }

    public void getAnimals() {
        for (Animal animal : animals) {
            System.out.println(animal.getName() + animal.getClass());
        }
    }

    public void removeOneAnimalOfType(String name, Class<? extends Animal> animalType) {//cow
        int posAnimal = -1;

        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            if (animal.getName().equals(name) && animalType.isInstance(animal)) {
                posAnimal = i; // found Animal in position i in the animals ArrayList
                break;
            }
        }

        if (posAnimal != -1) {
            animals.remove(posAnimal); //Remove animal from the list
        }
    }

    public void listAnimals() {
        System.out.println("List of your animals on your farm land:");
        for(Animal animal:animals){
            System.out.println("Animal type: "+animal.getClass()+". Animal name: "+animal.getName()+". Animal birthdate: "+animal.getBirthdate());
        }

    }


}
