package com.example.myapplication.controller.PopUp;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.controller.PopUp.abstractpopup.MainPopUp;
import com.example.myapplication.controller.util.button.listActivity.ButtonPanel;
import com.example.myapplication.model.EntityAisle;
import com.example.myapplication.model.EntityEmployee;
/**
 * This class allow us to manage the  add aisle pop-up
 * @<version 1.0
 * @author Leslie Kiav & Benjamin Vouillon
 */
public class AislePopUp extends MainPopUp {


    // Tag
    private static final String TAG = "AddAisle";


    EditText mAisle;



    public AislePopUp(EntityEmployee entityEmployee, ButtonPanel activity) {
        super(entityEmployee,activity);
    }
    /**
     * This method contain all action will do once you submit
     */
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
    /**
     * This method allow us to bind the attribute with the view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_add_aisle);
        
        mAisle  = (EditText)findViewById(R.id.txtNomRayon);
        this.mButtonSubmit = (ImageButton) findViewById(R.id.btnSubmit);

    }

    /**
     * This method check if the aisleDao do not exist
     * @return
     */
    @Override
    protected boolean isAllFieldsValide() {
        boolean res =  this.mAisleDAO.getByName(mAisle.getText().toString()).size() == 0;

        return res;
    }


}
