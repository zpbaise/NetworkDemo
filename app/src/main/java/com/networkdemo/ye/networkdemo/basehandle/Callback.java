package com.networkdemo.ye.networkdemo.basehandle;

import com.networkdemo.ye.networkdemo.model.ResultsInfo;

import io.reactivex.disposables.Disposable;

/**
 * Created by ye on 2017/11/19.
 *
 * presenter的回调接口，与RxJava的Observer同生命周期
 * 实现此对象 可用针对请求的状态做出响应
 */

public interface Callback<T> {
    /**
     * 在数据返回之前会被调用，可用于取消数据的接收
     * @param disposable 在activity和fragment的onDestory方法中可使用此对象取消异步任务
     */
    void onSubscribe(Disposable disposable);

    /**
     * RxJava的Observer对象的onNext方法被触发时回调此方法
     * @param result 请求到的数据
     */
    void onNext(T result);

    /**
     * RxJava的Observer对象的onError方法被触发时回调此方法，可针对e的具体类型进行相应的异常处理
     * @param e
     */
    void onError(Throwable e);

    /**
     * RxJava的Observer对象的onComplete方法被触发时回调此方法
     */
    void onComplete();
}
