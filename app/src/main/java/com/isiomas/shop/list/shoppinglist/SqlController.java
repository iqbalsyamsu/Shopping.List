package com.isiomas.shop.list.shoppinglist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
//import android.util.Log;

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

    public void insert(String name, String description){
        ContentValues contentValue = new ContentValues();
        contentValue.put(dbHelper.SHOPPING_NAME,name);
        contentValue.put(dbHelper.SHOPPING_DESCRIPTION, description);
        database.insert(dbHelper.TABLE_SHOPPING,null,contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { dbHelper.SHOPPING_ID, dbHelper.SHOPPING_NAME, dbHelper.SHOPPING_DESCRIPTION};
        Cursor cursor = database.query(dbHelper.TABLE_SHOPPING, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetchAll() {
        Cursor cursor = database.query(dbHelper.TABLE_SHOPPING, null, null, null, null, null, null);
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

    public int countRows() {
        String query = "SELECT * FROM " + dbHelper.TABLE_SHOPPING;
        Cursor cursor = database.rawQuery(query, null );
        int j = cursor.getCount();
        //Log.d("DeBe", query);
        //Log.d("DeBe",String.valueOf(j));
        cursor.close();
        return cursor.getCount();
    }
}
