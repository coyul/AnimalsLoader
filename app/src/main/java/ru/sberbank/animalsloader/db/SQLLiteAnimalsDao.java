package ru.sberbank.animalsloader.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ru.sberbank.animalsloader.animal.Animal;

public class SQLLiteAnimalsDao extends SQLiteOpenHelper implements AnimalsDao {

    private static final String TAG = "SQLLiteAnimalsDao";

    private static final String NAME = "animals.db";
    public static final int CURRENT_VERSION = 1;
    private static final int NO_ID = -1;

    public static final String TABLE_NAME = "animals";

    public SQLLiteAnimalsDao(Context context) {
        super(context, NAME, null, CURRENT_VERSION);
    }

    public SQLLiteAnimalsDao(Context context, String name, int version) {
        super(context, name, null, version, null);
    }

    @Override
    public List<Animal> getAnimals() {
        List<Animal> animals = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
            animals = new ArrayList<>();
            //ставим курсор в первую позицию
            cursor.moveToFirst();
            //если курсор не последний - двигаемся дальше
            while (!cursor.isAfterLast()) {
                animals.add(createAnimal(cursor));
                cursor.moveToNext();
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        Log.e(TAG, "READ all");
        return animals;
    }

    @Override
    public long insertAnimal(Animal animal) {
        long id = NO_ID;
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        try {
            ContentValues values = createValuesFromAnimals(animal);
            id = db.insert(TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

        Log.e(TAG, "INSERT with " + id);
        return id;
    }

    @Override
    public int update(Animal animal) {
        int output = 0;
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        String whereClause = AnimalsContract._ID + "=?";
        Log.e(TAG, "UPDATE whereClause " + whereClause);
        String[] whereArgs = {String.valueOf(animal.getID())};
        Log.e(TAG, "UPDATE whereArgs " + whereArgs[0]);

        try {
            ContentValues values = createValuesFromAnimals(animal);
            Log.e(TAG, "UPDATE values " + values.get(AnimalsContract.Animals.SPECIES));


            output = db.update(TABLE_NAME, values, whereClause, whereArgs);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
        Log.e(TAG, "UPDATE with " + output);
        return output;
    }

    @Override
    public int delete(Animal animal) {
        int output = 0;
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        String whereClause = AnimalsContract._ID + "=?";
        String[] whereArgs = {String.valueOf(animal.getID())};
        try {
            output = db.delete(TABLE_NAME, whereClause, whereArgs);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

        Log.e(TAG, "DELETE with " + output);
        return output;
    }

    @Override
    public Animal getAnimalById(long id) {
        Animal animal = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;

        String selection = AnimalsContract._ID + "=?";
        String[] selectionArgs = {String.valueOf(id)};

        try {
            cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                animal = createAnimal(cursor);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        Log.e(TAG, "READ single");
        return animal;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                AnimalsContract.Animals._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AnimalsContract.Animals.SPECIES + " TEXT NOT NULL, " +
                AnimalsContract.Animals.NAME + " TEXT NOT NULL, " +
                AnimalsContract.Animals.AGE + " INTEGER NOT NULL, " +
                AnimalsContract.Animals.LOCATION + " TEXT NOT NULL" +
                ");";

        Log.e(TAG, "CREATE completed");
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static Animal createAnimal(Cursor cursor) {
        Animal animal = new Animal();
        animal.setID(getLong(cursor, AnimalsContract._ID));
        animal.setSpecies(getString(cursor, AnimalsContract.Animals.SPECIES));
        animal.setName(getString(cursor, AnimalsContract.Animals.NAME));
        animal.setAge(getInt(cursor, AnimalsContract.Animals.AGE));
        animal.setLocation(getString(cursor, AnimalsContract.Animals.LOCATION));
        return animal;
    }

    private static long getLong(Cursor cursor, String ColumnName) {
        return cursor.getLong(cursor.getColumnIndex(ColumnName));
    }

    private static String getString(Cursor cursor, String ColumnName) {
        return cursor.getString(cursor.getColumnIndex(ColumnName));
    }

    private static int getInt(Cursor cursor, String ColumnName) {
        return cursor.getInt(cursor.getColumnIndex(ColumnName));
    }


    private static ContentValues createValuesFromAnimals(Animal animal) {
        ContentValues values = new ContentValues();
        values.put(AnimalsContract.Animals.SPECIES, animal.getSpecies());
        values.put(AnimalsContract.Animals.NAME, animal.getName());
        values.put(AnimalsContract.Animals.AGE, animal.getAge());
        values.put(AnimalsContract.Animals.LOCATION, animal.getLocation());

        return values;
    }


}
