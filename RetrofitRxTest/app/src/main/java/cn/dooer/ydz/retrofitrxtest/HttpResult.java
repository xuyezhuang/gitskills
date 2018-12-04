package cn.dooer.ydz.retrofitrxtest;

/**
 * Created by zex on 2017/8/13.
 */

public class HttpResult<T> { //指定本来里面的所有的泛型都是T，不是代表HttpResult是类型T，
    // 当本类中所有的泛型都是同一个的时候就可以直接在类后面加<限定符>
    private String code;  //1代表成功
    private String msg;

    private T data;

    public int getSta(){
        return Integer.parseInt(code);
    }

    public String getMsg(){
        return msg;
    }

    public T getData(){
        return data;
    }
}
