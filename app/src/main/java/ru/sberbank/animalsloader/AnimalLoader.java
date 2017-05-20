package ru.sberbank.animalsloader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;
import java.util.Random;

public class AnimalLoader extends AsyncTaskLoader<Animal> {

    private static final String TAG = "AnimalLoader";
    private Random random;

    public AnimalLoader(Context context) {
        super(context);
        random = new Random();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
        Log.e(TAG, "onStartLoading");
    }

    @Override
    public Animal loadInBackground() {
        Log.e(TAG, "loadInBackground");
        List<Animal> animalList = AnimalGenerator.provideAnimals();
        return AnimalGenerator.getRandomAnimal(animalList, random);
    }
}
