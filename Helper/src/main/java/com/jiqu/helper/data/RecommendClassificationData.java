package com.jiqu.helper.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiongweihua on 2016/7/26.
 */
public class RecommendClassificationData implements Serializable{

    private int status;
    /**所有分类名**/
    private List<RecommendClassificationInfo> data1;
    /**热门软件**/
    private List<RecommendClassificationInfo> data2;
    /**热门娱乐**/
    private List<RecommendClassificationInfo> data3;
    /**热门分类**/
    private List<RecommendClassificationInfo> data4;
    /**热门游戏**/
    private List<RecommendClassificationInfo> data5;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<RecommendClassificationInfo> getData1() {
        return data1;
    }

    public void setData1(List<RecommendClassificationInfo> data1) {
        this.data1 = data1;
    }

    public List<RecommendClassificationInfo> getData2() {
        return data2;
    }

    public void setData2(List<RecommendClassificationInfo> data2) {
        this.data2 = data2;
    }

    public List<RecommendClassificationInfo> getData3() {
        return data3;
    }

    public void setData3(List<RecommendClassificationInfo> data3) {
        this.data3 = data3;
    }

    public List<RecommendClassificationInfo> getData4() {
        return data4;
    }

    public void setData4(List<RecommendClassificationInfo> data4) {
        this.data4 = data4;
    }

    public List<RecommendClassificationInfo> getData5() {
        return data5;
    }

    public void setData5(List<RecommendClassificationInfo> data5) {
        this.data5 = data5;
    }
}
