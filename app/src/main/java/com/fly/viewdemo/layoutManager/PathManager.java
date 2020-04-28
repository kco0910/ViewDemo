package com.fly.viewdemo.layoutManager;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Fj on 2018/11/2.
 */
public class PathManager extends RecyclerView.LayoutManager {


    private final float mLength;
    private int mItemgap;
    private int mDirection;

    public PathManager(Path path,int itemGap,int direction){
        PathMeasure pathMeasure = new PathMeasure(path,false);
        mLength = pathMeasure.getLength();
        mItemgap = itemGap;
        mDirection = direction;
    }


    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public boolean canScrollVertically() {
        return mDirection == RecyclerView.VERTICAL;
    }

    @Override
    public boolean canScrollHorizontally() {
        return mDirection == RecyclerView.HORIZONTAL;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);

    }
}
