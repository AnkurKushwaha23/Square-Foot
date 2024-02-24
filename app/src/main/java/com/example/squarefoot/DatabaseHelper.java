package com.example.squarefoot;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "billing_calculator.db";
    private static final int DATABASE_VERSION = 1;

    // Table name and columns
    public static final String TABLE_NAME = "billing_data";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_LENGTH = "length";
    public static final String COLUMN_WIDTH = "width";
    public static final String COLUMN_UNIT_PRICE = "unit_price";
    public static final String COLUMN_RESULT = "result";
    Context context;

    // Create table SQL statement
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_LENGTH + " REAL, " +
                    COLUMN_WIDTH + " REAL, " +
                    COLUMN_UNIT_PRICE + " REAL, " +
                    COLUMN_RESULT + " REAL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void saveData(String date, String length, String width, String unitPrice, String result) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_DATE, date);
        values.put(COLUMN_LENGTH, Double.parseDouble(length));
        values.put(COLUMN_WIDTH, Double.parseDouble(width));
        values.put(COLUMN_UNIT_PRICE, Double.parseDouble(unitPrice));
        values.put(COLUMN_RESULT, Double.parseDouble(result));

        long insertedId = db.insert(TABLE_NAME, null, values);
        if (insertedId==-1){
            Toast.makeText(context, "Failed to Save", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public void deleteDataFromDatabase(long dataId) {
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_NAME, COLUMN_ID+ " = ?", new String[]{String.valueOf(dataId)});
        if (res == -1) {
            Toast.makeText(context, "Failed to Delete Item.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Item Deleted Successfully.", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        Toast.makeText(context, "All Item Deleted Successfully.", Toast.LENGTH_SHORT).show();
        db.close();
    }
}
