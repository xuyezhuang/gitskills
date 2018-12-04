package cn.dooer.ydz.retrofitrxtest;

/**
 * Created by zex on 2017/9/11.
 */

public class LoginInfo {

    /**
     * mobile : 15013215653
     * nickName : zex
     * memberId : 3
     */

    private String mobile;
    private String nickName;
    private int memberId;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getMemberId() {
        return memberId;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "mobile='" + mobile + '\'' +
                ", nickName='" + nickName + '\'' +
                ", memberId=" + memberId +
                '}';
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
