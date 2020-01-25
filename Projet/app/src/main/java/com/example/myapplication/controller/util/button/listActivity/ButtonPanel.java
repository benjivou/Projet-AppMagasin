package com.example.myapplication.controller.util.button.listActivity;

import android.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;

import com.example.myapplication.controller.PopUp.AislePopUp;

/**
 * Created by Benjamin Vouillon on 25,January,2020
 */
public abstract class ButtonPanel extends ControlOnglet{

    private static final String TAG_PANEL_ADD ="add";
    private static final String TAG_PANEL_REFRESH ="refresh";
    private static final String TAG_PANEL_FIND ="find";
    private static final String TAG_PANEL_ADD_AISLE ="add_aisle";

    protected ImageButton
            mButtonAjouter,
            mButtonRefresh,
            mButtonFind,
            mButtonAddAisle;

    @Override
    protected void onStart() {
        super.onStart();

        // Set TAG
        mButtonAjouter.setTag(TAG_PANEL_ADD);
        mButtonRefresh.setTag(TAG_PANEL_REFRESH);
        mButtonFind.setTag(TAG_PANEL_FIND);
        mButtonAddAisle.setTag(TAG_PANEL_ADD_AISLE);

        // Set listener
        mButtonAjouter.setOnClickListener(this);
        mButtonRefresh.setOnClickListener(this);
        mButtonFind.setOnClickListener(this);
        mButtonAddAisle.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (TAG_PANEL_ADD_AISLE.equals((String)v.getTag())){
            AislePopUp aislePopUp = new AislePopUp(getEntityEmployee(),this);
            aislePopUp.show();

        }

    }
}
