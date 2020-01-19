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
import com.example.myapplication.model.EntityEmployee;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a database access for the employee table
 * @<version 1.0
 * @author Leslie Kiav & Benjamin Vouillon
 */
public class EmployeeDAO {
    private static final String TAG = "EmployeeDAO";

    /**
     *
     * @param matricule Id of the employee
     * @param context Activity context
     * @return null if the employee didn't exist else the employee
     */
    public static EntityEmployee getByMatricule(String matricule, Context context){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        
        EntityEmployee result = null;

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(ConfigDAO.TABLE_EMPLOYEE, null, ConfigDAO.COLUMN_EMPLOYEE_ID +" = " + matricule , null, null, null, null, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.

             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_Employee_ID, Config.COLUMN_Employee_NAME, Config.COLUMN_Employee_REGISTRATION, Config.COLUMN_Employee_EMAIL, Config.COLUMN_Employee_PHONE, Config.TABLE_Employee);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<EntityEmployee> EmployeeList = new ArrayList<>();
                    do {
                        String id = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_ID));
                        String name = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_NAME));
                        String sex = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_SEX));
                        String password = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_PASSWORD));
                        String role = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_ROLE));


                        EmployeeList.add(new EntityEmployee(id, name, sex, password, role));
                    }   while (cursor.moveToNext());
                    result = EmployeeList.get(0);
                    return result;
                }
        } catch (Exception e){
            Log.i(TAG, "getAllEmployee: "+e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return result;
    }

    public static ArrayList< EntityEmployee> getByName(String nameWanted, Context context){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        ArrayList<EntityEmployee> employeeList = new ArrayList<>();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(ConfigDAO.TABLE_EMPLOYEE, null, ConfigDAO.COLUMN_EMPLOYEE_NAME +" = " + nameWanted , null, null, null, null, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.

             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_Employee_ID, Config.COLUMN_Employee_NAME, Config.COLUMN_Employee_REGISTRATION, Config.COLUMN_Employee_EMAIL, Config.COLUMN_Employee_PHONE, Config.TABLE_Employee);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor!=null)
                if(cursor.moveToFirst()){

                    do {
                        String id = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_ID));
                        String name = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_NAME));
                        String sex = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_SEX));
                        String password = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_PASSWORD));
                        String role = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_ROLE));


                        employeeList.add(new EntityEmployee(id, name, sex, password, role));
                    }   while (cursor.moveToNext());

                    return employeeList;
                }
        } catch (Exception e){
            Log.i(TAG, "getAllEmployee: "+e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return employeeList;
    }
    /**
     *
     * @param entityEmployeedent
     * @param context
     * @return
     */
    public static long  insertEmployee(EntityEmployee entityEmployeedent,Context context){

        long id = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConfigDAO.COLUMN_EMPLOYEE_NAME, entityEmployeedent.getName());
        contentValues.put(ConfigDAO.COLUMN_EMPLOYEE_SEX, entityEmployeedent.getSex());
        contentValues.put(ConfigDAO.COLUMN_EMPLOYEE_PASSWORD, entityEmployeedent.getPassword());
        contentValues.put(ConfigDAO.COLUMN_EMPLOYEE_ROLE, entityEmployeedent.getRole());

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

    /**
     * Count the number of Employee in the dB
     * @param context
     * @return
     */
    public static int countEmployee(Context context){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor Count= sqLiteDatabase.rawQuery("select count(*) from"+ ConfigDAO.TABLE_EMPLOYEE, null);
        Count.moveToFirst();
        int count= Count.getInt(0);
        Count.close();
        return count;
    }
    public static boolean  deleteStudentById(int subjectId,Context context) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        int row = sqLiteDatabase.delete(ConfigDAO.TABLE_EMPLOYEE,
                ConfigDAO.COLUMN_EMPLOYEE_ID + " = ? ", new String[]{String.valueOf(subjectId)});

        return row > 0;
    }



}
