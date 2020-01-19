package com.example.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;


import com.example.myapplication.config.ConfigDAO;
import com.example.myapplication.model.EntityEmployee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.security.auth.Subject;


public class DatabaseQueryClass {

    private Context context;
    private static final String TAG = "DatabaseQueryClass";
    public DatabaseQueryClass(Context context){
        this.context = context;
        Log.d(TAG, "DatabaseQueryClass: "+ " COntext done");
    }

    public long insertEmployee(EntityEmployee entityEmployeedent){

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

    public List<EntityEmployee> getAllEmployee(){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(ConfigDAO.TABLE_EMPLOYEE, null, null, null, null, null, null, null);

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

                    return EmployeeList;
                }
        } catch (Exception e){
            Log.i(TAG, "getAllEmployee: "+e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Collections.emptyList();
    }

  /*  public Employee getEmployeeByRegNum(long registrationNum){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        Employee Employee = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_Employee, null,
                    Config.COLUMN_Employee_REGISTRATION + " = ? ", new String[]{String.valueOf(registrationNum)},
                    null, null, null);

            *//**
                 // If you want to execute raw query then uncomment below 2 lines. And comment out above sqLiteDatabase.query() method.

                 String SELECT_QUERY = String.format("SELECT * FROM %s WHERE %s = %s", Config.TABLE_Employee, Config.COLUMN_Employee_REGISTRATION, String.valueOf(registrationNum));
                 cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             *//*

            if(cursor.moveToFirst()){
                int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_Employee_ID));
                String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_Employee_NAME));
                long registrationNumber = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_Employee_REGISTRATION));
                String phone = cursor.getString(cursor.getColumnIndex(Config.COLUMN_Employee_PHONE));
                String email = cursor.getString(cursor.getColumnIndex(Config.COLUMN_Employee_EMAIL));

                Employee = new Employee(id, name, registrationNumber, phone, email);
            }
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return Employee;
    }*/

    /*public long updateEmployeeInfo(Employee Employee){

        long rowCount = 0;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_Employee_NAME, Employee.getName());
        contentValues.put(Config.COLUMN_Employee_REGISTRATION, Employee.getRegistrationNumber());
        contentValues.put(Config.COLUMN_Employee_PHONE, Employee.getPhoneNumber());
        contentValues.put(Config.COLUMN_Employee_EMAIL, Employee.getEmail());

        try {
            rowCount = sqLiteDatabase.update(Config.TABLE_Employee, contentValues,
                    Config.COLUMN_Employee_ID + " = ? ",
                    new String[] {String.valueOf(Employee.getId())});
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowCount;
    }
*/


/*
    public long getNumberOfEmployee(){
        long count = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_Employee);
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return count;
    }*/

   /* // subjects
    public long insertEmployee(Subject subject, long registrationNo){
        long rowId = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConfigDAO.COLUMN_SUBJECT_NAME, subject.getName());
        contentValues.put(ConfigDAO.COLUMN_SUBJECT_CODE, subject.getCode());
        contentValues.put(ConfigDA.COLUMN_SUBJECT_CREDIT, subject.getCredit());
        contentValues.put(Config.COLUMN_REGISTRATION_NUMBER, registrationNo);

        try {
            rowId = sqLiteDatabase.insertOrThrow(Config.TABLE_SUBJECT, null, contentValues);
        } catch (SQLiteException e){
            Logger.d(e);
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowId;
    }
*/





   /* public boolean deleteAllSubjectsByRegNum(long registrationNum) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        int row = sqLiteDatabase.delete(Config.TABLE_SUBJECT,
                Config.COLUMN_REGISTRATION_NUMBER + " = ? ", new String[]{String.valueOf(registrationNum)});

        return row > 0;
    }*/



}