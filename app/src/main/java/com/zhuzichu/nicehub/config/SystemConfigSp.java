package com.zhuzichu.nicehub.config;

import android.content.Context;
import android.content.SharedPreferences;

public class SystemConfigSp {
    private final String fileName = "systemconfig.ini";
    private Context ctx;
    SharedPreferences sharedPreferences;
    private static SystemConfigSp systemConfigSp = null;

    public static SystemConfigSp instance() {
        if (systemConfigSp == null) {
            synchronized (SystemConfigSp.class) {
                systemConfigSp = new SystemConfigSp();
            }
        }
        return systemConfigSp;
    }

    private SystemConfigSp() {

    }

    public void init(Context ctx) {
        this.ctx = ctx;
        sharedPreferences = ctx.getSharedPreferences
                (fileName, ctx.MODE_PRIVATE);
    }

    public String getStrConfig(com.zhangwuji.im.DB.sp.SystemConfigSp.SysCfgDimension dimension) {
        String strValue = sharedPreferences.getString(dimension.name(), "");
        return strValue;
    }

    public void setStrConfig(com.zhangwuji.im.DB.sp.SystemConfigSp.SysCfgDimension dimension, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(dimension.name(), value);
        //提交当前数据
        editor.commit();
    }

    public int getIntConfig(com.zhangwuji.im.DB.sp.SystemConfigSp.SysCfgDimension dimension) {
        int strValue = sharedPreferences.getInt(dimension.name(), 0);
        return strValue;
    }

    public void setIntConfig(com.zhangwuji.im.DB.sp.SystemConfigSp.SysCfgDimension dimension, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(dimension.name(), value);
        //提交当前数据
        editor.commit();
    }

    public int getIntConfig(String key) {
        int strValue = sharedPreferences.getInt(key, 0);
        return strValue;
    }

    public void setIntConfig(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        //提交当前数据
        editor.commit();
    }

    public enum SysCfgDimension {
        KEYBOARDHEIGHT,
        DEFAULTINPUTMETHOD,
        DISCOVERYURI,
        LOGINSERVER,
        DISCOVERYDATA,
        MSFSSERVER,
        APPID
    }
}
