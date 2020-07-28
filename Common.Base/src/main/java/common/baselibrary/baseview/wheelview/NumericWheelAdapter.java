package common.baselibrary.baseview.wheelview;

/**
 * Created by chasen on 2018/3/22.
 */

public class NumericWheelAdapter implements WheelAdapter {

    public static final int DEFAULT_MAX_VALUE = 9;

    private static final int DEFAULT_MIN_VALUE = 0;

    private int minValue;
    private int maxValue;

    public NumericWheelAdapter() {
        this(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
    }

    public NumericWheelAdapter(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public Object getItem(int index) {
        if (index >= 0 && index < getItemsCount()) {
            int value = minValue + index;
            return value;
        }
        return 0;
    }

    @Override
    public int getItemsCount() {
        return maxValue - minValue + 1;
    }

    @Override
    public int indexOf(Object o) {
        return (int) o - minValue;
    }
}