package com.ys.baseproject.db;

import android.content.Context;

import com.ys.baseproject.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yunshan on 17/3/22.
 */

public class UserDao {

    private UserDao userDaoOpe;

    private Context context;

    private DatabaseHelper helper;

    public UserDao(Context context){
        helper = DatabaseHelper.getHelper(context);
    }

    public void addUser(User user){
        try {
            helper.getDao(User.class).create(user);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(User user){
        try {
            helper.getDao(User.class).delete(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void upDateUser(User user){
        try {
            helper.getDao(User.class).update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User queryUser(int id){

        User user = null;
        try {
            user = (User) helper.getDao(User.class).queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> queryAll(){
        try {
            List<User> list = helper.getDao(User.class).queryForAll();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
