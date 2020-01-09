package Controle;

import android.content.Context;

import DAO.AccesLocal;
import Model.EntityEmployee;

public class Controller {

    private static AccesLocal accesLocal;
    private static  Controller instance = null;
    private static EntityEmployee employee;

    public static final Controller getInstance(Context context){

        if(Controller.instance == null){

            Controller.instance = new Controller();
            accesLocal = new AccesLocal(context);
            employee = accesLocal.getLastEmployee();
        }

        return Controller.instance;
    }

    public void createEmployee(int idEmployee,String name,String sex, String password,String role){

        employee = new EntityEmployee(idEmployee,name,sex,password,role);
        accesLocal.add(employee);
    }
}
