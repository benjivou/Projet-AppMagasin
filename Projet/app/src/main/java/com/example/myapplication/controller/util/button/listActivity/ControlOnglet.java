package com.example.myapplication.controller.util.button.listActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.myapplication.config.ConfigFront;
import com.example.myapplication.dao.ArticleDAO;
import com.example.myapplication.dao.EmployeeDAO;
import com.example.myapplication.model.EntityArticle;
import com.example.myapplication.model.EntityEmployee;

public abstract class ControlOnglet extends ButtonOnglet{
    protected ListView mListOf;

    private ArrayAdapter<EntityEmployee> mEmployeeArrayAdapter;
    private ArrayAdapter<EntityArticle> mArticleArrayAdapter;

    private ArticleDAO mArticleDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArticleDAO = new ArticleDAO(
                ConfigFront.SYSTEM_ROLE,
                ConfigFront.SYSTEM_AISLE,
                this);
    }

    @Override
    protected void onOngletEmployee() {
        // request all employee
        mEmployeeArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                mCurrentUser.getAllEmployee()
        );
        mListOf.setAdapter(mEmployeeArrayAdapter);
    }

    @Override
    protected void onOngletProduct() {

        // request all article
        mArticleArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                mArticleDAO.getAll()
        );

        // display
        mListOf.setAdapter(mArticleArrayAdapter);
    }


}
