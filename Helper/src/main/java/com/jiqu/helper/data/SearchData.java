package com.jiqu.helper.data;

import java.util.List;

/**
 * Created by xiongweihua on 2016/8/19.
 */
public class SearchData {
    /**
     * status : 1
     * data : [[{"id":"4","hot":"1","content":"找你妹","pid":"1"},{"id":"5","hot":"1","content":"泡泡龙","pid":"1"},{"id":"6","hot":"1","content":"COS大乱斗","pid":"1"}],[{"id":"7","hot":"1","content":"1号店","pid":"2"},{"id":"8","hot":"1","content":"日语学习","pid":"2"},{"id":"9","hot":"1","content":"计算机","pid":"2"}],[{"id":"13","hot":"0","content":"91助手","pid":"3"},{"id":"14","hot":"0","content":"百度导航","pid":"3"},{"id":"15","hot":"0","content":"火柴人联盟","pid":"3"},{"id":"16","hot":"0","content":"微信","pid":"3"},{"id":"17","hot":"0","content":"小红书","pid":"3"},{"id":"18","hot":"0","content":"XY手机助手","pid":"3"},{"id":"19","hot":"0","content":"应用宝","pid":"3"},{"id":"20","hot":"0","content":"360","pid":"3"},{"id":"21","hot":"0","content":"相机","pid":"3"},{"id":"22","hot":"0","content":"视频","pid":"3"},{"id":"23","hot":"0","content":"音乐","pid":"3"},{"id":"24","hot":"0","content":"浏览器","pid":"3"},{"id":"25","hot":"0","content":"系统","pid":"3"},{"id":"26","hot":"0","content":"电子书","pid":"3"},{"id":"27","hot":"0","content":"新闻","pid":"3"}]]
     */

    private int status;
    /**
     * id : 4
     * hot : 1
     * content : 找你妹
     * pid : 1
     */

    private List<List<SearchInfo>> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<List<SearchInfo>> getData() {
        return data;
    }

    public void setData(List<List<SearchInfo>> data) {
        this.data = data;
    }

    public static class SearchInfo {
        private String id;
        private String hot;
        private String content;
        private String pid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }
    }
}
