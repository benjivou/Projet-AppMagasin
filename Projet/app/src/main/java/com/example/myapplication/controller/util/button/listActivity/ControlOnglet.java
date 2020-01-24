package com.example.myapplication.controller.util.button.listActivity;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.dao.ArticleDAO;
import com.example.myapplication.dao.EmployeeDAO;
import com.example.myapplication.model.EntityArticle;
import com.example.myapplication.model.EntityEmployee;

public abstract class ControlOnglet extends ButtonOnglet{
    protected ListView mListOf;

    private ArrayAdapter<EntityEmployee> mEmployeeArrayAdapter;
    private ArrayAdapter<EntityArticle> mArticleArrayAdapter;


    @Override
    protected void onOngletEmployee() {
        // request all employee
        mEmployeeArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                EmployeeDAO.getAllEmployee(this)
        );
        mListOf.setAdapter(mEmployeeArrayAdapter);
    }

    @Override
    protected void onOngletProduct() {
        // request all article
        mArticleArrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                ArticleDAO.getAll(this)
        );
        // display
        mListOf.setAdapter(mArticleArrayAdapter);
    }


}
