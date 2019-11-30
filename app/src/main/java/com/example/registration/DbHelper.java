package com.example.registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mydb";
    public static final String TABLE_NAME = "my_table";
    public static final String COL_1 = "id";
    public static final String COL_2 = "name";
    public static final String COL_3 = "email";
    public static final String COL_4 = "password";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+ TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT , email TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertdata(String name, String email, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(COL_2,name);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,password);
        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getdata(String email, String password){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        //String[] whereArgs = {uid};
        //Cursor cursor = sqLiteDatabase.rawQuery("select * from "+TABLE_NAME, new String[]{COL_1 + "=?" + new String[]{id}});
        String query="select * from  "+TABLE_NAME+" where "+COL_3+" = '"+email+ "' and "+COL_4+ " = '"+password+"'";
        Log.d("query",":"+query);
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        // sqLiteDatabase.close();
        return  cursor;
    }
}
