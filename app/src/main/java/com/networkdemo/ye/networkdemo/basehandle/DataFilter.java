package com.networkdemo.ye.networkdemo.basehandle;

import com.networkdemo.ye.networkdemo.exception.LoadException;
import com.networkdemo.ye.networkdemo.model.IntactInfo;
import com.networkdemo.ye.networkdemo.model.ResultsInfo;

import java.util.List;

import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by ye on 2017/11/19.
 *
 * 用于过滤apiservice返回的基本类型的数据
 * 如服务器返回的数据类型为
 * {
 *     code："0"
 *     message："successful"
 *     result:{}
 * }
 *
 * 通常只会关心result的数据，而code或message字段的值又指示了result数据的合法性
 * 要拿到合法的数据，需要先根据code或message的值进行校验，只有合法性的数据，才应该被使用
 * 此时可使用此类进行过滤操作，合法的数据会被返回，异常的数据会抛出一个LoadException
 */

public class DataFilter<T> implements Predicate<T> {

    //要过滤的字段的值
    private T mFieldValue;
    //期望的值
    private T mExpectation;
    //要抛出的异常信息，通常为异常的状态码
    private String mExceptionMessage;

    public DataFilter(T fieldValue, T expectation,String exceptionMessage) {
        mFieldValue = fieldValue;
        mExpectation = expectation;
        mExceptionMessage = exceptionMessage;
    }

    @Override
    public boolean test(T t) throws Exception {
        if (!mFieldValue.equals(mExpectation)){
            throw new LoadException(mExceptionMessage);
        }
        return true;
    }
}
