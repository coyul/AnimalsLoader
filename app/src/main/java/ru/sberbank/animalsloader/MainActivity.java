package ru.sberbank.animalsloader;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AnimalStorage mAnimalStorage;
    private AnimalAdapter mAnimalAdapter;

    private ProgressBar mProgressBar;
    private ListView mListView;

    private static final int LOADER_ID = 1;
    private static final String TAG = "MainActivity";

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


    private class AnimalsLoaderCallBacks implements LoaderManager.LoaderCallbacks<List<Animal>> {

        @Override
        public Loader<List<Animal>> onCreateLoader(int id, Bundle args) {
            return new AnimalLoader(MainActivity.this, mAnimalStorage);
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
