package com.xwh.interfaces;

/**
 * Created by xiongweihua on 2016/8/27.
 */
public interface OnRefreshListener {
    //正在刷新
    void refreshing();
    //刷新失败
    void refreshFail();
    //刷新成功
    void refreshComplete();
}
