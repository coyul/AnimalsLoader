package ru.sberbank.animalsloader.activities;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.sberbank.animalsloader.R;
import ru.sberbank.animalsloader.animal.Animal;
import ru.sberbank.animalsloader.animal.AnimalStorage;
import ru.sberbank.animalsloader.animal.AnimalStorageProvider;


public abstract class InputAnimalInfoActivity extends AppCompatActivity implements View.OnClickListener {

    protected AnimalStorage mAnimalsStorage;

    protected TextInputLayout mSpeciesTextInput;
    protected EditText mSpeciesEditText;
    protected TextInputLayout mNameTextInput;
    protected EditText mNameEditText;
    protected TextInputLayout mAgeTextInput;
    protected EditText mAgeEditText;
    protected TextInputLayout mLocationTextInput;
    protected EditText mLocationEditText;

    protected Button mOnChangeButton;
    protected EditText[] mEditTexts;

    protected abstract void setUpTextInViews();

    protected void onCreateActivity() {

        AnimalStorageProvider provider = (AnimalStorageProvider) getApplication();
        mAnimalsStorage = provider.getAnimalStorage();

        setContentView(R.layout.activity_add);
        setUpViews();
        setUpTextInViews();
        mOnChangeButton.setOnClickListener(this);

        mEditTexts = new EditText[]{mSpeciesEditText, mNameEditText, mAgeEditText, mLocationEditText};
        for (EditText editText : mEditTexts) {
            editText.addTextChangedListener(new TextWatcherImpl());
        }
    }

    protected void setUpViews() {
        mSpeciesTextInput = (TextInputLayout) findViewById(R.id.species_text_input);
        mSpeciesEditText = (EditText) findViewById(R.id.species_edit_text);
        mNameTextInput = (TextInputLayout) findViewById(R.id.name_text_input);
        mNameEditText = (EditText) findViewById(R.id.name_edit_text);
        mAgeTextInput = (TextInputLayout) findViewById(R.id.age_text_input);
        mAgeEditText = (EditText) findViewById(R.id.age_edit_text);
        mLocationTextInput = (TextInputLayout) findViewById(R.id.location_text_input);
        mLocationEditText = (EditText) findViewById(R.id.location_edit_text);
        mOnChangeButton = (Button) findViewById(R.id.confirm_button);
    }

    protected Animal getAnimalFromCurrentData(){
        String species = mSpeciesEditText.getText().toString();
        String name = mNameEditText.getText().toString();
        int age = Integer.valueOf(mAgeEditText.getText().toString());
        String location = mLocationEditText.getText().toString();
        return new Animal(species, name, age, location);
    }

    protected class TextWatcherImpl implements TextWatcher {

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
            mOnChangeButton.setEnabled(buttonEnabled);
        }
    }
}