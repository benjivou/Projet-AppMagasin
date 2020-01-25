package com.example.myapplication.controller.PopUp;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.dao.AisleDAO;
import com.example.myapplication.dao.RoleDAO;
import com.example.myapplication.model.EntityAisle;
import com.example.myapplication.model.EntityEmployee;

public class AislePopUp extends MainPopUp {

    ImageButton mButton;
    EditText mAisle;
    private static final String TAG = "AddAisle";
    private Context context;

    public AislePopUp(EntityEmployee entityEmployee, Context context) {
        super(entityEmployee, context);
    }


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
                AisleDAO aisleDAO = new AisleDAO(this.)

            }
        });


    }



}
