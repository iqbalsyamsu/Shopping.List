package com.isiomas.shop.list.shoppinglist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;

/**
 * Created by Iqbal Syamsu on 4/6/2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    // DATABASE INFORMATION
    private static final String DB_NAME = "shopping.db";
    private static final int DB_VERSION = 1;

    // TABLE INFORMATION
    public static final String TABLE_SHOPPING = "shopping";
    public static final String SHOPPING_ID = "_id";
    public static final String SHOPPING_NAME = "name";
	public static final String SHOPPING_DESCRIPTION= "description";
    public static final String SHOPPING_CATEGORY = "category";
    public static final String SHOPPING_QUANTITY = "quantity";
    public static final String SHOPPING_VALUE = "value";
    public static final String SHOPPING_STATUS = "done";
    public static final String SHOPPING_CREATED_AT = "created_at";
    public static final String SHOPPING_UPDATED_AT = "updated_at";
    public static final String SHOPPING_FAVOURITE = "fav";

    public static final String TABLE_CATEGORY = "category";
    public static final String CATEGORY_ID = "_id";
    public static final String CATEGORY_TITLE = "title";
    public static final String CATEGORY_COLOR = "color";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_CREATED_AT = "created_at";
    public static final String CATEGORY_UPDATED_AT = "updated_at";

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
                + SHOPPING_NAME + " TEXT NOT NULL, "
			    + SHOPPING_DESCRIPTION + " TEXT, "
                + SHOPPING_CATEGORY + " INTEGER DEFAULT 0, "
                + SHOPPING_QUANTITY + " INTEGER DEFAULT 0, "
                + SHOPPING_VALUE + " INTEGER DEFAULT 0, "
                + SHOPPING_STATUS + " INTEGER DEFAULT 0, "
                + SHOPPING_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
                + SHOPPING_UPDATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
                + SHOPPING_FAVOURITE + " INTEGER DEFAULT 0)");
        db.execSQL("CREATE TABLE " + TABLE_CATEGORY + " ("
                + CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CATEGORY_TITLE + " TEXT NOT NULL, "
                + CATEGORY_COLOR + " TEXT, "
                + CATEGORY_STATUS + " INTEGER DEFAULT 0, "
                + CATEGORY_CREATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
                + CATEGORY_UPDATED_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP) ");
    }

    /**
     * This would run after the user updates the app. This is in case
     * you want to modify the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // TODO Auto-generated method stub
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPPING);
        } catch (SQLiteException exception) {
            //Log.i("error boss", "on the next line");
            //exception.printStackTrace();
        }
        onCreate(db);
    }
}
