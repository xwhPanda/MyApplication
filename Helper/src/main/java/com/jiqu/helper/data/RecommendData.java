package com.jiqu.helper.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiongweihua on 2016/7/25.
 */
public class RecommendData implements Serializable{
    /**状态码**/
    private int status;
    /**推荐头部广告**/
    private List<RecommendChoiceAdInfo> data1;
    /**精选必玩**/
    private List<GameInfo> data2;
    /**一键装机**/
    private List<GameInfo> data3;
    /**推荐精选**/
    private List<GameInfo> data4;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<RecommendChoiceAdInfo> getData1() {
        return data1;
    }

    public void setData1(List<RecommendChoiceAdInfo> data1) {
        this.data1 = data1;
    }

    public List<GameInfo> getData2() {
        return data2;
    }

    public void setData2(List<GameInfo> data2) {
        this.data2 = data2;
    }

    public List<GameInfo> getData3() {
        return data3;
    }

    public void setData3(List<GameInfo> data3) {
        this.data3 = data3;
    }

    public List<GameInfo> getData4() {
        return data4;
    }

    public void setData4(List<GameInfo> data4) {
        this.data4 = data4;
    }
}