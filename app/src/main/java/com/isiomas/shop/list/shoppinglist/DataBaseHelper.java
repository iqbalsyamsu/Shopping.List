package com.isiomas.shop.list.shoppinglist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Iqbal Syamsu on 4/6/2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    // DATABASE INFORMATION
    private static final String DB_NAME = "shopping.db";
    private static final int DB_VERSION = 2;

    // TABLE INFORMATTION
    public static final String TABLE_SHOPPING = "shopping";
    public static final String SHOPPING_ID = "id";
    public static final String SHOPPING_NAME = "name";
	public static final String SHOPPING_DESCRIPTION= "description";
    public static final String SHOPPING_CATEGORY = "category";
    public static final String SHOPPING_DONE = "done";
    public static final String SHOPPING_CREATED_AT = "created_at";
    public static final String SHOPPING_UPDATED_AT = "updated_at";
    public static final String SHOPPING_FAVOURITE = "fav";

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * This runs once after the installation and creates a database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_SHOPPING + " ("
                + SHOPPING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SHOPPING_NAME + " TEXT, "
			 + SHOPPING_DESCRIPTION + " TEXT, "
                + SHOPPING_CATEGORY + " INTEGER, "
                + SHOPPING_DONE + " INTEGER, "
                + SHOPPING_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
                + SHOPPING_UPDATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
                + SHOPPING_FAVOURITE + " INTEGER )");

    }

    /**
     * This would run after the user updates the app. This is in case
     * you want to modify the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(db);
    }
}
