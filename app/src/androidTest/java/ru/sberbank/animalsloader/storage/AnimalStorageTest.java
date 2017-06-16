package ru.sberbank.animalsloader.storage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import ru.sberbank.animalsloader.EntitiesGenerator;
import ru.sberbank.animalsloader.animal.Animal;
import ru.sberbank.animalsloader.animal.AnimalStorage;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AnimalStorageTest {
    private StubAnimalsDao mFakeAnimalsDao;
    private AnimalStorage mAnimalStorage;
    private StubOnListChangeListener mFakeListener;

    private static final String TAG = "AnimalStorageTest";

    @Before
    public void setUp() {
        mFakeAnimalsDao = new StubAnimalsDao();
        mAnimalStorage = new AnimalStorage(mFakeAnimalsDao);
        mFakeListener = new StubOnListChangeListener();
        mAnimalStorage.addOnListChangeListener(mFakeListener);
    }

    @Test
    public void testGetAnimals() {
        mFakeAnimalsDao.animals = EntitiesGenerator.createRandomAnimalsList(true);
        List<Animal> actual = mAnimalStorage.getAnimals();

        assertThat(actual, is(mFakeAnimalsDao.animals));
        assertThat(mFakeAnimalsDao.getAnimalsCall, is(true));
    }

    @Test
    public void testAddAnimal() {
        Animal animal = EntitiesGenerator.createRandomAnimal(false);
        mAnimalStorage.addAnimal(animal);

        assertThat(mFakeAnimalsDao.insertAnimalCall, is(true));
        assertThat(animal, is(mFakeListener.mChangedAnimal));
    }

    @Test
    public void testUpdateAnimal() {
        Animal animal = EntitiesGenerator.createRandomAnimal(false);
        Animal update = getRandomAnimalFromDao();
        animal.setID(update.getID());
        mAnimalStorage.updateAnimal(animal);

        assertThat(mFakeAnimalsDao.updateAnimalCall, is(true));
        assertThat(animal, is(mFakeListener.mChangedAnimal));
    }

    @Test
    public void testDeleteAnimal() {
        Animal toDelete = getRandomAnimalFromDao();
        mAnimalStorage.deleteAnimal(toDelete);

        assertThat(mFakeAnimalsDao.deleteAnimalCall, is(true));
        assertThat(toDelete, is(mFakeListener.mChangedAnimal));
    }

    @After
    public void tearDown() {
        mAnimalStorage.removeOnListChangeListener(mFakeListener);
    }


    private Animal getRandomAnimalFromDao() {
        Animal randomAnimal = null;
        List<Animal> list = mFakeAnimalsDao.getAnimals();
        Random random = new Random();
        if (list.size() > 0) {
            randomAnimal = list.get(random.nextInt(list.size() - 1));
        }

        return randomAnimal;
    }

}
