package ru.sberbank.animalsloader;

import android.content.Context;
import android.os.SystemClock;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;
import java.util.Random;

public class AnimalLoader extends AsyncTaskLoader <List<Animal>> implements OnListChangeListener {

    private static final String TAG = "AnimalLoader";
    private AnimalStorage mAnimalStorage;
    private List<Animal> mCashedList;

    public AnimalLoader(Context context, AnimalStorage animalStorage) {
        super(context);
        this.mAnimalStorage = animalStorage;
        mAnimalStorage.addOnListChangeListener(this);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.e(TAG, "onStartLoading");
        if (mCashedList == null || takeContentChanged()) {
            Log.e(TAG, "onStartLoading forceLoad");
            forceLoad();
        }

    }

    @Override
    public void deliverResult(List<Animal> data) {
        super.deliverResult(data);
        Log.e(TAG, "deliverResult");
        mCashedList = data;
    }

    @Override
    public List<Animal> loadInBackground() {
        Log.e(TAG, "loadInBackground");
        List<Animal> animalList = mAnimalStorage.getAnimals();
        return animalList;
    }

    @Override
    protected void onReset() {
        super.onReset();
        Log.e(TAG, "onReset");
        mAnimalStorage.removeOnListChangeListener(this);
    }

    @Override
    public void onAnimalAdded() {
        onContentChanged();
    }
}
