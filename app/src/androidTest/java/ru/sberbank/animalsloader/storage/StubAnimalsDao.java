package ru.sberbank.animalsloader.storage;

import java.util.ArrayList;
import java.util.List;

import ru.sberbank.animalsloader.EntitiesGenerator;
import ru.sberbank.animalsloader.animal.Animal;
import ru.sberbank.animalsloader.db.AnimalsDao;

public class StubAnimalsDao implements AnimalsDao {

    public List<Animal> animals = new ArrayList<>();
    public boolean getAnimalsCall = false;
    public boolean insertAnimalCall = false;
    public boolean updateAnimalCall = false;
    public boolean deleteAnimalCall = false;
    public long lastAddedId;

    public StubAnimalsDao() {
        setUpAnimalsList();
    }

    @Override
    public List<Animal> getAnimals() {
        getAnimalsCall = true;
        return new ArrayList<>(animals);
    }

    @Override
    public long insertAnimal(Animal animal) {
        insertAnimalCall = true;

        lastAddedId++;
        animal.setID(lastAddedId);
        animals.add(animal);
        return lastAddedId;
    }

    @Override
    public int update(Animal animal) {
        updateAnimalCall = true;

        int index = getAnimalIndexById(animal.getID());
        if (index >= 0) return 1;
        else return 0;
    }

    @Override
    public int delete(Animal animal) {
        deleteAnimalCall = true;

        if (animals.remove(animal)) return 1;
        else return 0;
    }

    @Override
    public Animal getAnimalById(long id) {
        int index = getAnimalIndexById(id);
        if (index >= 0) return animals.get(index);
        else return null;
    }


    private void setUpAnimalsList() {
        List<Animal> newList = EntitiesGenerator.createRandomAnimalsList(false);
        insertAnimalsForTest(newList);
    }

    private void insertAnimalsForTest(List<Animal> list) {
        for (Animal animal : list) {
            lastAddedId++;
            animal.setID(lastAddedId);
        }
        animals.addAll(list);
    }

    private int getAnimalIndexById(long id) {
        for (Animal animal : animals) {
            if (animal.getID() == id) {
                return animals.indexOf(animal);
            }
        }
        return -1;
    }
}
