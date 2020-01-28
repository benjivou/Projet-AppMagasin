package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.Database.DatabaseHelper;
import com.example.myapplication.config.ConfigDAO;
import com.example.myapplication.model.EntityAisle;
import com.example.myapplication.model.EntityArticle;
import com.example.myapplication.model.EntityEmployee;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a database access for the employee table
 * @<version 1.0
 * @author Leslie Kiav & Benjamin Vouillon
 */
public class ArticleDAO  extends ManagerDAO{

    private  final String TAG = "ArticleDAO";

    public ArticleDAO(RoleDAO mCurrentRole, EntityAisle mCurrentAisle, Context mExecutionContext) {
        super(mCurrentRole, mCurrentAisle, mExecutionContext);
    }

    /**
     * Insert a article
     * @param entityArticle

     * @return
     */
    public  long  insertArticle(EntityArticle entityArticle){

        long id = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this.mExecutionContext);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConfigDAO.COLUMN_ARTICLE_NAME, entityArticle.getName());
        contentValues.put(ConfigDAO.COLUMN_ARTICLE_PRICE, entityArticle.getPrice());
        contentValues.put(ConfigDAO.COLUMN_ARTICLE_QUANTITY, entityArticle.getQuantity());
        contentValues.put(ConfigDAO.COLUMN_ARTICLE_AISLE_ID, entityArticle.getEntityAisle().getIdAisle());


        try {
            id = sqLiteDatabase.insertOrThrow(ConfigDAO.TABLE_ARTICLE, null, contentValues);
        } catch (SQLiteException e){
            Log.d(TAG,"Exception: " + e.getMessage());
            Toast.makeText(this.mExecutionContext, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return id;
    }

    /**
     * Delete a article
     * @param subjectId

     * @return
     */
    public  boolean  deleteArticleById(int subjectId) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this.mExecutionContext);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        int row = sqLiteDatabase.delete(ConfigDAO.TABLE_ARTICLE,
                ConfigDAO.COLUMN_ARTICLE_ID + " = ? ", new String[]{String.valueOf(subjectId)});

        return row > 0;
    }
    /**
     * Count the number of article in the dB

     * @return
     */
    public  int countArticle(){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this.mExecutionContext);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor Count= sqLiteDatabase.rawQuery("select count(*) from"+ ConfigDAO.TABLE_ARTICLE, null);
        Count.moveToFirst();
        int count= Count.getInt(0);
        Count.close();
        return count;
    }
    
    /**
     * Get a employee
     * @param nameWanted

     * @return
     */
    public  ArrayList< EntityArticle> getByName(String nameWanted ){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this.mExecutionContext);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        ArrayList<EntityArticle> ArticleList = new ArrayList<>();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(ConfigDAO.TABLE_ARTICLE,
                    null, ConfigDAO.COLUMN_ARTICLE_NAME+" LIKE '" + nameWanted+"'" , null, null, null, null, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.

             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_Employee_ID, Config.COLUMN_Employee_NAME, Config.COLUMN_Employee_REGISTRATION, Config.COLUMN_Employee_EMAIL, Config.COLUMN_Employee_PHONE, Config.TABLE_Employee);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor!=null)
                if(cursor.moveToFirst()){

                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(ConfigDAO.COLUMN_ARTICLE_ID));
                        String name = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_ARTICLE_NAME));
                        float price = cursor.getFloat(cursor.getColumnIndex(ConfigDAO.COLUMN_ARTICLE_PRICE));
                        int quantity = cursor.getInt(cursor.getColumnIndex(ConfigDAO.COLUMN_ARTICLE_QUANTITY));
                        int aisleId = cursor.getInt(cursor.getColumnIndex(ConfigDAO.COLUMN_ARTICLE_AISLE_ID));

                        AisleDAO aisleDAO = new AisleDAO(this.mCurrentRole,this.mCurrentAisle,this.mExecutionContext);
                        EntityAisle entityAisle = aisleDAO.getByMatricule(aisleId);


                        ArticleList.add(new EntityArticle(id, name, price, quantity,entityAisle));
                    }   while (cursor.moveToNext());

                    for (EntityArticle article : ArticleList){
                        Log.d(TAG, "getByName: listArticle" + ArticleList);
                    }

                   

                    return ArticleList;
                }
        } catch (Exception e){
            Log.i(TAG, "getAllArticle: "+e.getMessage());
            Toast.makeText(this.mExecutionContext, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return ArticleList;
    }

    public  ArrayList< EntityArticle> getAll( ){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this.mExecutionContext);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        ArrayList<EntityArticle> ArticleList = new ArrayList<>();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(ConfigDAO.TABLE_ARTICLE, null, null, null, null, null, null, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.

             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_Employee_ID, Config.COLUMN_Employee_NAME, Config.COLUMN_Employee_REGISTRATION, Config.COLUMN_Employee_EMAIL, Config.COLUMN_Employee_PHONE, Config.TABLE_Employee);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor!=null)
                if(cursor.moveToFirst()){

                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(ConfigDAO.COLUMN_ARTICLE_ID));
                        String name = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_ARTICLE_NAME));
                        float price = cursor.getFloat(cursor.getColumnIndex(ConfigDAO.COLUMN_ARTICLE_PRICE));
                        int quantity = cursor.getInt(cursor.getColumnIndex(ConfigDAO.COLUMN_ARTICLE_QUANTITY));
                        int articleId = cursor.getInt(cursor.getColumnIndex(ConfigDAO.COLUMN_ARTICLE_AISLE_ID));


                        ArticleList.add(new EntityArticle(
                                id,
                                name,
                                price,
                                quantity,
                                new AisleDAO(
                                        this.mCurrentRole,
                                        this.mCurrentAisle,
                                        this.mExecutionContext).getByMatricule(articleId)
                        ));
                    }   while (cursor.moveToNext());

                    return ArticleList;
                }
        } catch (Exception e){
            Log.i(TAG, "getAllArticle: "+e.getMessage());
            Toast.makeText(this.mExecutionContext, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return ArticleList;
    }

    public   void insertUpdate(EntityArticle entityArticle  ) {

        long rowCount = 0;

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this.mExecutionContext);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConfigDAO.COLUMN_ARTICLE_NAME, entityArticle.getName());
        contentValues.put(ConfigDAO.COLUMN_ARTICLE_PRICE, entityArticle.getPrice());
        contentValues.put(ConfigDAO.COLUMN_ARTICLE_QUANTITY, entityArticle.getQuantity());
        contentValues.put(ConfigDAO.COLUMN_ARTICLE_AISLE_ID, entityArticle.getEntityAisle().getIdAisle());

        try {
            rowCount = sqLiteDatabase.update(ConfigDAO.TABLE_ARTICLE, contentValues,
                    ConfigDAO.COLUMN_ARTICLE_ID + " = ? ",
                    new String[] {String.valueOf( entityArticle.getIdArticle())});
        } catch (SQLiteException e){
            Log.d(TAG, "insertUpdate: ");
            Toast.makeText(this.mExecutionContext, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }


    }

}
