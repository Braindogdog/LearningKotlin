package common.baselibrary.irecyclerview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;

import common.baselibrary.irecyclerview.IRecyclerView;

/**
 * Created by MHshachang on 2017/7/8.
 */

public class ScrollerRecyclerView extends IRecyclerView {
    private ScrollLayout scrollLayout;
    private View shadowView;

    public ScrollerRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ScrollerRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollerRecyclerView(Context context) {
        super(context);
    }

    public void setTopShadowView(View shadowView) {
        if (shadowView == null) {
            return;
        }
        this.shadowView = shadowView;
    }

    private void showTopShadow() {
        if (shadowView == null || shadowView.getVisibility() == View.VISIBLE) {
            return;
        }
        shadowView.setVisibility(View.VISIBLE);
    }

    private void hideTopShadow() {
        if (shadowView == null || shadowView.getVisibility() == View.GONE) {
            return;
        }
        shadowView.setVisibility(View.GONE);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        while (parent != null) {
            if (parent instanceof ScrollLayout) {
                ((ScrollLayout) parent).setAssociatedListView(this);
                break;
            }
            parent = parent.getParent();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public ScrollLayout getScrollLayout() {
        return scrollLayout;
    }

    public void setScrollLayout(ScrollLayout scrollLayout) {
        this.scrollLayout = scrollLayout;
    }
}
