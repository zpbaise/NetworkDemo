package com.networkdemo.ye.networkdemo.basehandle;

import android.os.Environment;
import android.util.Log;


import com.networkdemo.ye.networkdemo.model.Constant;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ye on 2017/11/21.
 *
 * 一个单例管理类，用于初始化okhttpclient
 * 如指定client的超时时长，拦截器，和缓存等信息，并返回一个单例的client对象
 */

public class ClientManager {
    private static final String TAG = "ClientManager";

    private static ClientManager mInstance;
    private Cache mCache;
    private OkHttpClient mClient;

    private ClientManager() {
        initCache();
        initClient();
    }

    /**
     * 初始化client对象
     */
    private void initClient() {
        Interceptor interceptor = new CacheInterceptor();
        mClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(interceptor)
                .cache(mCache)
                .build();
    }

    /**
     * 初始化缓存
     */
    private void initCache() {
        File file = null;
        String state = Environment.getExternalStorageState();
        //创建缓存路径
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            String path = Environment.getExternalStorageDirectory().getPath();
            file = new File(path, "Android/data/com.networkdemo.ye.networkdemo/cache");
        }
        mCache = new Cache(file, Constant.CACHE_SIZE);
    }

    /**
     * @return 返回ClienltManager的单例对象
     */
    public static ClientManager getInstance() {
        if (mInstance == null) {
            synchronized (ClientManager.class) {
                if (mInstance == null) {
                    mInstance = new ClientManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * @return 返回一个单例的client对象
     */
    public OkHttpClient getClient() {
        return mClient;
    }

    /**
     * 一个本地拦截器 用于打印网络日志
     */
    private final Interceptor loggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            Log.i(TAG,String.format("Sending request %s on %s%n%s", request.url(),  chain.connection(),
                    request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.i(TAG,String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    };
}
