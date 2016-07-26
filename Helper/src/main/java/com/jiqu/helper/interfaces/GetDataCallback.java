package com.jiqu.helper.interfaces;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by xiongweihua on 2016/7/25.
 */
public interface GetDataCallback {
    void onFailed(Call call, IOException e);
    void onSucceed(Call call,Object data);
}
