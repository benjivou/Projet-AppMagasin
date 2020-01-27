package com.example.myapplication.controller.PopUp;

import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myapplication.config.ConfigFront;
import com.example.myapplication.controller.util.button.listActivity.ButtonPanel;
import com.example.myapplication.model.EntityAisle;
import com.example.myapplication.model.EntityEmployee;

import java.util.ArrayList;
import java.util.List;

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
        mListAisle = mAisleDAO.getAll();
        checkList();
        mListNameAisle = modifyType(mListAisle);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<> (this.mActivity,android.R.layout.simple_list_item_1,mListNameAisle);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mProductAisle.setAdapter(arrayAdapter);
        mProductAisle.setOnItemSelectedListener(this);




    }

    public AisleSpinnerPopUp(EntityEmployee entityEmployee, ButtonPanel activity) {
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
     * Check if we have some aisle else kill the pop-up
     */
    private void checkList(){
        if(mListAisle.size() == 0){
            this.mBtActivity.displayError(ConfigFront.ERROR_FIELD_NO_AISLE);

        }else{
            mSelectedAisle = mListAisle.get(0);
        }
    }
}
