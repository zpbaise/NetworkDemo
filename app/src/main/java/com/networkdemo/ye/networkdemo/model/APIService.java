package com.networkdemo.ye.networkdemo.model;

import com.networkdemo.ye.networkdemo.model.IntactInfo;
import com.networkdemo.ye.networkdemo.model.ResultsInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ye on 2017/10/29.
 */

public interface APIService {
    /**
     * http://gank.io/api/data/福利/10/1 网址
     *type 类型
     * count 请求的数量 >0
     * page 请求的页数 >0
    */
    @GET("data/{type}/{count}/{page}")
    Observable<IntactInfo<ResultsInfo>> meiNv(@Path("type") String type, @Path("count") int count, @Path("page") int page);
}
