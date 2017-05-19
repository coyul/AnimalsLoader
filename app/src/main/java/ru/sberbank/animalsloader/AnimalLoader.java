package ru.sberbank.animalsloader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;
import java.util.Random;

/**
 * Created by User22 on 18.05.2017.
 */

public class AnimalLoader extends AsyncTaskLoader<Animal> {

    private static final String TAG = "AnimalLoader";
    private Animal mRandomAnimal = null;

    public AnimalLoader(Context context) {
        super(context);
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
        List<Animal> animalList = AnimalGenerator.provideAnimal();
        mRandomAnimal = getRandomAnimal(animalList);
        return mRandomAnimal;
    }


    private Animal getRandomAnimal(List<Animal> list) {
        Animal result;
        while (true) {
            int index = new Random().nextInt(list.size());
            result = list.get(index);
            if (mRandomAnimal != result) break;
        }
        return result;
    }
}
