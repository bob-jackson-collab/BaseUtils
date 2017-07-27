package com.ys.baseproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

/**
 * Created by yunshan on 17/7/11.
 */

public class ButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        final Button button = (Button) findViewById(R.id.button);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Animation animation = AnimationUtils.loadAnimation(ButtonActivity.this, R.anim.anim_button);
                    button.startAnimation(animation);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    Animation animation = AnimationUtils.loadAnimation(ButtonActivity.this, R.anim.anim_move);
                    button.startAnimation(animation);
                }else {

                }
                return false;
            }
        });
    }
}
