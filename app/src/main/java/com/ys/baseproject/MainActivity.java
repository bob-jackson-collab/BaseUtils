package com.ys.baseproject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by yunshan on 17/3/13.
 */

public class MainActivity extends AppCompatActivity{

    DrawerLayout drawerLayout;

    ActionBarDrawerToggle toggle;

    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawerlayout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
         drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

//        drawerLayout.openDrawer(View.DRAG_FLAG_GLOBAL);
//        3、去除左右抽屉划出后内容显示页背景的灰色？
        drawerLayout.setScrimColor(Color.TRANSPARENT);

         toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("321");
//                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle("123");
//                invalidateOptionsMenu();
            }
        };


        drawerLayout.addDrawerListener(toggle);


//        SimpleObservable simpleObservable = new SimpleObservable();
//
//        SimpleObserver observer = new SimpleObserver(simpleObservable);
//
//        SimpleObserver2 observer2 = new SimpleObserver2(simpleObservable);
//
//        simpleObservable.setData(1);
//        simpleObservable.setData(2);
//        simpleObservable.setData(3);
//
//        //移除观察者
//        simpleObservable.deleteObservers();


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == android.R.id.home) {
//            if( drawerLayout.isDrawerOpen(GravityCompat.START)
//                    ){
//                drawerLayout.closeDrawers();
//            }else{
//
//                drawerLayout.openDrawer(GravityCompat.START);
//            }
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    class SimpleObservable extends Observable{

        private int data;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;

            setChanged();
            notifyObservers();
        }
    }

    class SimpleObserver implements Observer{

        private SimpleObservable observable;

        public SimpleObserver(SimpleObservable observable){
            observable.addObserver(this);
            this.observable = observable;
        }
        @Override
        public void update(Observable o, Object arg) {
            Log.e("info",observable.getData()+"--->>>");
        }
    }

    class SimpleObserver2 implements Observer{

        private SimpleObservable observable;

        public SimpleObserver2(SimpleObservable observable){
            observable.addObserver(this);
            this.observable = observable;
        }
        @Override
        public void update(Observable o, Object arg) {
            Log.e("info",observable.getData()+"<<<--->>>");
        }
    }

}
