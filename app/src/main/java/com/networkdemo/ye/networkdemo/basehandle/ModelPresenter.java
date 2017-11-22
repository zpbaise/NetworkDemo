package com.networkdemo.ye.networkdemo.basehandle;

import android.util.Log;

import com.networkdemo.ye.networkdemo.model.IntactInfo;
import com.networkdemo.ye.networkdemo.model.ResultsInfo;
import com.networkdemo.ye.networkdemo.model.ServiceFactory;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


/**
 * Created by ye on 2017/11/19.
 *
 * 对源数据进行去壳操作,返回只关心的合法的数据
 */

public class ModelPresenter {

    public static Observable<List<ResultsInfo>> getResults(Peel<ResultsInfo> peel) {
        Observable<IntactInfo<ResultsInfo>> observable = ServiceFactory.getMeinvService("福利", 20, 1);
        return observable.map(peel);
    }
}
