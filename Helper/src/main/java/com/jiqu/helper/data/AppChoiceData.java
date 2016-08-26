package com.jiqu.helper.data;

import java.util.List;

/**
 * Created by xiongweihua on 2016/8/11.
 */
public class AppChoiceData {
    /**
     * data1 : []
     * data2 : []
     * status : 1
     */

    private int status;
    private List<GameInfo> data1;
    private List<GameInfo> data2;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<GameInfo> getData1() {
        return data1;
    }

    public void setData1(List<GameInfo> data1) {
        this.data1 = data1;
    }

    public List<GameInfo> getData2() {
        return data2;
    }

    public void setData2(List<GameInfo> data2) {
        this.data2 = data2;
    }
}
