package com.congp.timekeeping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.congp.timekeeping.data.Shift;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by congp on 11/25/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DB_VER = 1;
    private static final String DB_NAME = "ShiftManager";
    private static final String TB_SHIFT = "tbShift";
    private static final String SHIFT_ID = "id";
    private static final String SHIFT_NAME = "sName";
    private static final String SHIFT_INTIME = "sInTime";
    private static final String SHIFT_DATE = "sDate";

    private static final String SHIFT_OUTTIME = "sOutTime";
    private static final String SHIFT_TOTAL = "sTotalTime";
    private static final String SHIFT_NOTE = "sNote";


    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SHIFT_TABLE = "CREATE TABLE " + TB_SHIFT + "("
                + SHIFT_ID + " INTEGER NOT NULL PRIMARY KEY  AUTOINCREMENT,"
                + SHIFT_NAME + " TEXT,"
                + SHIFT_DATE + " TEXT,"
                + SHIFT_INTIME + " BIGINT,"
                + SHIFT_OUTTIME + " BIGINT,"
                + SHIFT_TOTAL + " INTEGER,"
                + SHIFT_NOTE + " TEXT" + ")";
        db.execSQL(CREATE_SHIFT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_SHIFT);

        // Create tables again
        onCreate(db);
    }
     public int existShift(Shift shift){
         List<Shift> list = new ArrayList<Shift>();
         String selectQuery = "SELECT  * FROM " + TB_SHIFT + " WHERE sDate = '"
                 + shift.getsDate() +"'  AND sName= '"+shift.getsName()+"'" ;
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(selectQuery, null);
         if (cursor.moveToFirst()) {
             do {
                 Shift s = new Shift();
                 s.setId(Integer.parseInt(cursor.getString(0)));
                 s.setsName(cursor.getString(1));
                 s.setsDate(cursor.getString(2));
                 s.setsInTime(cursor.getString(3));
                 s.setsOutTime(cursor.getString(4));
                 s.setsTotalTime(cursor.getDouble(5));
                 s.setsNote(cursor.getString(6));
                 // Adding Shift to list
                 list.add(s);
             } while (cursor.moveToNext());
         }
         // return Shift list
         cursor.close();
         if(list.size()>0){
             return list.get(0).getId();
         }else return -1;

     }


        public void delete(int id) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TB_SHIFT, SHIFT_ID + " = ?",
                    new String[] { String.valueOf(id)});
            db.close();
        }


    // thêm mới một ca
    public void addShift(Shift shift) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SHIFT_NAME, shift.getsName());
        values.put(SHIFT_DATE, shift.getsDate());
        values.put(SHIFT_INTIME, shift.getsInTime());
        values.put(SHIFT_OUTTIME, shift.getsOutTime());
        values.put(SHIFT_TOTAL, shift.getsTotalTime());
        values.put(SHIFT_NOTE, shift.getsNote());
        db.insert(TB_SHIFT, null, values);
        db.close();
    }

    public List<Shift> getShiftOfDate(String date) {
        List<Shift> ShiftList = new ArrayList<Shift>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TB_SHIFT + " WHERE sDate = '" + date+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Shift s = new Shift();
                s.setId(Integer.parseInt(cursor.getString(0)));
                s.setsName(cursor.getString(1));
                s.setsDate(cursor.getString(2));
                s.setsInTime(cursor.getString(3));
                s.setsOutTime(cursor.getString(4));
                s.setsTotalTime(cursor.getDouble(5));
                s.setsNote(cursor.getString(6));
                // Adding Shift to list
                ShiftList.add(s);
            } while (cursor.moveToNext());
        }
        // return Shift list
        cursor.close();
        return ShiftList;
    }



    public List<Shift> getAllShift() {
        List<Shift> list = new ArrayList<Shift>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TB_SHIFT;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Shift s = new Shift();
                s.setId(Integer.parseInt(cursor.getString(0)));
                s.setsName(cursor.getString(1));
                s.setsDate(cursor.getString(2));
                s.setsInTime(cursor.getString(3));
                s.setsOutTime(cursor.getString(4));
                s.setsTotalTime(cursor.getDouble(5));
                s.setsNote(cursor.getString(6));
                // Adding contact to list
                list.add(s);
            } while (cursor.moveToNext());
        }
         cursor.close();
        // return contact list
        return list;
    }
}
