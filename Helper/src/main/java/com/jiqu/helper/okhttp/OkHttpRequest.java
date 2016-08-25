package com.jiqu.helper.okhttp;

import android.text.TextUtils;
import android.util.Log;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by xiongweihua on 2016/7/22.
 */
public class OkHttpRequest {
    private final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");
    private String mUrl;
    private String mTag;
    private Map<String,Object> params;
    private Map<String,String> headers;
    private Request.Builder builder = new Request.Builder();
    private OkHttpClient client;
    private Call call;
    private long readTimeOut;
    private long writeTimeOut;
    private long connTimeOut;

    public OkHttpRequest(String url,String tag,Map<String,Object> params,Map<String,String> headers){
        this.mUrl = url;
        this.mTag = tag;
        this.params = params;
        this.headers = headers;
        if (TextUtils.isEmpty(url)){
            throw new IllegalArgumentException("url can not be null !");
        }
        initBuilder("GET",null);
    }

    public OkHttpRequest(String method,String url,String tag,Map<String,Object> params,Map<String,String> headers){
        this.mUrl = url;
        this.mTag = tag;
        this.params = params;
        this.headers = headers;
        if (TextUtils.isEmpty(url)){
            throw new IllegalArgumentException("url can not be null !");
        }
        initBuilder(method,params);
    }

    private void initBuilder(String method,Map<String ,Object> body){
        builder.url(mUrl).tag(mTag);
        initHeaders();
    }

    public OkHttpRequest setReadTimeOut(long readTimeOut){
        this.readTimeOut = readTimeOut;
        return this;
    }

    public OkHttpRequest setwriteTimeOut(long writeTimeOut){
        this.writeTimeOut = writeTimeOut;
        return this;
    }

    public OkHttpRequest setconnTimeOut(long connTimeOut){
        this.connTimeOut = connTimeOut;
        return this;
    }

    public OkHttpRequest build(){
        readTimeOut = readTimeOut > 0 ? readTimeOut : OkHttpManager.DEFAULT_MILLISECONDS;
        writeTimeOut = writeTimeOut > 0 ? writeTimeOut : OkHttpManager.DEFAULT_MILLISECONDS;
        connTimeOut = connTimeOut > 0 ? connTimeOut : OkHttpManager.DEFAULT_MILLISECONDS;
        client = OkHttpManager.getInstance().getmOkHttpClient().newBuilder()
                .readTimeout(readTimeOut, TimeUnit.MILLISECONDS)
                .writeTimeout(writeTimeOut,TimeUnit.MILLISECONDS)
                .connectTimeout(connTimeOut,TimeUnit.MILLISECONDS)
                .build();
        call = client.newCall(getRequest("GET",null));
        return this;
    }

    public OkHttpRequest build(String method,RequestBody body){
        readTimeOut = readTimeOut > 0 ? readTimeOut : OkHttpManager.DEFAULT_MILLISECONDS;
        writeTimeOut = writeTimeOut > 0 ? writeTimeOut : OkHttpManager.DEFAULT_MILLISECONDS;
        connTimeOut = connTimeOut > 0 ? connTimeOut : OkHttpManager.DEFAULT_MILLISECONDS;
        client = OkHttpManager.getInstance().getmOkHttpClient().newBuilder()
                .readTimeout(readTimeOut, TimeUnit.MILLISECONDS)
                .writeTimeout(writeTimeOut,TimeUnit.MILLISECONDS)
                .connectTimeout(connTimeOut,TimeUnit.MILLISECONDS)
                .build();
        call = client.newCall(getRequest(method,body));
        return this;
    }

    public Call getCall(){
        return  call;
    }

    private Request getRequest(String method,RequestBody body){
        if ("POST".equals(method)){
            return builder.post(body).build();
        }
        return builder.get().build();
    }


    private void initHeaders(){
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers != null && !headers.isEmpty()){
            for (String key : headers.keySet()){
                headerBuilder.add(key,headers.get(key));
            }
            builder.headers(headerBuilder.build());
        }else {
            return;
        }
    }
}
