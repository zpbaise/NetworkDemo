package com.networkdemo.ye.networkdemo.basehandle;

import com.networkdemo.ye.networkdemo.exception.LoadException;
import com.networkdemo.ye.networkdemo.model.IntactInfo;

import java.util.List;

import io.reactivex.functions.Function;

/**
 * Created by ye on 2017/11/20.
 *
 * 用于过滤apiservice返回的基本类型的数据
 * 如服务器返回的数据类型为
 * {
 *     error：flase
 *     results:{}
 * }
 *
 * 通常只会关心results的数据，而error字段的值又指示了results数据的合法性
 * 要拿到合法的数据，需要先根据error的值进行校验，只有合法性的数据，才应该被使用
 * 此时可使用此类进行去壳操作，合法的数据会results的值，异常的数据会抛出一个LoadException
 */

public class Peel<T> implements Function<IntactInfo<T>, List<T>> {

    @Override
    public List<T> apply(IntactInfo<T> resultsInfoIntactInfo) throws Exception {
        if (resultsInfoIntactInfo.isError()){
            throw new LoadException(""+resultsInfoIntactInfo.isError());
        }
        return resultsInfoIntactInfo.getResults();
    }
}
