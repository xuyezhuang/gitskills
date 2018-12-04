package cn.dooer.ydz.retrofitrxtest;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by zex on 2017/8/13.
 */

public class HttpResultFunc<T> implements Func1<Throwable, Observable<T>> {
    @Override
    public Observable<T> call(Throwable throwable) {
        return Observable.error(ExceptionEngine.handleException(throwable));
    }
}
