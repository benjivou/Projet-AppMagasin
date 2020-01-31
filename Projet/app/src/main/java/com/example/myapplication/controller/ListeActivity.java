package com.example.myapplication.controller;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.controller.util.button.listActivity.ButtonPanel;
import com.example.myapplication.dao.RoleDAO;


public class ListeActivity extends ButtonPanel {
    private static final String TAG = "ListeActivity";
    /*
    Attribut
     */
    // Frontend



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
        Log.d(TAG, "onCreate: your user role is "+ this.getEntityEmployee().getRole());
        Log.d(TAG, "onCreate: Are U normal user of the mag "+
                this.getEntityEmployee().getRole().equals(RoleDAO.USER.getSring()));
        // to block the access of the user to Admin parameters
        if (!isAdmin()) {
            Log.i(TAG, "onCreate: U are a Standard user");
            this.mOngletEmployers.setVisibility(View.INVISIBLE);
            this.mButtonAddAisle.setVisibility(View.INVISIBLE);
        }
    }


}
