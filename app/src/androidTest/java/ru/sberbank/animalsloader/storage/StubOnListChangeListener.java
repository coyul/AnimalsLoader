package ru.sberbank.animalsloader.storage;

import ru.sberbank.animalsloader.OnListChangeListener;
import ru.sberbank.animalsloader.animal.Animal;
import ru.sberbank.animalsloader.animal.AnimalStorage;

public class StubOnListChangeListener implements OnListChangeListener {

    public Animal mChangedAnimal;

    @Override
    public void onAnimalAdded(AnimalStorage sender, Animal animal) {
        mChangedAnimal = animal;
    }

    @Override
    public void onAnimalDeleted(AnimalStorage sender, Animal animal) {
        mChangedAnimal = animal;
    }

    @Override
    public void onAnimalUpdated(AnimalStorage sender, Animal animal) {
        mChangedAnimal = animal;
    }
}
