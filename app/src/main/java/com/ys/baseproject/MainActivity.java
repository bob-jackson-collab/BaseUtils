package com.ys.baseproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by yunshan on 17/3/13.
 */

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_h5);
        SimpleObservable simpleObservable = new SimpleObservable();

        SimpleObserver observer = new SimpleObserver(simpleObservable);

        SimpleObserver2 observer2 = new SimpleObserver2(simpleObservable);

        simpleObservable.setData(1);
        simpleObservable.setData(2);
        simpleObservable.setData(3);

        //移除观察者
        simpleObservable.deleteObservers();


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
