package com.networkdemo.ye.networkdemo.basehandle;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.networkdemo.ye.networkdemo.MyApplication;
import com.networkdemo.ye.networkdemo.model.Constant;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ye on 2017/11/19.
 * <p>
 * 一个单例类，用于管理Retrofit和网络状态
 */

public class NetworkManager {
    private static final String TAG = "NetworkManager";

    private static NetworkManager mInstance;
    private Retrofit mRetrofit;

    private NetworkManager() {
        OkHttpClient client = ClientManager.getInstance().getClient();

        mRetrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * @return 一个线程安全的方法，返回NetworkManager的单例对象
     */
    public static NetworkManager getInstance() {
        if (mInstance == null) {
            synchronized (NetworkManager.class) {
                if (mInstance == null) {
                    mInstance = new NetworkManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 返回一个Retrofit的单例对象
     *
     * @return
     */
    public Retrofit getRetrofit() {
        return mRetrofit;
    }


    /**
     * @return 返回设备的网络信息 ，如果无法获取网络信息 返回null
     */
    public static NetworkInfo getNetworkInfo() {
        Context context = MyApplication.mInstance;
        Log.d(TAG, "getNetworkInfo: "+context);
        if (context != null) {
            ConnectivityManager mConnectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return mConnectivityManager.getActiveNetworkInfo();
        }
        return null;
    }

    /**
     * 判断是否有网络
     *
     * @return true有网络，false无网络
     */
    public static boolean isNetworkConnected() {
        NetworkInfo networkInfo = getNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isAvailable();
        }
        return false;
    }


    /**
     * @return one of {@link ConnectivityManager#TYPE_MOBILE}, {@link
     * ConnectivityManager#TYPE_WIFI}, {@link ConnectivityManager#TYPE_WIMAX}, {@link
     * ConnectivityManager#TYPE_ETHERNET},  {@link ConnectivityManager#TYPE_BLUETOOTH}, or other
     * types defined by {@link ConnectivityManager}
     * 无法获取网络类型 返回-1
     */
    public static int getNetworkType() {
        NetworkInfo networkInfo = getNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.getType();
        }
        return -1;
    }

}
