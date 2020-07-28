package common.baselibrary.irecyclerview;

/**
 * Created by chasen on 2018/3/21.
 */

public interface RefreshHeaderViewTrigger {
    void onStart(boolean automatic, int headerHeight, int finalHeight);

    void onMove(boolean finished, boolean automatic, int moved);

    void onRefresh();

    void onRelease();

    void onComplete();

    void onReset();
}
