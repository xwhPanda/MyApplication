package com.jiqu.helper.application;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jiqu.database.DaoMaster;
import com.jiqu.database.DaoSession;

/**
 * Created by xiongweihua on 2016/7/4.
 */
public class HelperApplication extends Application{
    public static Context context;
    public static DaoSession daoSession;
    public static String PACKAGE_NAME;
    public static String DATA_CACHE_PATH;
    public static String DATA_FILE_PATH = "";

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Fresco.initialize(this);
        getDaoSession();
        PACKAGE_NAME = getPackageName();
        DATA_CACHE_PATH = getCacheDir().getAbsolutePath();
        DATA_FILE_PATH = getFilesDir().getAbsolutePath();
    }

    private static DaoSession getDaoSession() {
        if (daoSession == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "jiqu_helper_database", null);
            SQLiteDatabase db = helper.getWritableDatabase();
            DaoMaster daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
