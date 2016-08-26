package com.jiqu.helper.data;

import java.util.List;

/**
 * Created by xiongweihua on 2016/8/19.
 */
public class CommentData {
    /**
     * data : [{"id":"1","uid":"1","aid":"1","content":"好垃圾的APP","time":"2016-07-26","username":"佚名用户","photo":"http://koa.77gamebox.com/Upload/Photo/male.png"},{"id":"2","uid":"1","aid":"1","content":"好垃圾的APP","time":"2016-07-27","username":"佚名用户","photo":"http://koa.77gamebox.com/Upload/Photo/male.png"},{"id":"3","uid":"1","aid":"1","content":"好垃圾的APP","time":"2016-07-27","username":"sjash1sssd","photo":"http://koa.77gamebox.com/Upload/Photo/male.png"},{"id":"4","uid":"1","aid":"1","content":"好垃圾的APP","time":"2016-07-27","username":"sjash1sssd","photo":"http://koa.77gamebox.com/Upload/Photo/male.png"}]
     * count : 7
     * status : 1
     */

    private int count;
    private int status;
    /**
     * id : 1
     * uid : 1
     * aid : 1
     * content : 好垃圾的APP
     * time : 2016-07-26
     * username : 佚名用户
     * photo : http://koa.77gamebox.com/Upload/Photo/male.png
     */

    private List<CommentInfo> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CommentInfo> getData() {
        return data;
    }

    public void setData(List<CommentInfo> data) {
        this.data = data;
    }

    public static class CommentInfo {
        private String id;
        private String uid;
        private String aid;
        private String content;
        private String time;
        private String username;
        private String photo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
