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
import com.example.myapplication.model.EntityEmployee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class represent a database access for the employee table
 * @<version 1.0
 * @author Leslie Kiav & Benjamin Vouillon
 */
public class EmployeeDAO extends ManagerDAO {
    private   final String TAG = "EmployeeDAO";


    public EmployeeDAO(RoleDAO mCurrentRole, EntityAisle mCurrentAisle, Context mExecutionContext) {
        super(mCurrentRole, mCurrentAisle, mExecutionContext);
    }


    /**
     *
     * @param matricule Id of the employee
     * @return null if the employee didn't exist else the employee
     */
    public  EntityEmployee getByMatricule(String matricule){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this.mExecutionContext);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        EntityEmployee result = null;

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(ConfigDAO.TABLE_EMPLOYEE,
                   null,
                    ConfigDAO.COLUMN_EMPLOYEE_ID +" = ?"  ,
                    new String[]{matricule}, null, null, null);

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
                        int aisleId = cursor.getInt(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_AISLE_ID));

                        AisleDAO aisleDAO = new AisleDAO(this.mCurrentRole,this.mCurrentAisle,this.mExecutionContext);
                        EntityAisle entityAisle = aisleDAO.getByMatricule(aisleId);


                        result = new EntityEmployee(id, name, sex, password, role,entityAisle);
                    }   while (cursor.moveToNext());

                    return result;
                }
        } catch (Exception e){
            Log.i(TAG, "getAllEmployee: "+e.getMessage());
            Toast.makeText(this.mExecutionContext, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return result;
    }

    /**
     * Get a employee
     * @param nameWanted
     * @return
     */
    public  ArrayList< EntityEmployee> getByName(String nameWanted){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this.mExecutionContext);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        ArrayList<EntityEmployee> employeeList = new ArrayList<>();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(ConfigDAO.TABLE_EMPLOYEE,
                    null,
                    ConfigDAO.COLUMN_EMPLOYEE_NAME +" LIKE '" + nameWanted+"'" ,
                    null, null, null, null, null);

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
                        int aisleId = cursor.getInt(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_AISLE_ID));

                        AisleDAO aisleDAO = new AisleDAO(this.mCurrentRole,this.mCurrentAisle,this.mExecutionContext);
                        EntityAisle entityAisle = aisleDAO.getByMatricule(aisleId);

                        employeeList.add(new EntityEmployee(id, name, sex, password, role,entityAisle));
                    }   while (cursor.moveToNext());

                    return employeeList;
                }
        } catch (Exception e){
            Log.i(TAG, "getAllEmployee: "+e.getMessage());
            Toast.makeText(this.mExecutionContext, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return employeeList;
    }
    /**
     * Insert employee
     * @param entityEmployeedent

     * @return
     */
    public   long  insertEmployee(EntityEmployee entityEmployeedent  ){

        long id = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this.mExecutionContext);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConfigDAO.COLUMN_EMPLOYEE_NAME, entityEmployeedent.getName());
        contentValues.put(ConfigDAO.COLUMN_EMPLOYEE_SEX, entityEmployeedent.getSex());
        contentValues.put(ConfigDAO.COLUMN_EMPLOYEE_PASSWORD, entityEmployeedent.getPassword());
        contentValues.put(ConfigDAO.COLUMN_EMPLOYEE_ROLE, entityEmployeedent.getRole());
        contentValues.put(ConfigDAO.COLUMN_EMPLOYEE_ID, entityEmployeedent.getIdEmployee());
        contentValues.put(ConfigDAO.COLUMN_EMPLOYEE_AISLE_ID, entityEmployeedent.getEntityAisle().getIdAisle());

        try {
            id = sqLiteDatabase.insertOrThrow(ConfigDAO.TABLE_EMPLOYEE, null, contentValues);
        } catch (SQLiteException e){
            Log.d(TAG,"Exception: " + e.getMessage());
            Toast.makeText(this.mExecutionContext, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return id;
    }

    /**
     * Count the number of Employee in the dB
     * @return
     */
    public   int countEmployee(){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this.mExecutionContext);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor Count= sqLiteDatabase.rawQuery("select count(*) from"+ ConfigDAO.TABLE_EMPLOYEE, null);
        Count.moveToFirst();
        int count= Count.getInt(0);
        Count.close();
        return count;
    }

    /**
     * Delete a employee with id employee
     * @param subjectId
     * @return
     */
    public   boolean  deleteEmployeeById(int subjectId) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this.mExecutionContext);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        int row = sqLiteDatabase.delete(ConfigDAO.TABLE_EMPLOYEE,
                ConfigDAO.COLUMN_EMPLOYEE_ID + " = ? ", new String[]{String.valueOf(subjectId)});

        return row > 0;
    }

    public   ArrayList<EntityEmployee> getAllEmployee(){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this.mExecutionContext);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {

            cursor = sqLiteDatabase.query(ConfigDAO.TABLE_EMPLOYEE, null, null, null, null, null, null, null);

            /**
             // If you want to execute raw query then uncomment below 2 lines. And comment out above line.

             String SELECT_QUERY = String.format("SELECT %s, %s, %s, %s, %s FROM %s", Config.COLUMN_STUDENT_ID, Config.COLUMN_STUDENT_NAME, Config.COLUMN_STUDENT_REGISTRATION, Config.COLUMN_STUDENT_EMAIL, Config.COLUMN_STUDENT_PHONE, Config.TABLE_STUDENT);
             cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             */

            if(cursor!=null)
                if(cursor.moveToFirst()){
                    ArrayList<EntityEmployee> studentList = new ArrayList<>();
                    do {
                        String id = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_ID));
                        String name = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_NAME));
                        String role = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_ROLE));
                        String password = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_PASSWORD));
                        String sex = cursor.getString(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_SEX));
                        int aisleId = cursor.getInt(cursor.getColumnIndex(ConfigDAO.COLUMN_EMPLOYEE_AISLE_ID));

                        AisleDAO aisleDAO = new AisleDAO(this.mCurrentRole,this.mCurrentAisle,this.mExecutionContext);
                        EntityAisle entityAisle = aisleDAO.getByMatricule(aisleId);

                        studentList.add(new EntityEmployee(id, name, sex, password, role,entityAisle));
                    }   while (cursor.moveToNext());

                    return studentList;
                }
        } catch (Exception e){
            Log.d(TAG, "getAllEmployee: Exception: " + e.getMessage());
            Toast.makeText(this.mExecutionContext, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return new ArrayList<>();
    }

    public   void insertUpdate(EntityEmployee entityEmployee  ) {

        long rowCount = 0;

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this.mExecutionContext);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConfigDAO.COLUMN_EMPLOYEE_ID,entityEmployee.getIdEmployee());
        contentValues.put(ConfigDAO.COLUMN_EMPLOYEE_ROLE, entityEmployee.getRole());
        contentValues.put(ConfigDAO.COLUMN_EMPLOYEE_PASSWORD, entityEmployee.getPassword());
        contentValues.put(ConfigDAO.COLUMN_EMPLOYEE_SEX, entityEmployee.getSex());
        contentValues.put(ConfigDAO.COLUMN_EMPLOYEE_NAME, entityEmployee.getName());

        try {
            rowCount = sqLiteDatabase.update(ConfigDAO.TABLE_EMPLOYEE, contentValues,
                    ConfigDAO.COLUMN_EMPLOYEE_ID + " = ? ",
                    new String[] {String.valueOf(entityEmployee.getIdEmployee())});
        } catch (SQLiteException e){
            Log.d(TAG, "insertUpdate: ");
            Toast.makeText(this.mExecutionContext, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }


    }

}
