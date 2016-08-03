package com.jiqu.helper.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiongweihua on 2016/7/27.
 */
public class RecommendClassificationItemData implements Serializable{

    /**
     * id : 37
     * pic : 0
     * url :
     * name : 热门娱乐
     * intro :
     */

    private String id;
    private String pic;
    private String url;
    private String name;
    private String intro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
