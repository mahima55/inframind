
package com.example.fitnessapp2;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostList {

    @SerializedName("Datasource")
    @Expose
    private List<Datasource> datasource = null;

    public List<Datasource> getDatasource() {
        return datasource;
    }

    public void setDatasource(List<Datasource> datasource) {
        this.datasource = datasource;
    }

}
