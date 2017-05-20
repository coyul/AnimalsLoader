package ru.sberbank.animalsloader;

public class Animal {
    private String mSpecies;
    private String mName;
    private int mAge;
    private String mLocation;

    public Animal(String species, String name, int age, String location) {
        this.mSpecies = species;
        this.mName = name;
        this.mAge = age;
        this.mLocation = location;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;

        Animal animal = (Animal) o;

        if (getAge() != animal.getAge()) return false;
        if (!getSpecies().equals(animal.getSpecies())) return false;
        if (getName() != null ? !getName().equals(animal.getName()) : animal.getName() != null)
            return false;
        return getLocation() != null ? getLocation().equals(animal.getLocation()) : animal.getLocation() == null;

    }

    @Override
    public int hashCode() {
        int result = getSpecies().hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getAge();
        result = 31 * result + (getLocation() != null ? getLocation().hashCode() : 0);
        return result;
    }
}
