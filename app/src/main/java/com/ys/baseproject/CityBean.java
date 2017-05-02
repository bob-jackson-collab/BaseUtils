package com.ys.baseproject;

import java.util.List;

/**
 * Created by yunshan on 17/4/27.
 */

public class CityBean {

    private String id;
    private String city;
    private List<TwoCityBean> beanList;

    public CityBean(String city, List<TwoCityBean> beanList) {
        this.city = city;
        this.beanList = beanList;
    }

    public List<TwoCityBean> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<TwoCityBean> beanList) {
        this.beanList = beanList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
