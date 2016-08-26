package com.jiqu.helper.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xiongweihua on 2016/7/30.
 */
public class RecommendPrerequisitesData implements Serializable{

    private int status;
    private List<RecommendPrerequisitesInfo> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<RecommendPrerequisitesInfo> getData() {
        return data;
    }

    public void setData(List<RecommendPrerequisitesInfo> data) {
        this.data = data;
    }

    public class RecommendPrerequisitesInfo implements Serializable{

        private String id;
        private String pic;
        private String url;
        private String name;
        private String intro;
        private List<GameInfo> col;

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

        public List<GameInfo> getCol() {
            return col;
        }

        public void setCol(List<GameInfo> col) {
            this.col = col;
        }
    }
}
