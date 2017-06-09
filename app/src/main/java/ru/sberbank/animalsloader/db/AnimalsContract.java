package ru.sberbank.animalsloader.db;

import android.provider.BaseColumns;

/**
 * класс для объявления структуры данных
 */

public class AnimalsContract implements BaseColumns {


    public static class Animals implements BaseColumns {

        public static final String SPECIES = "species";
        public static final String NAME = "name";
        public static final String AGE = "age";
        public static final String LOCATION = "location";

    }


}
