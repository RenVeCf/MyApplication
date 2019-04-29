package com.example.administrator.myapplication.common.view;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2019/4/20.
 */

public final class CustomLoadMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.null_load_more_view;
    }

    @Override
    protected int getLoadingViewId() {
        return 0;
    }

    @Override
    protected int getLoadFailViewId() {
        return 0;
    }

    @Override
    protected int getLoadEndViewId() {
        return 0;
    }
}
