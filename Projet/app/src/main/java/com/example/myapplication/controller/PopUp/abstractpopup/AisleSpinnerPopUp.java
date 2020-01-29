package com.example.myapplication.controller.PopUp.abstractpopup;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myapplication.config.ConfigFront;
import com.example.myapplication.controller.util.button.listActivity.ButtonPanel;
import com.example.myapplication.controller.util.button.listActivity.ControlOnglet;
import com.example.myapplication.model.EntityAisle;
import com.example.myapplication.model.EntityArticle;
import com.example.myapplication.model.EntityEmployee;

import java.util.ArrayList;

/**
 * Created by Benjamin Vouillon on 27,January,2020
 */
public abstract class AisleSpinnerPopUp extends SubmitControllerPopUp implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "AisleSpinnerPopUp";
    
    protected Spinner mProductAisle;
    // back-end attribute
    protected EntityAisle mSelectedAisle;
    protected ArrayList<EntityAisle> mListAisle;
    protected ArrayList<String> mListNameAisle;

    @Override
    protected void onStart() {
        super.onStart();
        
        //getAll aisle

        setContentOfSpinner(mAisleDAO.getAll());
        mProductAisle.setOnItemSelectedListener(this);

    }

    public AisleSpinnerPopUp(EntityEmployee entityEmployee, ControlOnglet activity) {
        super(entityEmployee, activity);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //get selected item
        mSelectedAisle = (EntityAisle)mListAisle.get(position);

        Log.d(TAG, "onItemClick: Selected item is : " + mSelectedAisle);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * Check if we have some aisle else display an error message
     */
    private void checkList(){
        if(mListAisle.size() == 0){
            this.mCOwner.displayError(ConfigFront.ERROR_FIELD_NO_AISLE);

        }else{
            mSelectedAisle = mListAisle.get(0);
        }
    }

    /**
     * Set the Spinner to the position of this aisle
     * @param entityAisle the entity is necessary in the attribute mListAisle
     */
    protected void setSpinnerToAisle(EntityAisle entityAisle){
        /*
        get the position of the aisle
         */
        int count = 0;
        for (EntityAisle ea:
                this.mListAisle
             ) {

            if (entityAisle.equals(ea)){
                break;
            }

            count++;
        }

        /*
        Set the spinner position
         */
        this.mProductAisle.setSelection(count);
    }

    /**
     * Set the spinner content to the list
     * @param entityAisleArrayList
     */
    protected void setContentOfSpinner(ArrayList<EntityAisle> entityAisleArrayList){

        this.mListAisle = entityAisleArrayList;
        checkList();
        this.mListNameAisle = modifyType(mListAisle);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<> (this.mActivity,android.R.layout.simple_list_item_1,mListNameAisle);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mProductAisle.setAdapter(arrayAdapter);
        mProductAisle.setOnItemSelectedListener(this);
    }
}
