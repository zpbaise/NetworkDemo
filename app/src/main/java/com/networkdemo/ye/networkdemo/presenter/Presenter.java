package com.networkdemo.ye.networkdemo.presenter;

import android.util.Log;

import com.networkdemo.ye.networkdemo.basehandle.Callback;
import com.networkdemo.ye.networkdemo.basehandle.ModelPresenter;
import com.networkdemo.ye.networkdemo.basehandle.ObservableTransformerImp;
import com.networkdemo.ye.networkdemo.basehandle.Peel;
import com.networkdemo.ye.networkdemo.model.ResultsInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created by ye on 2017/11/19.
 *
 * 应用层 对可应用数据进行处理
 */

public class Presenter {
    private static final String TAG = "Presenter";
    private Callback mCallback;
    private Peel<ResultsInfo> mPeel;

    public Presenter(Callback callback) {
        mCallback = callback;
        mPeel = new Peel<>();
    }

    public void loadData() {
        if (mPeel == null){
            return;
        }
        ModelPresenter.getResults(mPeel)
                .flatMap(new Function<List<ResultsInfo>, ObservableSource<List<String>>>() {//对数据进行变换 返回url地址的集合
                    @Override
                    public ObservableSource<List<String>> apply(List<ResultsInfo> resultsInfos) throws Exception {
                        List<String> urls = new ArrayList<>();
                        for (ResultsInfo resultsInfo : resultsInfos) {
                            urls.add(resultsInfo.getUrl());
                        }
                        return Observable.just(urls);
                    }
                })
                .compose(new ObservableTransformerImp<List<String>>())//对observable进行线程变换
                .subscribe(new Observer<List<String>>() {//订阅observer对象，绑定callback
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCallback.onSubscribe(d);
                    }

                    @Override
                    public void onNext(List<String> strings) {
                        mCallback.onNext(strings);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mCallback.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        mCallback.onComplete();
                    }
                });
    }
}
