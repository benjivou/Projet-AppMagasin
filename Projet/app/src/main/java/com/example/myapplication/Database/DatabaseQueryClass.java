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



public class DatabaseQueryClass {

    private Context context;
    private static final String TAG = "DatabaseQueryClass";
    public DatabaseQueryClass(Context context){
        this.context = context;
        Log.d(TAG, "DatabaseQueryClass: "+ " COntext done");
    }

    public long insertStudent(EntityEmployee entityEmployeedent){

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

                    return studentList;
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

   /* public Student getStudentByRegNum(long registrationNum){

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        Student student = null;
        try {

            cursor = sqLiteDatabase.query(Config.TABLE_STUDENT, null,
                    Config.COLUMN_STUDENT_REGISTRATION + " = ? ", new String[]{String.valueOf(registrationNum)},
                    null, null, null);

            *//**
                 // If you want to execute raw query then uncomment below 2 lines. And comment out above sqLiteDatabase.query() method.

                 String SELECT_QUERY = String.format("SELECT * FROM %s WHERE %s = %s", Config.TABLE_STUDENT, Config.COLUMN_STUDENT_REGISTRATION, String.valueOf(registrationNum));
                 cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);
             *//*

            if(cursor.moveToFirst()){
                int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_STUDENT_ID));
                String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_NAME));
                long registrationNumber = cursor.getLong(cursor.getColumnIndex(Config.COLUMN_STUDENT_REGISTRATION));
                String phone = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_PHONE));
                String email = cursor.getString(cursor.getColumnIndex(Config.COLUMN_STUDENT_EMAIL));

                student = new Student(id, name, registrationNumber, phone, email);
            }
        } catch (Exception e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return student;
    }

    public long updateStudentInfo(Student student){

        long rowCount = 0;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_STUDENT_NAME, student.getName());
        contentValues.put(Config.COLUMN_STUDENT_REGISTRATION, student.getRegistrationNumber());
        contentValues.put(Config.COLUMN_STUDENT_PHONE, student.getPhoneNumber());
        contentValues.put(Config.COLUMN_STUDENT_EMAIL, student.getEmail());

        try {
            rowCount = sqLiteDatabase.update(Config.TABLE_STUDENT, contentValues,
                    Config.COLUMN_STUDENT_ID + " = ? ",
                    new String[] {String.valueOf(student.getId())});
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowCount;
    }

    public long deleteStudentByRegNum(long registrationNum) {
        long deletedRowCount = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            deletedRowCount = sqLiteDatabase.delete(Config.TABLE_STUDENT,
                                    Config.COLUMN_STUDENT_REGISTRATION + " = ? ",
                                    new String[]{ String.valueOf(registrationNum)});
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deletedRowCount;
    }

    public boolean deleteAllStudents(){
        boolean deleteStatus = false;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            //for "1" delete() method returns number of deleted rows
            //if you don't want row count just use delete(TABLE_NAME, null, null)
            sqLiteDatabase.delete(Config.TABLE_STUDENT, null, null);

            long count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_STUDENT);

            if(count==0)
                deleteStatus = true;

        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return deleteStatus;
    }

    public long getNumberOfStudent(){
        long count = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_STUDENT);
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return count;
    }

    // subjects
    public long insertSubject(Subject subject, long registrationNo){
        long rowId = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_SUBJECT_NAME, subject.getName());
        contentValues.put(Config.COLUMN_SUBJECT_CODE, subject.getCode());
        contentValues.put(Config.COLUMN_SUBJECT_CREDIT, subject.getCredit());
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

    public Subject getSubjectById(long subjectId){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        Subject subject = null;

        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(Config.TABLE_SUBJECT, null,
                    Config.COLUMN_SUBJECT_ID + " = ? ", new String[] {String.valueOf(subjectId)},
                    null, null, null);

            if(cursor!=null && cursor.moveToFirst()){
                String subjectName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SUBJECT_NAME));
                int subjectCode = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_SUBJECT_CODE));
                double subjectCredit = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_SUBJECT_CREDIT));

                subject = new Subject(subjectId, subjectName, subjectCode, subjectCredit);
            }
        } catch (SQLiteException e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return subject;
    }

    public long updateSubjectInfo(Subject subject){

        long rowCount = 0;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_SUBJECT_NAME, subject.getName());
        contentValues.put(Config.COLUMN_SUBJECT_CODE, subject.getCode());
        contentValues.put(Config.COLUMN_SUBJECT_CREDIT, subject.getCredit());

        try {
            rowCount = sqLiteDatabase.update(Config.TABLE_SUBJECT, contentValues,
                    Config.COLUMN_SUBJECT_ID + " = ? ",
                    new String[] {String.valueOf(subject.getId())});
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return rowCount;
    }

    public List<Subject> getAllSubjectsByRegNo(long registrationNo){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        List<Subject> subjectList = new ArrayList<>();
        Cursor cursor = null;
        try{
            cursor = sqLiteDatabase.query(Config.TABLE_SUBJECT,
                    new String[] {Config.COLUMN_SUBJECT_ID, Config.COLUMN_SUBJECT_NAME, Config.COLUMN_SUBJECT_CODE, Config.COLUMN_SUBJECT_CREDIT},
                    Config.COLUMN_REGISTRATION_NUMBER + " = ? ",
                    new String[] {String.valueOf(registrationNo)},
                    null, null, null);

            if(cursor!=null && cursor.moveToFirst()){
                subjectList = new ArrayList<>();
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_SUBJECT_ID));
                    String subjectName = cursor.getString(cursor.getColumnIndex(Config.COLUMN_SUBJECT_NAME));
                    int subjectCode = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_SUBJECT_CODE));
                    double subjectCredit = cursor.getDouble(cursor.getColumnIndex(Config.COLUMN_SUBJECT_CREDIT));

                    subjectList.add(new Subject(id, subjectName, subjectCode, subjectCredit));
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if(cursor!=null)
                cursor.close();
            sqLiteDatabase.close();
        }

        return subjectList;
    }

    public boolean deleteSubjectById(long subjectId) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        int row = sqLiteDatabase.delete(Config.TABLE_SUBJECT,
                Config.COLUMN_SUBJECT_ID + " = ? ", new String[]{String.valueOf(subjectId)});

        return row > 0;
    }

    public boolean deleteAllSubjectsByRegNum(long registrationNum) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        int row = sqLiteDatabase.delete(Config.TABLE_SUBJECT,
                Config.COLUMN_REGISTRATION_NUMBER + " = ? ", new String[]{String.valueOf(registrationNum)});

        return row > 0;
    }

    public long getNumberOfSubject(){
        long count = -1;
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        try {
            count = DatabaseUtils.queryNumEntries(sqLiteDatabase, Config.TABLE_SUBJECT);
        } catch (SQLiteException e){
            Logger.d("Exception: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            sqLiteDatabase.close();
        }

        return count;
    }
*/
}