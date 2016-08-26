package com.jiqu.helper.data;

import java.util.List;

/**
 * Created by xiongweihua on 2016/8/12.
 */
public class MineData {
    /**
     * status : 1
     * data : []
     */

    private int status;
    private List<RecommendClassificationItemData> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<RecommendClassificationItemData> getData() {
        return data;
    }

    public void setData(List<RecommendClassificationItemData> data) {
        this.data = data;
    }
}
