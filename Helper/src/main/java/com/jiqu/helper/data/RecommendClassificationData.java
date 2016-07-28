package com.jiqu.helper.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiongweihua on 2016/7/26.
 */
public class RecommendClassificationData implements Serializable{
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
