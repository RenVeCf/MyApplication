package com.example.ipd.yueyue.progress;

import com.example.ipd.yueyue.utils.ExceptionHandle.ResponeThrowable;

/**
 * 请求监听
 */

public interface ObserverResponseListener<T> {
    /**
     * 响应成功
     * @param t
     */
    void onNext(T t);

    /**
     * 响应失败
     * @param e
     */
    void onError(ResponeThrowable e);
}
