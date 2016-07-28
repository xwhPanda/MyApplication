package com.jiqu.helper.okhttp;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.jiqu.helper.interfaces.GetDataCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;

/**
 * Created by xiongweihua on 2016/7/21.
 */
public class OkHttpManager {
    private static final String TAG = "OkHttpManager";
    public static long DEFAULT_MILLISECONDS = 10_000L;
    private static OkHttpManager mInstance;
    private OkHttpClient mOkHttpClient;
    private Platform mPlatform;
    private Handler mHandler;

    private OkHttpManager(){
        if (mOkHttpClient == null){
            mOkHttpClient = new OkHttpClient();
            mHandler = new Handler(Looper.getMainLooper());
        }
    }

    public static OkHttpManager getInstance(){
        if (mInstance == null){
            mInstance = new OkHttpManager();
        }
        return mInstance;
    }

    public OkHttpClient getmOkHttpClient(){
        return mOkHttpClient;
    }

    public void execute(OkHttpRequest request, final Class className, final GetDataCallback callback){
        request.getCall().enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailed(call,e);
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                String json = response.body().string();
                Log.i("TAG",json);
                if (!TextUtils.isEmpty(json)){
                    final Object data = JSON.parseObject(json,className);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSucceed(call,data);
                        }
                    });
                }else {
                    Log.e(TAG,"recommend data is null !");
                }
            }
        });
    }

    public void cancleByTag(String tag){
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()){
            if (tag.equals(call.request().tag()) && !call.isCanceled())
                call.cancel();
        }

        for (Call call : mOkHttpClient.dispatcher().runningCalls())
        {
            if (tag.equals(call.request().tag()))
                call.cancel();
        }
    }

    public void cancleAll(){
        mOkHttpClient.dispatcher().cancelAll();
    }

}