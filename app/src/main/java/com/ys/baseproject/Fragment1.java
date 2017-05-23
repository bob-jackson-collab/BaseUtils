package com.ys.baseproject;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.blankj.utilcode.utils.ToastUtils;
import com.ys.baseproject.databinding.Frag1Binding;
import com.ys.baseproject.rx.RxBus;
import com.ys.baseproject.view.PassWordView;

/**
 * Created by yunshan on 17/3/14.
 */

public class Fragment1 extends Fragment {

    public static final String TAG = Fragment1.class.getSimpleName();
    Fragment2 fragment2 = Fragment2.getNewInstance("12345678");

    private Frag1Binding mBinding;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG,"onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG,"onCreate");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG,"onActivityCreated");
        if(savedInstanceState!=null) {
            String ss = savedInstanceState.getString("str");
            if (!TextUtils.isEmpty(ss)) {
                mBinding.textView.setText(ss);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG,"onStart");
    }



    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("str","nihaoa");

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG,"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG,"onDetach");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Log.e(TAG,"onCreateView");
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

        mBinding.passwordView.setFocusable(true);
        mBinding.passwordView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.showSoftInputFromInputMethod(v.getWindowToken(),InputMethodManager.SHOW_FORCED);
                }
            }
        });
        mBinding.passwordView.setCompareWord("123456", new PassWordView.PasswordListener() {
            @Override
            public void equals() {
                ToastUtils.showShortToast("密码成功");
            }

            @Override
            public void difference() {
                ToastUtils.showShortToast("密码不匹配");
            }
        });
//        String str = "<font color=\"#FF0000\">我是红色字体</font>";
//        String str2 = "<font color=\"#00ff00\">我是绿色字体</font>";
//        mBinding.textView.setText(Html.fromHtml(str).toString()+Html.fromHtml(str2));


//        SpannableString styledText = new SpannableString("亲爱的小宝，你好");
//        styledText.setSpan(new TextAppearanceSpan(getActivity(),R.style.style0), 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        styledText.setSpan(new TextAppearanceSpan(getActivity(), R.style.style1), 3, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        mBinding.textView.setText(styledText.toString());
//        mBinding.editText.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//
//                Log.e("keyCode",keyCode+"--->>");
//                return false;
//            }
//        });
        return mBinding.getRoot();
    }

    public void bindClick(View view){
        Log.e("info","点击了时间");

        RxBus.getRxBus().post("123",fragment2);
//        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment2).commit();
    }
}
