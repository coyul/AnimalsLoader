package ru.sberbank.animalsloader.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;

import java.util.List;

import ru.sberbank.animalsloader.EntitiesGenerator;
import ru.sberbank.animalsloader.animal.Animal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class SQLiteAnimalsDaoTest {

    private SQLiteAnimalsDaoRule mDaoRule = new SQLiteAnimalsDaoRule();

    @Rule
    public TestRule mRule = RuleChain.outerRule(mDaoRule);

    @Test
    public void testGetAnimals() {
        List<Animal> expected = EntitiesGenerator.createRandomAnimalsList(false);
        for (Animal animal : expected) {
            animal.setID(mDaoRule.getSQLLiteAnimalsDao().insertAnimal(animal));
        }
        List<Animal> actual = mDaoRule.getSQLLiteAnimalsDao().getAnimals();
        assertThat(actual, is(expected));
    }

    @Test
    public void testInsert() {
        Animal animal = EntitiesGenerator.createRandomAnimal(false);
        long id = mDaoRule.getSQLLiteAnimalsDao().insertAnimal(animal);
        assertThat(true, is(id > 0));
    }

    @Test
    public void testUpdate() {
        Animal animal = EntitiesGenerator.createRandomAnimal(false);
        long id = mDaoRule.getSQLLiteAnimalsDao().insertAnimal(animal);

        animal = EntitiesGenerator.createRandomAnimal(false);
        animal.setID(id);

        int result = mDaoRule.getSQLLiteAnimalsDao().update(animal);
        assertThat(true, is(result > 0));
    }

    @Test
    public void testDelete() {
        Animal animal = EntitiesGenerator.createRandomAnimal(false);
        animal.setID(mDaoRule.getSQLLiteAnimalsDao().insertAnimal(animal));

        int result = mDaoRule.getSQLLiteAnimalsDao().delete(animal);
        assertThat(true, is(result > 0));
    }

    @Test
    public void testGetAnimalById() {
        Animal expected = EntitiesGenerator.createRandomAnimal(false);
        long expectedId = mDaoRule.getSQLLiteAnimalsDao().insertAnimal(expected);
        expected.setID(expectedId);
        Animal actual = mDaoRule.getSQLLiteAnimalsDao().getAnimalById(expectedId);

        assertThat(actual, is(expected));
    }

    @Test
    public void testOnCreateDB() {
        SQLiteDatabase db = mDaoRule.getSQLLiteAnimalsDao().getReadableDatabase();
        assertThat(true, is(db.isOpen()));
        Cursor cursor = db.query(SQLLiteAnimalsDao.TABLE_NAME, null, null, null, null, null, null);

        assertThat(cursor.getColumnName(0), is(AnimalsContract.Animals._ID));
        assertThat(cursor.getColumnName(1), is(AnimalsContract.Animals.SPECIES));
        assertThat(cursor.getColumnName(2), is(AnimalsContract.Animals.NAME));
        assertThat(cursor.getColumnName(3), is(AnimalsContract.Animals.AGE));
        assertThat(cursor.getColumnName(4), is(AnimalsContract.Animals.LOCATION));

        cursor.close();
        db.close();
    }

}
