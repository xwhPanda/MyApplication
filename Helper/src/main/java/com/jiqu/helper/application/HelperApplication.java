package com.jiqu.helper.application;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by xiongweihua on 2016/7/4.
 */
public class HelperApplication extends Application{
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        LeakCanary.install(this);
        Fresco.initialize(this);
    }
}
