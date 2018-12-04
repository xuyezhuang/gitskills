package cn.dooer.ydz.retrofitrxtest.net;

/**
 * Created by zex on 2017/8/13.
 */

public class ApiException extends Exception{
    private int code;
    private String msg;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;

    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
