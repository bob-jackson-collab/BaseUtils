package com.ys.baseproject.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Base BaseEntity
 * @author DwGG
 * Created at 2016/01/29 10:25
 * Copyright (c) 2015年 Beijing Yunshan Information Technology Co., Ltd. All rights reserved.
 */
public class BaseEntity<T> {

    @SerializedName("ret")
    @Expose
    private int ret;

    @SerializedName("data")
    @Expose
    private T data;

    @SerializedName("error_code")
    @Expose
    private int error_code;

    @SerializedName("error")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                ", data=" + data +
                ", ret=" + ret +
                ", error_code=" + error_code +
                '}';
    }
}
