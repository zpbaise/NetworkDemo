package com.networkdemo.ye.networkdemo.model;

import com.networkdemo.ye.networkdemo.basehandle.NetworkManager;

import io.reactivex.Observable;

/**
 * Created by ye on 2017/11/20.
 *
 */

public class ServiceFactory {
    /**
     * 返回一个Observable对象
     * @param type 类型
     * @param count 一次加载的数据数量
     * @param page 加载的页数
     * @return
     */
    public static Observable<IntactInfo<ResultsInfo>> getMeinvService(String type, int count, int page){
        return NetworkManager.getInstance().getRetrofit().create(APIService.class).meiNv(type,count,page);
    }
}
