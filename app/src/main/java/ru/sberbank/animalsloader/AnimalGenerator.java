package ru.sberbank.animalsloader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User22 on 18.05.2017.
 */

public class AnimalGenerator {

    public static final List<Animal> provideAnimal(){
        List<Animal> animals = new ArrayList();
        animals.add(new Animal("Cat","Murzik",1,"Home"));
        animals.add(new Animal("Dog","Bublik",10,"Street"));
        animals.add(new Animal("Deer","DeerOne",10,"Forest"));
        animals.add(new Animal("Fish","Pups",2,"Water"));
        animals.add(new Animal("StrangeAnimal","Noname",0,"???"));
        animals.add(new Animal("Tiger","Tiger",1,"Desert"));
        animals.add(new Animal("Bear","Misha",20,"Russia"));
        animals.add(new Animal("new Animal()","JavaAnimal",1,"Programm"));
        animals.add(new Animal("Bird","Birdie",5,"Sky"));
        animals.add(new Animal("Cat","Barsik",15,"Street"));

        return animals;
    }


}
