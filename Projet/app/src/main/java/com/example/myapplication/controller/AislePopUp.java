package com.example.myapplication.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.controller.util.DisplayUtilActivity;
import com.example.myapplication.dao.AisleDAO;
import com.example.myapplication.model.EntityAisle;

public class AislePopUp extends DisplayUtilActivity {

    ImageButton mButton;
    EditText mAisle;

    private static final String TAG = "AddAisle";
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_add_aisle);

        mButton = (ImageButton)findViewById(R.id.btnSubmit);
        mAisle  = (EditText)findViewById(R.id.txtNomRayon);
        this.context = this;
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder myPopup = new AlertDialog.Builder(context);
                EntityAisle entityAisle =  new EntityAisle();
                entityAisle.setName(mAisle.getText().toString());
                AisleDAO.insertAisle(entityAisle,context);

            }
        });


    }



}
