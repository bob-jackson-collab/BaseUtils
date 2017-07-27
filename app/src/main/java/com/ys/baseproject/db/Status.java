package com.ys.baseproject.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by yunshan on 17/5/22.
 */

@DatabaseTable(tableName = "tab_status")
public class Status {

//    @DatabaseField(columnName = "id", generatedId = true ,canBeNull = false)
//    private int id;

    @DatabaseField(id = true,columnName = "data_id")
    private String data_id;

    @DatabaseField(columnName = "status")
    private boolean status; //状态
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getData_id() {
        return data_id;
    }

    public void setData_id(String data_id) {
        this.data_id = data_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
