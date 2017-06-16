package ru.sberbank.animalsloader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.sberbank.animalsloader.animal.Animal;


public class EntitiesGenerator {

    private static final int SIZE = 10;
    private static final int START_CHAR = (int) 'a';
    private static final int END_CHAR = (int) 'z';
    private static final Random RANDOM = new Random();

    protected static String createRandomString() {
        int size = RANDOM.nextInt(100);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int value = START_CHAR + RANDOM.nextInt(END_CHAR - START_CHAR);
            sb.append((char) value);
        }
        return sb.toString();
    }

    public static Animal createRandomAnimal(boolean includeId) {
        Animal animal = new Animal();
        if (includeId) {
            animal.setID(Math.abs(RANDOM.nextLong()));
        }
        animal.setName(createRandomString());
        animal.setSpecies(createRandomString());
        animal.setAge(Math.abs(RANDOM.nextInt()));
        animal.setLocation(createRandomString());
        return animal;
    }

    public static Animal createCopyAnimal(Animal source) {
        Animal animal = new Animal();
        animal.setID(source.getID());
        animal.setSpecies(source.getSpecies());
        animal.setAge(source.getAge());
        animal.setName(source.getName());
        animal.setLocation(source.getLocation());
        return animal;
    }


    public static List<Animal> createRandomAnimalsList(boolean includeId) {
        List<Animal> resultList = new ArrayList<>();
        int size = 1 + RANDOM.nextInt(SIZE);
        for (int i = 0; i < size; i++) {
            resultList.add(createRandomAnimal(includeId));
        }
        return resultList;
    }

}
