package com.example.myapplication.Database;

import android.content.Context;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myapplication.config.ConfigDAO;

import java.util.logging.Logger;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static DatabaseHelper databaseHelper;

    // All Static variables
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = ConfigDAO.DB;

    // Constructor
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "DatabaseHelper: " + "Connexion done");
    }

    public static DatabaseHelper getInstance(Context context) {
        if(databaseHelper==null){
            synchronized (DatabaseHelper.class) {
                if(databaseHelper==null)
                    databaseHelper = new DatabaseHelper(context);
            }
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create tables SQL execution
        // Table Employee
        String CREATE_EMPLOYEE_TABLE = "CREATE TABLE " + ConfigDAO.TABLE_EMPLOYEE + "("
                + ConfigDAO.COLUMN_EMPLOYEE_ID + " INTEGER PRIMARY KEY UNIQUE, "
                + ConfigDAO.COLUMN_EMPLOYEE_NAME + " TEXT NOT NULL, "
                + ConfigDAO.COLUMN_EMPLOYEE_SEX + " TEXT NOT NULL, "
                + ConfigDAO.COLUMN_EMPLOYEE_PASSWORD + " TEXT NOT NULL, " //nullable
                + ConfigDAO.COLUMN_EMPLOYEE_ROLE + " TEXT NOT NULL" //nullable
                + ")";
/*
        String CREATE_ARTICLE_TABLE = "CREATE TABLE " + Config.TABLE_ARTICLE + "("
                + Config.COLUMN_ARTICLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_REGISTRATION_NUMBER + " INTEGER NOT NULL, "
                + Config.COLUMN_ARTICLE_NAME + " TEXT NOT NULL, "
                + Config.COLUMN_ARTICLE_CODE + " INTEGER NOT NULL, "
                + Config.COLUMN_ARTICLE_CREDIT + " REAL, " //nullable
                + "FOREIGN KEY (" + Config.COLUMN_REGISTRATION_NUMBER + ") REFERENCES " + Config.TABLE_EMPLOYEE + "(" + Config.COLUMN_EMPLOYEE_REGISTRATION + ") ON UPDATE CASCADE ON DELETE CASCADE, "
                + "CONSTRAINT " + Config.EMPLOYEE_SUB_CONSTRAINT + " UNIQUE (" + Config.COLUMN_REGISTRATION_NUMBER + "," + Config.COLUMN_ARTICLE_CODE + ")"
                + ")";*/

        db.execSQL(CREATE_EMPLOYEE_TABLE);




       /* db.execSQL(CREATE_SUBJECT_TABLE);*/
        // TO-DO

        Log.d(TAG, "onCreate: "+ "DB Create");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + ConfigDAO.TABLE_EMPLOYEE);
        /*db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_SUBJECT);*/

        // Create tables again
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        //enable foreign key constraints like ON UPDATE CASCADE, ON DELETE CASCADE
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    /*
   Check if the root user exist
   if not create 1
    */

}
