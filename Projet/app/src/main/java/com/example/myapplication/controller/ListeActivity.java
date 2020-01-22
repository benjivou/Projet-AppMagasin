package com.example.myapplication.controller;


import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.config.ConfigFront;
import com.example.myapplication.controller.util.DisplayUtilActivity;
import com.example.myapplication.controller.util.button.listActivity.ButtonOnglet;
import com.example.myapplication.dao.EmployeeDAO;
import com.example.myapplication.model.EntityEmployee;

public class ListeActivity extends ButtonOnglet {
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

    ListView mListOf;
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
        mListOf = (ListView) findViewById(R.id.listView);

        Log.d(TAG, "onCreate: Binding done !");



        // get the current employee
        mEntityEmployee = EmployeeDAO.getByMatricule(getIntent().getStringExtra(ConfigFront.USERNAME_SESSION),this);
        Log.d(TAG, "onCreate: " + mEntityEmployee.toString());



        mEmployeInfo.setText(displayUsername(mEntityEmployee));
        String[] prenoms = new String[]{
                "Antoine", "Benoit", "Cyril", "David", "Eloise", "Florent",
                "Gerard", "Hugo", "Ingrid", "Jonathan", "Kevin", "Logan",
                "Mathieu", "Noemie", "Olivia", "Philippe", "Quentin", "Romain",
                "Sophie", "Tristan", "Ulric", "Vincent", "Willy", "Xavier",
                "Yann", "Zoé"
        };
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListeActivity.this,
                android.R.layout.simple_list_item_1, prenoms);
        mListOf.setAdapter(adapter);
    }

    @Override
    protected void onOngletEmployee() {

    }

    @Override
    protected void onOngletProduct() {

    }
}
