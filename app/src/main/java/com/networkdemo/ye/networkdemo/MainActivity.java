package com.networkdemo.ye.networkdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.networkdemo.ye.networkdemo.basehandle.NetworkManager;
import com.networkdemo.ye.networkdemo.presenter.Presenter;
import com.networkdemo.ye.networkdemo.basehandle.Callback;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements Callback<List<String>> {

    private static final int REQUEST_PERMISSIONS_CODE = 100;
    private Disposable mDisposable;
    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private List<String> mData = new ArrayList<>();
    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //请求权限
        checkPermission();

        Presenter presenter = new Presenter(this);
        presenter.loadData();
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new Adapter(this, mData);
        mRecyclerView.setAdapter(mAdapter);

        Log.d(TAG, "onCreate: "+ NetworkManager.getNetworkInfo().getTypeName());
    }

    /**
     * 动态请求权限
     */
    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS_CODE);
        }
    }

    /**
     * 动态请求权限的处理结果
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_DENIED){
                Toast.makeText(this, "写权限被拒绝", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        mDisposable = disposable;
    }

    @Override
    public void onNext(List<String> result) {
        mData.addAll(0, result);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "onError: " + e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
