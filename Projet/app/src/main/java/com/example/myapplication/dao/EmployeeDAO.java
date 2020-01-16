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

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private static final String TAG = "EmployeeDAO";

    /**
     *
     * @param matricule Id of the employee
     * @param context Activity context
     * @return null if the employee didn't exist else the employee
     */
    public static EntityEmployee getByMatricule(int matricule, Context context){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        
        EntityEmployee result = null;

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(ConfigDAO.TABLE_EMPLOYEE, null, ConfigDAO.COLUMN_EMPLOYEE_ID +" = " + matricule , null, null, null, null, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.

             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor!=null)
                if(cursor.moveToFirst()){
                    List<EntityEmployee> studentList = new ArrayList<>();
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_ID));
                        String name = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_NAME));
                        String sex = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_SEX));
                        String password = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_PASSWORD));
                        String role = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_ROLE));


                        studentList.add(new EntityEmployee(id, name, sex, password, role));
                    }   while (cursor.moveToNext());
                    result = studentList.get(0);
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
    public long insertStudent(EntityEmployee entityEmployeedent,Context context){

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


}
