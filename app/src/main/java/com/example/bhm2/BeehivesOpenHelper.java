package com.example.bhm2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class BeehivesOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "BeehivesManager.db";
    public static final int DATABASE_VERSION = 1;

    public BeehivesOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BeehivesDatabaseContract.BeeHivesInfoEntry.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean InsertBeehive(Integer beehiveNumber, String beehivePopulation){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("number", beehiveNumber);
        contentValues.put("population", beehivePopulation);

        long result = db.insert("beehives", null, contentValues);
        if (result == -1) {
            Log.d("INREGISTRAREA", "noup");
            return false;
        } else {
            Log.d("INREGISTRAREA", "yeap");
            return true;
        }
    }

    public boolean UpdateBeehive(String id, Integer beehiveNumber, String beehivePopulation){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("number", beehiveNumber);
        contentValues.put("population", beehivePopulation);

        long result = db.update("beehives", contentValues, "id = ?", new String[] {id});
        if (result == -1) {
            Log.d("INREGISTRAREA", "noup");
            return false;
        } else {
            Log.d("INREGISTRAREA", "yeap");
            return true;
        }
    }

    public ArrayList<String> GetBeehives(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> beehivesList = new ArrayList<>();
        final String[] beehiveColumns = {
                BeehivesDatabaseContract.BeeHivesInfoEntry.COLUMN_BEEHIVE_NUMBER,
                BeehivesDatabaseContract.BeeHivesInfoEntry.COLUMN_BEEHIVE_POPULATION};
        Cursor beehivesCursor = db.query(BeehivesDatabaseContract.BeeHivesInfoEntry.TABLE_NAME, beehiveColumns, null, null, null, null, null);
        while (beehivesCursor.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            beehivesList.add(beehivesCursor.getString(0));
        }
        return beehivesList;
    }

    public Cursor getBeehiveId(int number){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + BeehivesDatabaseContract.BeeHivesInfoEntry.COLUMN_BEEHIVE_ID + " FROM " + BeehivesDatabaseContract.BeeHivesInfoEntry.TABLE_NAME +
                " WHERE " + BeehivesDatabaseContract.BeeHivesInfoEntry.COLUMN_BEEHIVE_NUMBER + " = " + number ;
        return db.rawQuery(query, null);
    }

    public BeehiveInfo getBeehiveInfo(int number){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + BeehivesDatabaseContract.BeeHivesInfoEntry.TABLE_NAME +
                " WHERE " + BeehivesDatabaseContract.BeeHivesInfoEntry.COLUMN_BEEHIVE_NUMBER + " = " + number ;

        Cursor beehive = db.rawQuery(query, null);
        beehive.moveToFirst();
        return new BeehiveInfo(beehive.getString(0), beehive.getString(1), beehive.getString(2));
    }

}