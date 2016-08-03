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
    private List<String> data1;
    private List<List<RecommendClassificationItemData>> data2;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getData1() {
        return data1;
    }

    public void setData1(List<String> data1) {
        this.data1 = data1;
    }

    public List<List<RecommendClassificationItemData>> getData2() {
        return data2;
    }

    public void setData2(List<List<RecommendClassificationItemData>> data2) {
        this.data2 = data2;
    }
}
