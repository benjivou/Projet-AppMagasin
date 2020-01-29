package com.example.myapplication.controller.PopUp.abstractpopup;

import android.util.Log;

import com.example.myapplication.controller.util.button.listActivity.ButtonPanel;
import com.example.myapplication.controller.util.button.listActivity.ControlOnglet;
import com.example.myapplication.model.EntityArticle;
import com.example.myapplication.model.EntityEmployee;

/**
 * Created by Benjamin Vouillon on 28,January,2020
 */
public abstract class DeleteProductPopUp extends DeletePopUp {

    protected EntityArticle mSelectedArticle;
    private static final String TAG = "DeleteProductPopUp";
    public DeleteProductPopUp(EntityEmployee entityEmployee, ControlOnglet activity, EntityArticle selectedArticle) {
        super(entityEmployee, activity);
        this.mSelectedArticle = selectedArticle;
    }

    @Override
    protected void onDelete() {
        Log.d(TAG, "onDelete: Starting to kill your article");
        this.mArticleDAO.deleteArticleById(this.mSelectedArticle.getIdArticle());
    }
}
