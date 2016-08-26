package com.jiqu.helper.data;

import java.util.List;

/**
 * Created by xiongweihua on 2016/8/19.
 */
public class DetailCorrelationData {
    /**
     * data : [{"id":"35","apply_name":"高德导航","descript":"AutoNavi","e_name":"AutoNavi","column":"44","version":"1","size":"100","type":"应用","down":"100","down_url":"http://download.123sjzs.com/koios/Apk/www.down.com","icon":"http://koa.77gamebox.com/Upload/Apply/2016-07-13/5785a66281eff.jpg","score":"1.0","star":"0.0","package_name":"com.autonavi.xmgd.navigator","version_name":"9.6.8803.2549","gift_id":"","siteID":"1","statisticsID":"120"},{"id":"34","apply_name":"土豆视频","descript":"土豆视频","e_name":"","column":"42","version":"1","size":"100","type":"应用","down":"100","down_url":"http://download.123sjzs.com/koios/Apk/www.down.com","icon":"http://koa.77gamebox.com/Upload/Apply/2016-07-13/5785a527d6b79.jpg","score":"1.0","star":"0.0","package_name":"com.tudou.android","version_name":"5.8.4","gift_id":"","siteID":"2","statisticsID":"120"},{"id":"33","apply_name":"微信","descript":"微信","e_name":"WeChat","column":"41","version":"1","size":"100","type":"应用","down":"100","down_url":"http://download.123sjzs.com/koios/Apk/www.down.com","icon":"http://koa.77gamebox.com/Upload/Apply/2016-07-13/5785a38ce87be.jpg","score":"1.0","star":"0.0","package_name":"com.tencent.mm","version_name":"6.3.22","gift_id":"","siteID":"3","statisticsID":"120"}]
     * label : [""]
     * status : 1
     */

    private int status;

    private List<GameInfo> data;
    private List<String> label;

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

    public List<String> getLabel() {
        return label;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }


}
