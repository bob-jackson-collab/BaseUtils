package com.ys.baseproject;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.blankj.utilcode.utils.ToastUtils;
import com.ys.baseproject.base.PermissionUtils;
import com.ys.baseproject.db.DataDao;
import com.ys.baseproject.db.Status;
import com.ys.baseproject.view.MyViewAndCircle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunshan on 17/5/22.
 */

public class SQLActivity extends AppCompatActivity {

    private DataDao dao;
    private List<Status> users;
//    HashMap

    private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtils.CODE_CAMERA:
                    ToastUtils.showShortToast("打开了照相机");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
        final MyViewAndCircle  circle = (MyViewAndCircle) findViewById(R.id.text);
        circle.setTitleText("打单");
        circle.setIsVisiable(true);

        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circle.setIsVisiable(false);
            }
        });
//        setContentView(R.layout.sss);
        dao = new DataDao(this);
        users = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            Status user = new Status();
            user.setData_id(i + "");
            user.setStatus(true);
            users.add(user);
//            dao.addUser(user);
        }

        Dialog dialog = new Dialog(this,R.style.CustomProgressDialog);
        dialog.setContentView(R.layout.dialog_ss);

        dialog.show();
        Window window = dialog.getWindow();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//        window.setLayout(400,400);
//        AndroidDatabaseConnection db = new AndroidDatabaseConnection(
//                getSQLiteDatabase(), true);
//        db.setAutoCommit(false);
//        for (ChannelDate cd : listChannelDate) {
//            cdao.createOrUpdate(cd);
//        }
//        db.commit(null);
//        dao.addUser(users);

        // 存取数据
        dao.addUsers(users);

//        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////             PermissionsUtil.checkAndRequestPermissions(SQLActivity.this);
//                PermissionUtils.requestPermission(SQLActivity.this, PermissionUtils.CODE_CAMERA, mPermissionGrant);
//            }
//        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, mPermissionGrant);
    }
}
