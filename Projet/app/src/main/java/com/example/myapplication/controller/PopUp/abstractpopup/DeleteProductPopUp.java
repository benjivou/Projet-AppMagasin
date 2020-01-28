package com.example.myapplication.controller.PopUp.abstractpopup;

import com.example.myapplication.controller.util.button.listActivity.ButtonPanel;
import com.example.myapplication.model.EntityArticle;
import com.example.myapplication.model.EntityEmployee;

/**
 * Created by Benjamin Vouillon on 28,January,2020
 */
public abstract class DeleteProductPopUp extends DeletePopUp {

    EntityArticle mSelectedArticle;

    public DeleteProductPopUp(EntityEmployee entityEmployee, ButtonPanel activity, EntityArticle selectedArticle) {
        super(entityEmployee, activity);
        this.mSelectedArticle = selectedArticle;
    }

    @Override
    protected void onDelete() {
        this.mArticleDAO.deleteArticleById(this.mSelectedArticle.getIdArticle());
    }
}
