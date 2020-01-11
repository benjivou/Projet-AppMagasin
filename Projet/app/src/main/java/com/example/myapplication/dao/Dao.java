package com.example.myapplication.dao;

import com.example.myapplication.model.SearchebleImplement;

import java.util.ArrayList;

public abstract class Dao <T extends SearchebleImplement>{
     protected FileManager<T> mFileManager ;

     public void add(T t){
          ArrayList<T> list =  this.mFileManager.read();
          list.add(t);
          this.mFileManager.update();
     }

     public ArrayList<T> findByName(String name){
          ArrayList<T> list = new ArrayList<>();
          for( T t : this.mFileManager.read()){
               // find the good device
               if (t.getName().equals(name)){
                    list.add(t);
               }
          }
          return list;
     }

     /**
      *
      * @param t Need a valid element
      */
     public void remove(T t){

          this.mFileManager.read().remove(t);

          this.mFileManager.update();
     }


}
