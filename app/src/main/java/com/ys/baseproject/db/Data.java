package com.ys.baseproject.db;

import java.util.List;

/**
 * Created by yunshan on 17/5/19.
 */

public class Data {

    private String number;
    private List<ChildData> datas;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<ChildData> getDatas() {
        return datas;
    }

    public void setDatas(List<ChildData> datas) {
        this.datas = datas;
    }
}
