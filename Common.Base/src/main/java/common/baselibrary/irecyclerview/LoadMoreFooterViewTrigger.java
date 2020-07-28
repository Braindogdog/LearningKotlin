package common.baselibrary.irecyclerview;

/**
 * Created by chasen on 2018/3/26.
 */

public interface LoadMoreFooterViewTrigger {

    void onLoading();

    void onComplete();

    void onError();

    void onTheEnd();
}