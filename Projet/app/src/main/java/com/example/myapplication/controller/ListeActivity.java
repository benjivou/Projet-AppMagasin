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
import com.example.myapplication.controller.util.DisplayUtilActivity;

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
        mOngletEmployers = (Button) findViewById(R.id.btModeProduct);
        mOngletProduit = (Button) findViewById(R.id.btModeEmployee);
        mListOf = (RecyclerView)findViewById(R.id.recyclerView);

        Log.d(TAG, "onCreate: Binding done !");
    }
}
