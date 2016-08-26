package com.jiqu.helper.data;

import java.util.List;

/**
 * Created by xiongweihua on 2016/8/22.
 */
public class SearchContentData {
    private int status;

    private List<GameInfo> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<GameInfo> getData() {
        return data;
    }

    public void setData(List<GameInfo> data) {
        this.data = data;
    }
}
