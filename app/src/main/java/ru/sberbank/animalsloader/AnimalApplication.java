package ru.sberbank.animalsloader;

import android.app.Application;

public class AnimalApplication extends Application implements AnimalStorageProvider {

    AnimalStorage mAnimalStorage = new AnimalStorage();

    @Override
    public AnimalStorage getAnimalStorage() {
        return mAnimalStorage;
    }
}
