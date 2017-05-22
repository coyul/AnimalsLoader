package ru.sberbank.animalsloader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnimalStorage {

    private final List<Animal> mCurrentAnimals;
    private static final List<Animal> mAnimals;
    private final List<OnListChangeListener> mOnListChangeListenerList;

    static {
        mAnimals = Collections.unmodifiableList(AnimalGenerator.provideAnimals());
    }

    public AnimalStorage() {
        mCurrentAnimals = new ArrayList<>(mAnimals);
        mOnListChangeListenerList = new ArrayList<>();
    }

    public List<Animal> getAnimals(){
        return new ArrayList<>(mCurrentAnimals);
    }

    public void addAnimal(Animal animal){
        mCurrentAnimals.add(animal);
        for (OnListChangeListener listener : mOnListChangeListenerList){
            listener.onAnimalAdded();
        }
    }

    public void addOnListChangeListener(OnListChangeListener listener) {
        mOnListChangeListenerList.add(listener);
    }

    public void removeOnListChangeListener(OnListChangeListener listener) {
        mOnListChangeListenerList.remove(listener);
    }
}
