package cn.dooer.ydz.retrofitrxtest;



import rx.functions.Func1;

/**
 * Created by zex on 2017/8/13.
 */

public class ServerResultFunc<T> implements Func1<HttpResult<T>, T> {
    @Override
    public T call(HttpResult<T> httpResult) {
        if (httpResult.getSta() != 0) {
            throw new ServerException(httpResult.getSta(),httpResult.getMsg());
        }
        return httpResult.getData();
    }
}
