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

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a database access for the aisle table
 * @<version 1.0
 * @author Leslie Kiav & Benjamin Vouillon
 */
public class AisleDAO extends ManagerDAO{

    private static final String TAG = "AisleDAO";


    /**
     * Insert a aisle
     * @param entityAisle
     * @param context
     * @return
     */
    public static long  insertAisle(EntityAisle entityAisle, Context context){

        long id = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConfigDAO.COLUMN_RAYON_NAME, entityAisle.getName());
        contentValues.put(ConfigDAO.COLUMN_RAYON_EMPLOYEE, entityAisle.getEmployee().getIdEmployee()
        );



        try {
            id = sqLiteDatabase.insertOrThrow(ConfigDAO.TABLE_ARTICLE, null, contentValues);
        } catch (SQLiteException e){
            Log.d(TAG,"Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return id;
    }

    /**
     * Delete a aisle
     * @param subjectId
     * @param context
     * @return
     */
    public static boolean  deleteArticleById(int subjectId,Context context) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        int row = sqLiteDatabase.delete(ConfigDAO.TABLE_RAYON,
                ConfigDAO.COLUMN_RAYON_ID + " = ? ", new String[]{String.valueOf(subjectId)});

        return row > 0;
    }

    /**
     * Count the number of aisle in the dB
     * @param context
     * @return
     */
    public static int countArticle(Context context){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor Count= sqLiteDatabase.rawQuery("select count(*) from"+ ConfigDAO.TABLE_RAYON, null);
        Count.moveToFirst();
        int count= Count.getInt(0);
        Count.close();
        return count;
    }

    /**
     * Get a aisle by id
     * @param matricule Id of the aisle
     * @param context Activity context
     * @return null if the aisle didn't exist else the aisle
     */
    public static EntityAisle getByMatricule(String matricule, Context context){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        EntityAisle result = null;

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(ConfigDAO.TABLE_RAYON, null, ConfigDAO.COLUMN_RAYON_ID +" = " + matricule , null, null, null, null, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.

             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_Employee_ID, Config.COLUMN_Employee_NAME, Config.COLUMN_Employee_REGISTRATION, Config.COLUMN_Employee_EMAIL, Config.COLUMN_Employee_PHONE, Config.TABLE_Employee);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<EntityAisle> AisleList = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(ConfigDAO.COLUMN_RAYON_ID));
                        String name = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_RAYON_NAME));



                        AisleList.add(new EntityAisle(id, name));
                    }   while (cursor.moveToNext());
                    result = AisleList.get(0);
                    return result;
                }
        } catch (Exception e){
            Log.i(TAG, "getAllAisle: "+e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return result;
    }

    /**
     * Get a aisle by name
     * @param nameWanted
     * @param context
     * @return
     */
    public static ArrayList< EntityAisle> getByName(String nameWanted, Context context){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        ArrayList<EntityAisle> AisleList = new ArrayList<>();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(ConfigDAO.TABLE_RAYON, null, ConfigDAO.COLUMN_RAYON_NAME+" = " + nameWanted , null, null, null, null, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.

             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_Employee_ID, Config.COLUMN_Employee_NAME, Config.COLUMN_Employee_REGISTRATION, Config.COLUMN_Employee_EMAIL, Config.COLUMN_Employee_PHONE, Config.TABLE_Employee);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor!=null)
                if(cursor.moveToFirst()){

                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(ConfigDAO.COLUMN_RAYON_ID));
                        String name = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_RAYON_NAME));


                        AisleList.add(new EntityAisle(id, name));
                    }   while (cursor.moveToNext());

                    return AisleList;
                }
        } catch (Exception e){
            Log.i(TAG, "getAllAisle: "+e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return AisleList;
    }

}
