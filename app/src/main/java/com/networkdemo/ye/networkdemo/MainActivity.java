package com.networkdemo.ye.networkdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.networkdemo.ye.networkdemo.presenter.Presenter;
import com.networkdemo.ye.networkdemo.basehandle.Callback;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements Callback<List<String>> {

    private Disposable mDisposable;
    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private List<String> mData = new ArrayList<>();
    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Presenter presenter = new Presenter(this);
        presenter.loadData();
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new Adapter(this, mData);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        mDisposable = disposable;
    }

    @Override
    public void onNext(List<String> result) {
        Log.d(TAG, "onNext: "+result);
        mData.addAll(0,result);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && mDisposable.isDisposed()){
            mDisposable.dispose();
        }
    }
}
