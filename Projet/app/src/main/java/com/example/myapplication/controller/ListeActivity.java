package com.example.myapplication.controller;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.config.ConfigFront;
import com.example.myapplication.controller.util.DisplayUtilActivity;
import com.example.myapplication.dao.EmployeeDAO;
import com.example.myapplication.model.EntityEmployee;

public class ListeActivity extends DisplayUtilActivity {
    private static final String TAG = "ListeActivity";
    /*
    Attribut
     */
    // Frontend
    Button
            mOngletProduit,
            mOngletEmployers;
    ImageButton
            mButtonAjouter,
            mButtonRefresh,
            mButtonFind,
            mButtonAddAisle;

    TextView mEmployeInfo;

    RecyclerView mListOf;
    // Backend
    EntityEmployee mEntityEmployee;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        // init the toolbar
        mEmployeInfo = (TextView)findViewById(R.id.userInfo);
        mButtonAddAisle = (ImageButton)findViewById(R.id.btShoppingBasket);
        mButtonAjouter = (ImageButton)findViewById(R.id.btPlus);
        mButtonFind = (ImageButton)findViewById(R.id.btBrowsing);
        mButtonRefresh = (ImageButton)findViewById(R.id.btReload);

        // Let's control the mainContent
        mOngletEmployers = (Button) findViewById(R.id.btModeEmployee);
        mOngletProduit = (Button) findViewById(R.id.btModeProduct);
        mListOf = (RecyclerView)findViewById(R.id.recyclerView);

        Log.d(TAG, "onCreate: Binding done !");

        // get the current employee
        mEntityEmployee = EmployeeDAO.getByMatricule(getIntent().getStringExtra(ConfigFront.USERNAME_SESSION),this);
        Log.d(TAG, "onCreate: " + mEntityEmployee.toString());



        mEmployeInfo.setText(displayUsername(mEntityEmployee));
    }
}
