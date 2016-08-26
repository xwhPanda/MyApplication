package com.jiqu.helper.data;

import java.util.List;

/**
 * Created by xiongweihua on 2016/8/1.
 */
public class GameChoiceTopData {

    /**
     * data1 : []
     * data2 : []
     * status : 1
     */

    private String status;
    private List<RecommendChoiceAdInfo> data1;
    private List<RecommendClassificationItemData> data2;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RecommendChoiceAdInfo> getData1() {
        return data1;
    }

    public void setData1(List<RecommendChoiceAdInfo> data1) {
        this.data1 = data1;
    }

    public List<RecommendClassificationItemData> getData2() {
        return data2;
    }

    public void setData2(List<RecommendClassificationItemData> data2) {
        this.data2 = data2;
    }
}
