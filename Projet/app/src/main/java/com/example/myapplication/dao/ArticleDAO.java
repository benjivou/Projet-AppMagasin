package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.Database.DatabaseHelper;
import com.example.myapplication.config.ConfigDAO;
import com.example.myapplication.model.EntityArticle;

/**
 * This class represent a database access for the employee table
 * @<version 1.0
 * @author Leslie Kiav & Benjamin Vouillon
 */
public class ArticleDAO  {

    private static final String TAG = "ArticleDAO";

    /**
     *
     * @param entityArticle
     * @param context
     * @return
     */
    public static long  insertEmployee(EntityArticle entityArticle, Context context){

        long id = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConfigDAO.COLUMN_ARTICLE_NAME, entityArticle.getName());
        contentValues.put(ConfigDAO.COLUMN_ARTICLE_PRICE, entityArticle.getPrice());
        contentValues.put(ConfigDAO.COLUMN_ARTICLE_QUANTITY, entityArticle.getQuantity());


        try {
            id = sqLiteDatabase.insertOrThrow(ConfigDAO.TABLE_EMPLOYEE, null, contentValues);
        } catch (SQLiteException e){
            Log.d(TAG,"Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return id;
    }

}
