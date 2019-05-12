package com.example.myapplication;

import android.content.ContentResolver;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CustomDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "userdb";
    public static final int DATABASE_VERSION = 1;


    public CustomDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATE TABLE TABLENAME(COLUMN INTEGER PRIMARY KEY AUTOINCREMENT,
        // COLUMN VARCHAR(255));
        db.execSQL(
                "CREATE TABLE "+
                        TableConstants.UserDetails.TABLE_NAME+
                        " ("+
                        TableConstants.UserDetails._ID+
                        " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        TableConstants.UserDetails.NAME_COLUMN+
                        " VARCHAR(255), "+
                        TableConstants.UserDetails.EMAIL_COLUMN+
                        " VARCHAR(255), "+
                        TableConstants.UserDetails.BIRTHDAY_COLUMN+
                        " DATE, "+
                        TableConstants.UserDetails.COUNTRY_COLUMN+
                        " VARCHAR(255), "+
                        TableConstants.UserDetails.PASS_COLUMN+
                        " VARCHAR(255), "+
                        TableConstants.UserDetails.ADDRESS_COLUMN+
                        " VARCHAR(255), "+
                        TableConstants.UserDetails.GENDER_COLUMN+
                        " VARCHAR(255));"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(
          " DROP TABLE IF EXISTS "+ TableConstants.UserDetails.TABLE_NAME
        );
        onCreate(db);
    }
}
