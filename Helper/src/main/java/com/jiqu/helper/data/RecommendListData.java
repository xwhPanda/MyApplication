package com.jiqu.helper.data;

import java.util.List;

/**
 * Created by xiongweihua on 2016/7/28.
 */
public class RecommendListData {

    /**
     * status : 1
     * data : []
     */

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
