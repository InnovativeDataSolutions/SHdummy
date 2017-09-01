package com.ids.smarthome;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zaid on 7/31/17.
 */
public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "smarthome.db";
    public static final String table = "homeinfo";
    public static final String col1 = "homeid";
    public static final String col2 = "username";
    public static final String col3 = "gateway";
    public static final String col4 = "ip";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_String = "CREATE TABLE " + table + "(" + col1 + " TEXT," + col2 + " TEXT," + col3 + " TEXT," + col4 + " TEXT" + ")";
        db.execSQL(SQL_String);
    }

    //String SQL_String2 = "CREATE TABLE " + table2 + "(" + col1ui + " TEXT" + col2ui + " TEXT" + col3ui + " TEXT" + col4ui + " TEXT" + col5ui + " TEXT" + col6ui + " TEXT" + col7ui + " TEXT" + ")";

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + table);
        onCreate(db);

    }

    public boolean inserthomeinfo(String homeid,String username,String gateway,String ipadd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1, homeid);
        contentValues.put(col2, username);
        contentValues.put(col3, gateway);
        contentValues.put(col4, ipadd);
        Long result = db.insert(table, null, contentValues);

        if (result == -1){
            return false;
        }
        else{
            return true;
        }

    }

    public Cursor getip() {
        Cursor cursor = null;
        //String empName = "";
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery("SELECT * FROM homeinfo", null);
//            if(cursor.getCount() > 0) {
//                cursor.moveToFirst();
//                empName = cursor.getString(cursor.getColumnIndex("ip"));
//            }
            return cursor;
        }finally {
            //cursor.close();
        }
    }
}
