package com.jiqu.helper.data;

import java.util.List;

/**
 * Created by xiongweihua on 2016/7/27.
 */
public class RecommendFindingsData {

    /**
     * status : 1
     * data : []
     */

    private int status;
    private List<RecommendFindingsItemInfo> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<RecommendFindingsItemInfo> getData() {
        return data;
    }

    public void setData(List<RecommendFindingsItemInfo> data) {
        this.data = data;
    }
}
