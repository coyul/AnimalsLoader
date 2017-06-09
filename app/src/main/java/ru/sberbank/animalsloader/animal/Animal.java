package ru.sberbank.animalsloader.animal;

import java.io.Serializable;

public class Animal implements Serializable {

    private long mID;
    private String mSpecies;
    private String mName;
    private int mAge;
    private String mLocation;

    public Animal() {
    }

    public Animal(String species, String name, int age, String location) {
        this.mSpecies = species;
        this.mName = name;
        this.mAge = age;
        this.mLocation = location;
    }

    public long getID() {
        return mID;
    }

    public String getSpecies() {
        return mSpecies;
    }

    public String getName() {
        return mName;
    }

    public int getAge() {
        return mAge;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setID(long ID) {
        this.mID = ID;
    }

    public void setSpecies(String species) {
        this.mSpecies = species;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public void setAge(int age) {
        this.mAge = age;
    }

    public void setLocation(String location) {
        this.mLocation = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Animal animal = (Animal) o;

        if (mID != animal.mID) return false;
        if (mAge != animal.mAge) return false;
        if (mSpecies != null ? !mSpecies.equals(animal.mSpecies) : animal.mSpecies != null)
            return false;
        if (mName != null ? !mName.equals(animal.mName) : animal.mName != null) return false;
        return mLocation != null ? mLocation.equals(animal.mLocation) : animal.mLocation == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (mID ^ (mID >>> 32));
        result = 31 * result + (mSpecies != null ? mSpecies.hashCode() : 0);
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + mAge;
        result = 31 * result + (mLocation != null ? mLocation.hashCode() : 0);
        return result;
    }
}
