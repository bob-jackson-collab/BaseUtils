package com.ys.baseproject.db;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.ys.baseproject.User;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by yunshan on 17/3/22.
 */

public class UserDao {

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

    public DeleteBuilder getBuilder(){
        try {
            return helper.getDao(User.class).deleteBuilder();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUsers(final List<User> lists){
        try{

            helper.getDao(User.class).callBatchTasks(new Callable() {
                @Override
                public Object call() throws Exception {

                    for(User user:lists){
                        helper.getDao(User.class).create(user);
                    }
                    return null;
                }
            });
        }catch (Exception e){
            Log.e("infoerror",e.getMessage());
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
