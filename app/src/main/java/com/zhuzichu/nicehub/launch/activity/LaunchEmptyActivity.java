package com.zhuzichu.nicehub.launch.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.zhuzichu.nicehub.login.activity.LoginActivity;

public class LaunchEmptyActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        LoginActivity.Companion.start(this);
//        if (TextUtils.isEmpty(UserPreferences.getUserToken())) {
//            LoginActivity.start(this);
//        } else {
//            MainActivity.start(this);
//        }
        finish();
    }
}
