package com.example.myapplication.dao;

import com.example.myapplication.model.EntityRayon;

import java.io.IOException;

import static com.example.myapplication.config.ConfigDAO.AISLE;



public class AisleDAO extends Dao<EntityRayon>{



    public AisleDAO() {
        this.mPath = AISLE;
        try {
            this.mFileManager = new FileManager<>(this.mPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
