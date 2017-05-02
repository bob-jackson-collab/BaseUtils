package com.ys.baseproject;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ys.baseproject.databinding.Frag1Binding;
import com.ys.baseproject.rx.RxBus;

/**
 * Created by yunshan on 17/3/14.
 */

public class Fragment1 extends Fragment {


    Fragment2 fragment2 = Fragment2.getNewInstance("12345678");

    private Frag1Binding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

//        View view = inflater.inflate(R.layout.frag1,container,false);

        mBinding = DataBindingUtil.inflate(inflater,R.layout.frag1,container,false);
//        Button button = (Button) view.findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        mBinding.setFrag(this);

//        String str = "<font color=\"#FF0000\">我是红色字体</font>";
//        String str2 = "<font color=\"#00ff00\">我是绿色字体</font>";
//        mBinding.textView.setText(Html.fromHtml(str).toString()+Html.fromHtml(str2));


        SpannableString styledText = new SpannableString("亲爱的小宝，你好");
        styledText.setSpan(new TextAppearanceSpan(getActivity(),R.style.style0), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new TextAppearanceSpan(getActivity(), R.style.style1), 3, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mBinding.textView.setText(styledText.toString());
        mBinding.editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                Log.e("keyCode",keyCode+"--->>");
                return false;
            }
        });
        return mBinding.getRoot();
    }

    public void bindClick(View view){
        Log.e("info","点击了时间");

        RxBus.getRxBus().post("123",fragment2);
//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment2).commit();
    }
}
