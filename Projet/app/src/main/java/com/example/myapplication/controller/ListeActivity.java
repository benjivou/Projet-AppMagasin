package com.example.myapplication.controller;


import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;


import com.example.myapplication.R;
import com.example.myapplication.controller.util.button.listActivity.ControlOnglet;


public class ListeActivity extends ControlOnglet {
    private static final String TAG = "ListeActivity";
    /*
    Attribut
     */
    // Frontend

    ImageButton
            mButtonAjouter,
            mButtonRefresh,
            mButtonFind,
            mButtonAddAisle;

    TextView mEmployeInfo;



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
        mListOf = (ListView) findViewById(R.id.listView);

        Log.d(TAG, "onCreate: Binding done !");




        mEmployeInfo.setText(displayUsername(this.getEntityEmployee()));

    }


}
