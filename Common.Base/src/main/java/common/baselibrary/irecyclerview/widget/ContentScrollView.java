package common.baselibrary.irecyclerview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.ScrollView;

/**
 * Created by MHshachang on 2017/7/8.
 */

public class ContentScrollView extends ScrollView {
    private ContentScrollView.OnScrollChangedListener listener;

    public ContentScrollView(Context context) {
        super(context);
    }

    public ContentScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContentScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setOnScrollChangeListener(OnScrollChangedListener listener) {
        this.listener = listener;
    }

    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        this.listener.onScrollChanged(l, t, oldl, oldt);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        for(ViewParent parent = this.getParent(); parent != null; parent = parent.getParent()) {
            if(parent instanceof ScrollLayout) {
                ((ScrollLayout)parent).setAssociatedScrollView(this);
                break;
            }
        }

    }
    public boolean onTouchEvent(MotionEvent ev) {
        ViewParent parent = this.getParent();
        return parent instanceof ScrollLayout && ((ScrollLayout)parent).getCurrentStatus() == ScrollLayout.Status.OPENED?false:super.onTouchEvent(ev);
    }

    public interface OnScrollChangedListener {
        void onScrollChanged(int var1, int var2, int var3, int var4);
    }
}
