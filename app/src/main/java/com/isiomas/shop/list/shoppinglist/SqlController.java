package com.isiomas.shop.list.shoppinglist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ConcurrentModificationException;

/**
 * Created by Iqbal Syamsu on 4/7/2015.
 */
public class SqlController {
    private DataBaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public SqlController(Context c) {
        context = c;
    }

    public SqlController open() throws SQLException {
        dbHelper = new DataBaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name){
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbHelper.SHOPPING_NAME,name);
        database.insert(dbHelper.TABLE_SHOPPING,null,contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { dbHelper.SHOPPING_ID, dbHelper.SHOPPING_NAME};
        Cursor cursor = database.query(dbHelper.TABLE_SHOPPING, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.SHOPPING_NAME, name);
        //contentValues.put(dbHelper.SHOPPING_CATEGORY);
        int i = database.update(dbHelper.TABLE_SHOPPING, contentValues,
                dbHelper.SHOPPING_ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(dbHelper.TABLE_SHOPPING, dbHelper.SHOPPING_ID + "=" + _id, null);
    }

}