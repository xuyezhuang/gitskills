package cn.dooer.ydz.retrofitrxtest;

import java.util.List;

public class Test {

    private List<UserInfoBean> userInfo;

    public List<UserInfoBean> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<UserInfoBean> userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        /**
         * displayID : 13382
         * gender : 男
         * iconUrl : https://tstatic.jianlilian.com/icons/824aa852579371bc0067cb4425261f1c.jpeg
         * isNotify : 1
         * name : cc
         * pushId :
         * selfIntro : ddfhhhhhhhhh
         * token : eyJhbGciOiJIUzI1NiIsImlhdCI6MTUzMTQ2ODY0MywiZXhwIjoxNTMxNDcyMjQ0fQ.eyJ1c2VyX2lkIjoiMiIsImlhdCI6MTUzMTQ2ODY0My4yNjUyNTgzfQ.TQbs6CxGCdGvPQFtCqAXp8FPf-XC_dr4SllhRtg_VMU
         * userId : 2
         */

        private String displayID;
        private String gender;
        private String iconUrl;
        private int isNotify;
        private String name;
        private String pushId;
        private String selfIntro;
        private String token;
        private int userId;

        public String getDisplayID() {
            return displayID;
        }

        public void setDisplayID(String displayID) {
            this.displayID = displayID;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public int getIsNotify() {
            return isNotify;
        }

        public void setIsNotify(int isNotify) {
            this.isNotify = isNotify;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPushId() {
            return pushId;
        }

        public void setPushId(String pushId) {
            this.pushId = pushId;
        }

        public String getSelfIntro() {
            return selfIntro;
        }

        public void setSelfIntro(String selfIntro) {
            this.selfIntro = selfIntro;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
