package ru.sberbank.animalsloader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AnimalAddActivity extends AppCompatActivity {
    private AnimalStorage mAnimalsStorage;

    private TextInputLayout mSpeciesTextInput;
    private EditText mSpeciesEditText;
    private TextInputLayout mNameTextInput;
    private EditText mNameEditText;
    private TextInputLayout mAgeTextInput;
    private EditText mAgeEditText;
    private TextInputLayout mLocationTextInput;
    private EditText mLocationEditText;

    private Button mAddButton;
    private EditText[] mEditTexts;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AnimalAddActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnimalStorageProvider provider = (AnimalStorageProvider) getApplication();
        mAnimalsStorage = provider.getAnimalStorage();

        setContentView(R.layout.activity_add);

        mSpeciesTextInput = (TextInputLayout) findViewById(R.id.species_text_input);
        mSpeciesEditText = (EditText) findViewById(R.id.species_edit_text);
        mNameTextInput = (TextInputLayout) findViewById(R.id.name_text_input);
        mNameEditText = (EditText) findViewById(R.id.name_edit_text);
        mAgeTextInput = (TextInputLayout) findViewById(R.id.age_text_input);
        mAgeEditText = (EditText) findViewById(R.id.age_edit_text);
        mLocationTextInput = (TextInputLayout) findViewById(R.id.location_text_input);
        mLocationEditText = (EditText) findViewById(R.id.location_edit_text);

        mAddButton = (Button) findViewById(R.id.add_animal_button);
        mEditTexts = new EditText[]{mSpeciesEditText, mNameEditText, mAgeEditText, mLocationEditText};
        for (EditText editText : mEditTexts) {
            editText.addTextChangedListener(new TextWatcherImpl());
        }

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAnimal();
            }
        });
    }

    private void createAnimal() {
        String species = mSpeciesEditText.getText().toString();
        String name = mNameEditText.getText().toString();
        int age = Integer.valueOf(mAgeEditText.getText().toString());
        String location = mLocationEditText.getText().toString();
        Animal animal = new Animal(species, name, age, location);
        mAnimalsStorage.addAnimal(animal);
        finish();
    }

    private class TextWatcherImpl implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            boolean buttonEnabled = true;
            for (EditText editText : mEditTexts) {
                if (TextUtils.isEmpty(editText.getText())) {
                    buttonEnabled = false;
                    break;
                }
            }
            mAddButton.setEnabled(buttonEnabled);
        }
    }
}
