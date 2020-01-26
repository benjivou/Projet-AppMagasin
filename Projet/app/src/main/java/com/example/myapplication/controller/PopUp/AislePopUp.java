package com.example.myapplication.controller.PopUp;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.controller.ListeActivity;
import com.example.myapplication.controller.util.button.listActivity.ButtonPanel;
import com.example.myapplication.dao.AisleDAO;
import com.example.myapplication.dao.RoleDAO;
import com.example.myapplication.model.EntityAisle;
import com.example.myapplication.model.EntityEmployee;

public class AislePopUp extends SubmitControllerPopUp{


    // Tag
    private static final String TAG = "AddAisle";


    EditText mAisle;



    public AislePopUp(EntityEmployee entityEmployee, ButtonPanel activity) {
        super(entityEmployee,activity);
    }

    @Override
    protected void onSubmit() {
        EntityAisle entityAisle = new EntityAisle(0,mAisle.getText().toString());
        mAisleDAO.insertAisle(entityAisle);
        Log.d(TAG, "onSubmit: the list of Aisle");
        for (EntityAisle ea:
                mAisleDAO.getAll()
             ) {
            Log.d(TAG, "onSubmit: aisle " + ea.getName());
        }

       
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_add_aisle);
        
        mAisle  = (EditText)findViewById(R.id.txtNomRayon);
        this.mButtonSubmit = (ImageButton) findViewById(R.id.btnSubmit);



    }




}
