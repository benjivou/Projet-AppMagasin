package com.example.myapplication.dao;

import com.example.myapplication.model.EntityArticle;

import java.io.IOException;

import static com.example.myapplication.config.ConfigDAO.ARTICLE;
import static com.example.myapplication.config.ConfigDAO.FOLDER;


public class ArticleDAO extends Dao<EntityArticle>{
    private static final String path = FOLDER + ARTICLE;

    public ArticleDAO() {
        try {
            this.mFileManager = new FileManager<>(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
