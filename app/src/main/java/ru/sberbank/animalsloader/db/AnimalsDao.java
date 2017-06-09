package ru.sberbank.animalsloader.db;


import java.util.List;

import ru.sberbank.animalsloader.animal.Animal;

public interface AnimalsDao {

    List<Animal> getAnimals();

    long insertAnimal(Animal animal);

    int update(Animal animal);

    int delete(Animal animal);

    Animal getAnimalById(long id);


}
