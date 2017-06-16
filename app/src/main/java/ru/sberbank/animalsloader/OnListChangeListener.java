package ru.sberbank.animalsloader;

import ru.sberbank.animalsloader.animal.Animal;
import ru.sberbank.animalsloader.animal.AnimalStorage;

public interface OnListChangeListener {
    void onAnimalAdded(AnimalStorage sender, Animal animal);
    void onAnimalDeleted(AnimalStorage sender, Animal animal);
    void onAnimalUpdated(AnimalStorage sender, Animal animal);
}
