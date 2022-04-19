package com.example.abubaker_midt2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_NAME = "users_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "FIRST_NAME";
    public static final String COL3 = "SUR_NAME";
    public static final String COL4 = "NAT_ID";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY, " +
                " FIRST_NAME TEXT, SUR_NAME TEXT, NAT_ID TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String userID, String firstName, String surName, String natID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, userID);
        contentValues.put(COL2, firstName);
        contentValues.put(COL3, surName);
        contentValues.put(COL4, natID);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1) {return false;} else {return true;}
    }

    /* Returns only one result */
    public Cursor ViewUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor x = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);
        return x;
    }
    public Integer DeleteUser(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }

}

