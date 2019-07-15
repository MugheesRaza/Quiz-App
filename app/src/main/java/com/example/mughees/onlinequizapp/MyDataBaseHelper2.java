package com.example.mughees.onlinequizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.NavUtils;
import android.widget.EditText;

import java.net.PortUnreachableException;

/**
 * Created by Mughees on 23-Mar-2018.
 */
public class MyDataBaseHelper2 extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Record.db";
    public static final String TABLE_NAME = "Record";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SCORE";
    public static final String COL_4 = "CNIC";
    public static final String COL_5 = "PHONE";
    public static final String COL_6 = "PROFE";

    public MyDataBaseHelper2(Context context) {
        super(context, DATABASE_NAME, null, 2);


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT ,NAME TEXT,SCORE TEXT,CNIC TEXT,PHONE TEXT,PROFE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF  EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String item2, String item3, String item4, String item5, String item6) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, item2);
        contentValues.put(COL_3, item3);
        contentValues.put(COL_4, item4);
        contentValues.put(COL_5, item5);
        contentValues.put(COL_6, item6);
        long result = db.insert(TABLE_NAME, null, contentValues);


        if (result == -1) {
            return false;
        } else {
            return true;

        }
    }


    public Cursor getListContents() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor data  = db.rawQuery("SELECT * FROM "+DATABASE_NAME, null);
        return data;
    }
}
