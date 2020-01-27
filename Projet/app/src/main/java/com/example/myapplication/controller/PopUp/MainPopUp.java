package com.example.myapplication.controller.PopUp;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.controller.ListeActivity;
import com.example.myapplication.controller.util.DisplayUtilActivity;
import com.example.myapplication.controller.util.button.listActivity.ButtonPanel;
import com.example.myapplication.dao.AisleDAO;
import com.example.myapplication.dao.ArticleDAO;
import com.example.myapplication.dao.EmployeeDAO;
import com.example.myapplication.model.EntityAisle;
import com.example.myapplication.model.EntityEmployee;

import java.util.ArrayList;

public abstract class MainPopUp extends Dialog {

    // Attributs
    protected AisleDAO mAisleDAO;
    protected EmployeeDAO mEmployeeDAO;
    protected ArticleDAO mArticleDAO;
    protected Activity mActivity;

    private EntityEmployee mEntityEmployee;

    protected ButtonPanel mCOwner;



    public MainPopUp(EntityEmployee entityEmployee, ButtonPanel activity) {
        super(activity, R.style.Theme_AppCompat);
        this.mEntityEmployee = entityEmployee;
        this.mActivity = activity;
        this.mCOwner =activity;
       this.mAisleDAO = new AisleDAO(entityEmployee.getRoleEnum(),entityEmployee.getEntityAisle(),this.mActivity);
       this.mEmployeeDAO = new EmployeeDAO(entityEmployee.getRoleEnum(),entityEmployee.getEntityAisle(),this.mActivity);
       this.mArticleDAO = new ArticleDAO(entityEmployee.getRoleEnum(),entityEmployee.getEntityAisle(),this.mActivity);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public EntityEmployee getEntityEmployee() {
        return mEntityEmployee;
    }

    protected ArrayList<String> modifyType(ArrayList<EntityAisle> listAisle){

        ArrayList <EntityAisle> listAisle1 = listAisle;
        ArrayList <String> newList = new ArrayList<String>();

        for ( EntityAisle entityAisle: listAisle1) {
            newList.add(entityAisle.getName());

        }

        return newList;
    }
}
