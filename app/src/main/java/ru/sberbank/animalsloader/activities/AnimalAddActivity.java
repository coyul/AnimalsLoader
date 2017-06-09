package ru.sberbank.animalsloader.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import ru.sberbank.animalsloader.animal.Animal;
import ru.sberbank.animalsloader.R;

public class AnimalAddActivity extends InputAnimalInfoActivity {

    private static final String TAG = "AnimalAddActivity";

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AnimalAddActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        onCreateActivity();
    }

    @Override
    protected void setUpTextInViews() {
        mOnChangeButton.setText(R.string.add_button);
    }

    @Override
    public void onClick(View view) {
        createAnimal();
    }

    private void createAnimal() {
        Animal animal = getAnimalFromCurrentData();
        mAnimalsStorage.addAnimal(animal);
        finish();
    }
}
