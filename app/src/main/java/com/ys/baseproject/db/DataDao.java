package com.ys.baseproject.db;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.ys.baseproject.User;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by yunshan on 17/5/19.
 */

public class DataDao {

    private DatabaseHelper helper;

    public DataDao(Context context) {
        helper = DatabaseHelper.getHelper(context);
    }

    public void addUser(Status user) {
        try {
            helper.getDao(Status.class).createOrUpdate(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DeleteBuilder getBuilder() {
        try {
            return helper.getDao(Status.class).deleteBuilder();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUsers(final List<Status> lists) {
        try {
            helper.getDao(Status.class).callBatchTasks(new Callable() {
                @Override
                public Object call() throws Exception {
                    for(Status status:lists){
                        helper.getDao(Status.class).create(status);
                    }

                    return null;
                }
            });

//            helper.getDao(Status.class).commit();
//            Log.e("info",i+"");
        } catch (Exception e) {
            Log.e("infoerror", e.getMessage());
            e.printStackTrace();
        }
    }


    public void deleteUser(Status user) {
        try {
            helper.getDao(Status.class).delete(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllUser(List<Status> user) {
        try {
            helper.getDao(Status.class).delete(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void upDateUser(Status user) {
        try {
            helper.getDao(Status.class).update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Status queryUser(int id) {

        Status user = null;
        try {
            user = (Status) helper.getDao(Status.class).queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<Status> queryAll() {
        try {
            List<Status> list = helper.getDao(Status.class).queryForAll();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
