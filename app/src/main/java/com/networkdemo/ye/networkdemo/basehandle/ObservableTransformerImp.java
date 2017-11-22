package com.networkdemo.ye.networkdemo.basehandle;

import android.icu.lang.UProperty;

import com.networkdemo.ye.networkdemo.model.ResultsInfo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ye on 2017/11/19.
 *
 * 一个Observable的变换处理
 */

public class ObservableTransformerImp<T> implements ObservableTransformer<T, T> {
    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
