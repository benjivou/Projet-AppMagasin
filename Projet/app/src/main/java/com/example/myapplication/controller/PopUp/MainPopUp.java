package com.example.myapplication.controller.PopUp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.myapplication.controller.util.DisplayUtilActivity;
import com.example.myapplication.dao.AisleDAO;
import com.example.myapplication.dao.ArticleDAO;
import com.example.myapplication.dao.EmployeeDAO;
import com.example.myapplication.dao.RoleDAO;
import com.example.myapplication.model.EntityEmployee;

public abstract class MainPopUp extends DisplayUtilActivity {

    protected AisleDAO aisleDAO;
    protected EmployeeDAO employeeDAO;
    protected ArticleDAO articleDAO;


    private EntityEmployee entityEmployee;

    public MainPopUp(EntityEmployee entityEmployee) {
       this.entityEmployee = entityEmployee;
       this.aisleDAO = new AisleDAO(entityEmployee.getRoleEnum(),entityEmployee.getEntityAisle(),this);
       this.employeeDAO= new EmployeeDAO(entityEmployee.getRoleEnum(),entityEmployee.getEntityAisle(),this);
       this.articleDAO = new ArticleDAO(entityEmployee.getRoleEnum(),entityEmployee.getEntityAisle(),this);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public EntityEmployee getEntityEmployee() {
        return entityEmployee;
    }


}
