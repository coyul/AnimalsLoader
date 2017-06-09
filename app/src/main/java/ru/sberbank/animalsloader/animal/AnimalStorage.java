package ru.sberbank.animalsloader.animal;

import java.util.ArrayList;
import java.util.List;

import ru.sberbank.animalsloader.OnListChangeListener;
import ru.sberbank.animalsloader.db.AnimalsDao;

public class AnimalStorage {

    private final List<OnListChangeListener> mOnListChangeListenerList;
    private final AnimalsDao mAnimalsDao;


    public AnimalStorage(AnimalsDao animalsDao) {
        mAnimalsDao = animalsDao;
        mOnListChangeListenerList = new ArrayList<>();
    }

    public List<Animal> getAnimals() {
        return mAnimalsDao.getAnimals();
    }

    public void addAnimal(Animal animal) {
        mAnimalsDao.insertAnimal(animal);
        for (OnListChangeListener listener : mOnListChangeListenerList) {
            listener.onAnimalAdded();
        }
    }

    public void deleteAnimal(Animal animal) {
        mAnimalsDao.delete(animal);
        for (OnListChangeListener listener : mOnListChangeListenerList) {
            listener.onAnimalDeleted();
        }
    }

    public void updateAnimal(Animal animal) {
        mAnimalsDao.update(animal);
        for (OnListChangeListener listener : mOnListChangeListenerList) {
            listener.onAnimalUpdated();
        }
    }

    public void addOnListChangeListener(OnListChangeListener listener) {
        mOnListChangeListenerList.add(listener);
    }

    public void removeOnListChangeListener(OnListChangeListener listener) {
        mOnListChangeListenerList.remove(listener);
    }
}
