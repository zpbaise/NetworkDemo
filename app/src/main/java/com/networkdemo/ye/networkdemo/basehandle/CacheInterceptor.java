package com.networkdemo.ye.networkdemo.basehandle;

import com.networkdemo.ye.networkdemo.model.Constant;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ye on 2017/11/22.
 *
 * 网络拦截器 用于定义缓存策略，有网络连接是 强制使用网络数据没有网络时强制使用缓存数据
 */

public class CacheInterceptor implements Interceptor {

    private boolean mNetworkConnected;

    public CacheInterceptor() {
        mNetworkConnected = NetworkManager.isNetworkConnected();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();//获取请求

        //有网络权限是时强制使用网络数据
        if (mNetworkConnected) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();
            return chain.proceed(request).newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", Constant.CACHE_CONTROL_AGE)
                    .build();
        } else {
            //没有网络时强制使用缓存数据
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            return chain.proceed(request).newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", Constant.CACHE_CONTROL_STALE)
                    .build();
        }
    }
}
