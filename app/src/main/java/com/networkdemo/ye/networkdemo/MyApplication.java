package com.networkdemo.ye.networkdemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.File;

/**
 * Created by ye on 2017/11/21.
 */

public class MyApplication extends Application {
    public static MyApplication mInstance;

//    public static MyApplication getInstance() {
//        return mInstance;
//    }
    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

    }
}
