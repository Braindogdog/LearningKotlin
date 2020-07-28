package common.baselibrary.baseview.wheelview;

/**
 * Created by chasen on 2018/3/22.
 */

public interface WheelAdapter<T> {
    int getItemsCount();

    T getItem(int index);

    int indexOf(T o);
}
