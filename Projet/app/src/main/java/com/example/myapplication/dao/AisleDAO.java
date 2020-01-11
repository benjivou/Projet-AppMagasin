package com.example.myapplication.dao;

import com.example.myapplication.model.EntityRayon;

import java.io.IOException;

import static com.example.myapplication.config.ConfigDAO.AISLE;
import static com.example.myapplication.config.ConfigDAO.EMPLOYEE;
import static com.example.myapplication.config.ConfigDAO.FOLDER;

public class AisleDAO extends Dao<EntityRayon>{
    private static final String path = FOLDER + AISLE;


    public AisleDAO() {
        try {
            this.mFileManager = new FileManager<>(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
