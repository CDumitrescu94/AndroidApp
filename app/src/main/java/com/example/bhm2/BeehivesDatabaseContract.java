package com.example.bhm2;

import android.provider.BaseColumns;

public class BeehivesDatabaseContract {
    private BeehivesDatabaseContract() {
    }

    public static final class BeeHivesInfoEntry implements BaseColumns {
        public static final String TABLE_NAME = "beehives";
        public  static final String COLUMN_BEEHIVE_ID = "id";
        public static final String COLUMN_BEEHIVE_NUMBER = "number";
        public static final String COLUMN_BEEHIVE_POPULATION = "population";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_BEEHIVE_ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_BEEHIVE_NUMBER + " INTEGER UNIQUE NOT NULL, " +
                        COLUMN_BEEHIVE_POPULATION + " TEXT)";

    }
}
