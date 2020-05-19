package com.kt.std.cars_db.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.kt.std.cars_db.Model.Car;
import com.kt.std.cars_db.Utils.Util;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CARS_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                + Util.KEY_NAME + " TEXT,"
                + Util.KEY_PRICE + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_CARS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addCar(Car car) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, car.getName());
        contentValues.put(Util.KEY_PRICE, car.getPrice());

        db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();

    }

    public Car getCar(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME, new String[] {Util.KEY_ID, Util.KEY_NAME, Util.KEY_PRICE}, Util.KEY_ID + "=?"
        + new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        Car car = new Car(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return car;


    }
}

















