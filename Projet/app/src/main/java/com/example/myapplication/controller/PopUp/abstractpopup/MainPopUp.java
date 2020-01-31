package com.example.myapplication.controller.PopUp.abstractpopup;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.controller.util.button.listActivity.ControlOnglet;
import com.example.myapplication.dao.AisleDAO;
import com.example.myapplication.dao.ArticleDAO;
import com.example.myapplication.dao.EmployeeDAO;
import com.example.myapplication.model.EntityAisle;
import com.example.myapplication.model.EntityEmployee;

import java.util.ArrayList;

public abstract class MainPopUp extends Dialog implements View.OnClickListener {
    private static final String TAG_BUTTON_SUBMIT = "submit";

    // Front
    protected ImageButton mButtonSubmit;
    // Attributs
    protected AisleDAO mAisleDAO;
    protected EmployeeDAO mEmployeeDAO;
    protected ArticleDAO mArticleDAO;
    protected Activity mActivity;

    private EntityEmployee mEntityEmployee;

    protected ControlOnglet mCOwner;



    public MainPopUp(EntityEmployee entityEmployee, ControlOnglet activity) {
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



    @Override
    protected void onStart() {
        super.onStart();
        mButtonSubmit.setTag(TAG_BUTTON_SUBMIT);
        mButtonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(TAG_BUTTON_SUBMIT.equals(v.getTag()) &&isAllFieldsValide() ){
            onSubmit();
            dismiss();
        }
    }
    protected abstract void onSubmit();

    /**
     * Check if we can submit this formular
     * @return
     */
    protected abstract boolean isAllFieldsValide();
}
