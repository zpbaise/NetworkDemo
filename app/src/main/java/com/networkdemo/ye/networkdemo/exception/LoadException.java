package com.networkdemo.ye.networkdemo.exception;

/**
 * Created by ye on 2017/11/19.
 * 请求数据时，如果用于标记数据状态的字段error为true时抛出的异常
 * 用于指示返回的数据异常
 */

public class LoadException extends RuntimeException {
    public LoadException(String iserror) {
        super(iserror);
    }
}
