package com.ys.baseproject.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.ys.baseproject.User;

import java.sql.SQLException;

/**
 * Created by yunshan on 17/3/21.
 */

public class DbOpenHelper extends OrmLiteSqliteOpenHelper {

    private int version = 1;

    private static final String DATABASE_NAME = "app.db";

    private static DbOpenHelper dbOpenHelper;

    public static DbOpenHelper getInstance(Context context) {
        synchronized (DbOpenHelper.class) {
            if (dbOpenHelper == null) {
                dbOpenHelper = new DbOpenHelper(context);
            }
            return dbOpenHelper;
        }
    }

    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 0);
    }

    public DbOpenHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <D extends Dao<T, ?>, T> D getDao(Class<T> tClass) {
        return getDao(tClass);
    }
}
