package cn.dooer.ydz.retrofitrxtest;

/**
 * Created by xuyezhuangT5000 on 2018/2/28.
 */

public class Login {

    @Override
    public String toString() {
        return "Login{" +
                "member=" + member +
                ", point=" + point +
                ", account=" + account +
                '}';
    }

    /**
     * member : {"memberId":2,"mobile":"15914481249","headPicUrl":{"ext":"jpg","url":"/up/pic/6/9120180130_1005311900.jpg","name":null,"size":null},"nickName":"test","memberLevel":1}
     * point : 1841667.0
     * account : 986.01
     */

    private MemberBean member;
    private double point;
    private double account;

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public static class MemberBean {
        /**
         * memberId : 2
         * mobile : 15914481249
         * headPicUrl : {"ext":"jpg","url":"/up/pic/6/9120180130_1005311900.jpg","name":null,"size":null}
         * nickName : test
         * memberLevel : 1
         */

        private int memberId;
        private String mobile;
        private HeadPicUrlBean headPicUrl;
        private String nickName;
        private int memberLevel;

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public HeadPicUrlBean getHeadPicUrl() {
            return headPicUrl;
        }

        public void setHeadPicUrl(HeadPicUrlBean headPicUrl) {
            this.headPicUrl = headPicUrl;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getMemberLevel() {
            return memberLevel;
        }

        public void setMemberLevel(int memberLevel) {
            this.memberLevel = memberLevel;
        }

        public static class HeadPicUrlBean {
            /**
             * ext : jpg
             * url : /up/pic/6/9120180130_1005311900.jpg
             * name : null
             * size : null
             */

            private String ext;
            private String url;
            private Object name;
            private Object size;

            public String getExt() {
                return ext;
            }

            public void setExt(String ext) {
                this.ext = ext;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public Object getSize() {
                return size;
            }

            public void setSize(Object size) {
                this.size = size;
            }
        }
    }
}
