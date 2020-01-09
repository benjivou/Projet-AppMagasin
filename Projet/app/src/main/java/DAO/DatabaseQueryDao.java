package DAO;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseQueryDao  extends SQLiteOpenHelper {

    //propreties

    private String create = "create table Employee ("
            + "idEmployee INTEGER PRIMARY KEY,"
            + "name TEXT NOT NULL,"
            + "sex TEXT NOT NULL,"
            + "password TEXT NOT NULL,"
            + "role TEXT NOT NULL); ";


    /**
     * Constructor
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public DatabaseQueryDao( Context context,String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * If we change bdd
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL(create);
    }

    /**
     * If we change the version
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
