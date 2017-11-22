package com.networkdemo.ye.networkdemo;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;

import java.util.List;

/**
 * Created by ye on 2017/11/20.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private static final String TAG = "Adapter";
    private Context mContext;
    private List<String> mData;

    public Adapter(Context context,List<String> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(mContext);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400);
        imageView.setLayoutParams(layoutParams);
        return new ViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final String url = mData.get(position);
        holder.mImageView.setTag(R.id.recyclerview_image_tag,url);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Glide.with(mContext).load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView;
        }
    }
}
