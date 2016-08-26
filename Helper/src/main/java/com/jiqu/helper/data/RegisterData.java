package com.jiqu.helper.data;

import com.jiqu.database.Account;

/**
 * Created by xiongweihua on 2016/8/24.
 */
public class RegisterData {
    /**
     * status : 1
     * data : {"uid":"1","username":"12154545","nickname":"王小五","gender":"1","phone":"13828447526","qq":"28456258","photo":"http://img.123sjzs.com/123.jpg","birthday":"2016-05-11","level":"3","email":"123456@qq.com"}
     */

    private int status;

    private AccountInfo data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public AccountInfo getData() {
        return data;
    }

    public void setData(AccountInfo data) {
        this.data = data;
    }

    public static class AccountInfo {
        private String uid;
        private String username;
        private String nickname;
        private String gender;
        private String phone;
        private String qq;
        private String photo;
        private String birthday;
        private String level;
        private String email;

        public static Account toAccount(AccountInfo info){
            Account account = new Account();
            account.setBirthday(info.getBirthday());
            account.setEmail(info.getEmail());
            account.setGender(Integer.parseInt(info.getGender()));
            account.setLevel(info.getLevel());
            account.setNickname(info.getNickname());
            account.setPhone(info.getPhone());
            account.setPhoto(info.getPhoto());
            account.setQq(info.getQq());
            account.setUid(info.getUid());
            account.setUsername(info.getUsername());

            return account;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
