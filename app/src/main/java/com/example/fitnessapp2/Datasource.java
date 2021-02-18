
package com.example.fitnessapp2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datasource {

    @SerializedName("dataType")
    @Expose
    private String dataType;
    @SerializedName("value")
    @Expose
    private String value;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
