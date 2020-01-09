package DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import DAO.DatabaseQueryDao;
import Model.EntityEmployee;

public class AccesLocal {

    private String nameBase = "bdCoach.sqlite";
    private Integer versionBase = 1;
    private DatabaseQueryDao dbDao;
    private SQLiteDatabase bd;

    /**
     * Constructor
     * @param context
     */
    public AccesLocal(Context context ){

        dbDao = new DatabaseQueryDao(context, nameBase,null,versionBase );
    }

    /**
     * Add a employee in bdd
     * @param employee
     */
    public void  add(EntityEmployee employee){

        bd = dbDao.getWritableDatabase();
        String req = " insert into Employee(idEmployee,name,sex,password,role) values ";
        req += "("+employee.getIdEmployee()+",\""+employee.getName()+"\",\""+employee.getSex()+"\",\""+employee.getPassword()+"\",\""+employee.getRole()+"\")";
        bd.execSQL(req);
    }

    /**
     * Get Last Employee in bdd
     * @return
     */
    public EntityEmployee getLastEmployee(){

        bd = dbDao.getReadableDatabase();
        EntityEmployee employee = null;
        String req = "select * from Employee";
        Cursor cursor = bd.rawQuery(req,null);
        cursor.moveToLast();

        if(!cursor.isAfterLast()){
            int idEmployee = cursor.getInt(1);
            String name = cursor.getString(2);
            String sex = cursor.getString(3);
            String password = cursor.getString(4);
            String role = cursor.getString(5);
            employee = new EntityEmployee(idEmployee,name,sex,password,role);

        }
        cursor.close();
        return employee;
    }
}
