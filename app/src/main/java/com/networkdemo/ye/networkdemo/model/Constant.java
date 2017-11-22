package com.networkdemo.ye.networkdemo.model;

/**
 * Created by ye on 2017/11/19.
 * 全局的常量
 */

public class Constant {

    /**
     * 网络的主机地址
     */
    public static final String BASE_URL = "http://gank.io/api/";

    /**
     * 缓存大小5M
     */
    public static final long CACHE_SIZE = 1024 * 1024 * 5;

    /**
     * max-stale时长24小时
     */
    public static final long CACHE_MAX_STALE = 60 * 60 * 24 * 1;
    /**
     * max-age时长60s
     */
    public static final long CACHE_MAX_AGE = 60;

    /**
     * cache_control的值 缓存模式为private,only-if-cache, max-age=60
     */
    public static final String CACHE_CONTROL_AGE = "private,only-if-cache, max-age=" + CACHE_MAX_AGE;

    /**
     * cache_control的值 缓存模式为private,only-if-cache, max-age=2419200
     */
    public static final String CACHE_CONTROL_STALE = "private, only-if-cache,max-age=" + CACHE_MAX_STALE;
}
