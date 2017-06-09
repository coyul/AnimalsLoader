package ru.sberbank.animalsloader.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import ru.sberbank.animalsloader.R;
import ru.sberbank.animalsloader.animal.Animal;

public class AnimalUpdateActivity extends InputAnimalInfoActivity {

    private static final String TAG = "AnimalAddActivity";
    private static final String ANIMAL_DATA = "animalToUpdate";

    private static Animal mUpdatingAnimal;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AnimalUpdateActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        mUpdatingAnimal = (Animal) getIntent().getSerializableExtra(ANIMAL_DATA);
        onCreateActivity();

    }

    @Override
    protected void setUpTextInViews() {
        mOnChangeButton.setText(R.string.update_button);
        mSpeciesEditText.setText(mUpdatingAnimal.getSpecies());
        mNameEditText.setText(mUpdatingAnimal.getName());
        mAgeEditText.setText(String.valueOf(mUpdatingAnimal.getAge()));
        mLocationEditText.setText(mUpdatingAnimal.getLocation());
    }

    @Override
    public void onClick(View view) {
        updateAnimal();
    }

    private void updateAnimal() {
        Animal animal = getAnimalFromCurrentData();
        animal.setID(mUpdatingAnimal.getID());
        mAnimalsStorage.updateAnimal(animal);
        finish();
    }
}
