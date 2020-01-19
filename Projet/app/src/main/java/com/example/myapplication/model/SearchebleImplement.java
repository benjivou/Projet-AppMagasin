package com.example.myapplication.model;

import androidx.annotation.Nullable;

public abstract class SearchebleImplement<T>  implements Searchable<T>{
    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj)        // Same object
            return true;
        if (obj == null)        // null object
            return false;
        if (getClass() != obj.getClass())   // not the same class
            return false;

        SearchebleImplement sch = (SearchebleImplement) obj;
        // same Unique Key
        return this.getUniqueKey().equals(sch.getUniqueKey());
    }
}
