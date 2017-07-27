package com.ys.baseproject;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by yunshan on 17/3/21.
 */

@DatabaseTable(tableName = "User")
public class User extends BaseObservable{

    @DatabaseField(columnName = "url")
    private String url;

    @DatabaseField(generatedId = true,canBeNull = false)
    private int id;

    @DatabaseField(columnName = "userName")
    private String userName;

    @DatabaseField(columnName = "userAge")
    private String userAge;

    private boolean isSelected;


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public User(){

    }
    public User(String userName, String userAge) {
        this.userName = userName;
        this.userAge = userAge;
//        this.isSelected = isSelected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
