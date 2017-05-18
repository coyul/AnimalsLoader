package ru.sberbank.animalsloader;

/**
 * Created by User22 on 18.05.2017.
 */

public class Animal {
    private String mSpecies;
    private String mName;
    private int mAge;
    private String mLocation;

    public Animal(String mSpecies, String mName, int mAge, String location) {
        this.mSpecies = mSpecies;
        this.mName = mName;
        this.mAge = mAge;
        this.mLocation = location;
    }

    public String getmSpecies() {
        return mSpecies;
    }

    public String getmName() {
        return mName;
    }

    public int getmAge() {
        return mAge;
    }

    public String getmLocation() {
        return mLocation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;

        Animal animal = (Animal) o;

        if (getmAge() != animal.getmAge()) return false;
        if (!getmSpecies().equals(animal.getmSpecies())) return false;
        if (getmName() != null ? !getmName().equals(animal.getmName()) : animal.getmName() != null)
            return false;
        return getmLocation() != null ? getmLocation().equals(animal.getmLocation()) : animal.getmLocation() == null;

    }

    @Override
    public int hashCode() {
        int result = getmSpecies().hashCode();
        result = 31 * result + (getmName() != null ? getmName().hashCode() : 0);
        result = 31 * result + getmAge();
        result = 31 * result + (getmLocation() != null ? getmLocation().hashCode() : 0);
        return result;
    }
}
