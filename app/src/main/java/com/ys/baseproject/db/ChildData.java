package com.ys.baseproject.db;

/**
 * Created by yunshan on 17/5/19.
 */


public class ChildData {

    private String id;

    private String data;

    private boolean flag;    //核货和未核货标志


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
