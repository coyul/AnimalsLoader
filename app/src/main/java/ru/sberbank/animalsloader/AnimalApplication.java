package ru.sberbank.animalsloader;

import android.app.Application;

import ru.sberbank.animalsloader.animal.AnimalStorage;
import ru.sberbank.animalsloader.animal.AnimalStorageProvider;
import ru.sberbank.animalsloader.db.AnimalsDao;
import ru.sberbank.animalsloader.db.SQLLiteAnimalsDao;

public class AnimalApplication extends Application implements AnimalStorageProvider {

    AnimalStorage mAnimalStorage;

    @Override
    public void onCreate() {
        super.onCreate();
        AnimalsDao animalsDao = new SQLLiteAnimalsDao(this);
        mAnimalStorage = new AnimalStorage(animalsDao);
    }

    @Override
    public AnimalStorage getAnimalStorage() {
        return mAnimalStorage;
    }
}
