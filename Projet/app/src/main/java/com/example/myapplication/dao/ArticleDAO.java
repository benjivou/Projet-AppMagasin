package com.example.myapplication.dao;

import com.example.myapplication.model.EntityArticle;

import java.io.IOException;

import static com.example.myapplication.config.ConfigDAO.ARTICLE;



public class ArticleDAO extends Dao<EntityArticle>{


    public ArticleDAO() {
        this.mPath = ARTICLE;
        try {
            this.mFileManager = new FileManager<>(this.mPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
