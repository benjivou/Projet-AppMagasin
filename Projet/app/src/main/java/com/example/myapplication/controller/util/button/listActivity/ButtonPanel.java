package com.example.myapplication.controller.util.button.listActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.myapplication.controller.PopUp.AddProductPopUp;
import com.example.myapplication.controller.PopUp.AislePopUp;
import com.example.myapplication.controller.PopUp.FindPopUp;
import com.example.myapplication.controller.PopUp.MainPopUp;

/**
 * Created by Benjamin Vouillon on 25,January,2020
 */
public abstract class ButtonPanel extends ControlOnglet {
    private static final String TAG = "ButtonPanel";


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

        if (TAG_PANEL_REFRESH.equals((String)v.getTag())) {
            Log.d(TAG, "onClick:trying to refresh");
            refreshAll();
            Log.d(TAG, "onClick: All list Are refreshed");
        }

        if(TAG_PANEL_ADD.equals((String)v.getTag())){
            Log.d(TAG, "onClick: Add button click");

            // product mode
            if (isModeProductIsClicked()){
                Log.d(TAG, "onClick: start Product dialog");
                AddProductPopUp addProductPopUp = new AddProductPopUp(getEntityEmployee(),this);
                addProductPopUp.setOnDismissListener(this);
                addProductPopUp.show();
            }
            else {
                Log.d(TAG, "onClick: start Employee dialog");
            }
        }
        if(TAG_PANEL_FIND.equals((String)v.getTag())){
            Log.d(TAG, "onClick: Find button");

            new FindPopUp(getEntityEmployee(),this).show();
        }

    }



}
