package com.ys.baseproject;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yunshan on 17/3/14.
 */

public class Fragment2 extends Fragment {


    public static Fragment2 getNewInstance(String args){
        Fragment2 fragment2 = new Fragment2();
        Bundle bundle = new Bundle();
        bundle.putString("args",args);
        fragment2.setArguments(bundle);
        return  fragment2;
    }

    public String getArags(){
        return  getArguments().getString("args");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag1,container,false);
        TextView textView = (TextView) view.findViewById(R.id.textView);

        Log.e("info","fragment2"+getArags());
        textView.setText(getArags());
        return view;


    }
}
