package com.ys.baseproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ys.baseproject.db.UserDao;

/**
 * Created by yunshan on 17/5/22.
 */

public class SQLActivity extends AppCompatActivity{

    private UserDao dao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
//        setContentView(R.layout.sss);
        dao = new UserDao(this);
        User user = new User();
        user.setSelected(false);
        user.setUrl("http");

        user.setUserAge("111");
        user.setUserName("yangsong");

      // 存取数据
        dao.addUser(user);



    }
}
