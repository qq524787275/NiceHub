package com.zhangwuji.im;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.zhangwuji.im.imcore.service.IMService;

import org.greenrobot.greendao.annotation.NotNull;

public class IMClient {


    private static Context app;

    public static void init(@NotNull Context app) {
        IMClient.app = app;
        startIMService();
    }

    private static void startIMService() {
        Intent intent = new Intent();
        intent.setClass(app, IMService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            app.startForegroundService(intent);
        } else {
            app.startService(intent);
        }
    }


    public static <T> T getService(@NotNull Class<T> clz) {
        //Todo
        return null;
    }
}
