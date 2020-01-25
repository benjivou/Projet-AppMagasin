package com.example.myapplication.controller.util.button.listActivity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.config.ConfigFront;
import com.example.myapplication.controller.util.DisplayUtilActivity;
import com.example.myapplication.model.EntityEmployee;

/**
 * Created by benji on 25,January,2020
 * The objectif is to prepare the session and avoid risk that user behind sudo
 */
public abstract class SessionManagerActivity extends DisplayUtilActivity {
    private static final String TAG = "SessionManagerActivity";

    private EntityEmployee mEntityEmployee;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get the current employee
        mEntityEmployee = mCurrentUser.getByMatricule(getIntent().getStringExtra(ConfigFront.USERNAME_SESSION));
        Log.d(TAG, "onCreate: " + mEntityEmployee.toString());
    }

    protected EntityEmployee getEntityEmployee(){
        return this.mEntityEmployee;
    }
}
