package ru.sberbank.animalsloader;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mSpeciesView;
    private TextView mNameView;
    private TextView mAgeView;
    private TextView mLocationView;
    private Button mRefreshButton;
    private ProgressBar mProgressBar;
    private RelativeLayout mRelativeLayout;

    private static int LOADER_ID = 1;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpeciesView = (TextView) findViewById(R.id.species);
        mNameView = (TextView) findViewById(R.id.name);
        mAgeView = (TextView) findViewById(R.id.age);
        mLocationView = (TextView) findViewById(R.id.location);
        mRefreshButton = (Button) findViewById(R.id.refresh_button);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relative_layout);

        mRefreshButton.setOnClickListener(this);
        getSupportLoaderManager().initLoader(LOADER_ID, null, new AnimalsLoaderCallBacks());
    }


    @Override
    public void onClick(View v) {
        getSupportLoaderManager().getLoader(LOADER_ID).forceLoad();
    }

    private class AnimalsLoaderCallBacks implements LoaderManager.LoaderCallbacks<Animal>{

        @Override
        public Loader onCreateLoader(int id, Bundle args) {
            return new AnimalLoader(MainActivity.this);
        }

        @Override
        public void onLoadFinished(Loader loader, Animal data) {
            Log.e(TAG, "on load finished");
            mSpeciesView.setText(data.getmSpecies());
            mNameView.setText(data.getmName());
            mAgeView.setText(getString(R.string.age_format, String.valueOf(data.getmAge())));
            mLocationView.setText(getString(R.string.location_format, data.getmLocation()));

            mRelativeLayout.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);


        }

        @Override
        public void onLoaderReset(Loader loader) {
            Log.e(TAG, "on load reset");
            mRelativeLayout.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }
}
