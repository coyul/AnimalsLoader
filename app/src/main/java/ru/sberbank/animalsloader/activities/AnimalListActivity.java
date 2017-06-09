package ru.sberbank.animalsloader.activities;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

import ru.sberbank.animalsloader.animal.Animal;
import ru.sberbank.animalsloader.animal.AnimalAdapter;
import ru.sberbank.animalsloader.animal.AnimalLoader;
import ru.sberbank.animalsloader.animal.AnimalStorage;
import ru.sberbank.animalsloader.animal.AnimalStorageProvider;
import ru.sberbank.animalsloader.R;

public class AnimalListActivity extends AppCompatActivity {

    private AnimalStorage mAnimalStorage;
    private AnimalAdapter mAnimalAdapter;

    private ProgressBar mProgressBar;
    private ListView mListView;

    private static final int LOADER_ID = 1;
    private static final String TAG = "AnimalListActivity";
    private static final String ANIMAL_DATA = "animalToUpdate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnimalStorageProvider provider = (AnimalStorageProvider) getApplication();
        mAnimalStorage = provider.getAnimalStorage();

        mAnimalAdapter = new AnimalAdapter();
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(mAnimalAdapter);
        registerForContextMenu(mListView);

        getSupportLoaderManager().initLoader(LOADER_ID, null, new AnimalsLoaderCallBacks());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.animal_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = false;
        switch (item.getItemId()) {
            case R.id.add_animal_menu_item: {
                startActivity(AnimalAddActivity.newIntent(this));
                break;
            }
            default: {
                handled = super.onOptionsItemSelected(item);
                break;
            }
        }
        return handled;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.animal_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        switch (item.getItemId()) {
            case R.id.menu_update:
                Intent intent = AnimalUpdateActivity.newIntent(this);
                intent.putExtra(ANIMAL_DATA, mAnimalAdapter.getItem(position));
                startActivity(intent);
                break;
            case R.id.menu_delete:
                mAnimalStorage.deleteAnimal(mAnimalAdapter.getItem(position));
                break;
        }
        return true;
    }

    private class AnimalsLoaderCallBacks implements LoaderManager.LoaderCallbacks<List<Animal>> {

        @Override
        public Loader<List<Animal>> onCreateLoader(int id, Bundle args) {
            return new AnimalLoader(AnimalListActivity.this, mAnimalStorage);
        }

        @Override
        public void onLoadFinished(Loader<List<Animal>> loader, List<Animal> data) {
            mAnimalAdapter.setUpAdapter(data);

            mListView.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
            Log.e(TAG, "onLoadFinished");
        }

        @Override
        public void onLoaderReset(Loader<List<Animal>> loader) {
            mListView.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
            Log.e(TAG, "onLoaderReset");
        }
    }
}
