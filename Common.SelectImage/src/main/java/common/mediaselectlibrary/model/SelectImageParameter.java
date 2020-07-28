package common.mediaselectlibrary.model;

import java.io.Serializable;

/**
 * Created by chasen on 2018/3/13.
 */

public class SelectImageParameter implements Serializable {
    //图片最大选择数量
    private int maxNumber;
    //是否需要剪裁
    private boolean needCrop;
    //剪裁图片宽高比
    private int mAspectX;
    private int mAspectY;
    //剪裁图片大小
    private int mOutputX;
    private int mOutputY;

    public SelectImageParameter() {
        this.maxNumber = 1;
    }

    public SelectImageParameter(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public SelectImageParameter(int mAspectX, int mAspectY, int mOutputX, int mOutputY) {
        this.maxNumber = 1;
        this.needCrop = true;
        this.mAspectX = mAspectX;
        this.mAspectY = mAspectY;
        this.mOutputX = mOutputX;
        this.mOutputY = mOutputY;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public boolean isNeedCrop() {
        return needCrop;
    }

    public void setNeedCrop(boolean needCrop) {
        this.needCrop = needCrop;
    }

    public int getmAspectX() {
        return mAspectX;
    }

    public void setmAspectX(int mAspectX) {
        this.mAspectX = mAspectX;
    }

    public int getmAspectY() {
        return mAspectY;
    }

    public void setmAspectY(int mAspectY) {
        this.mAspectY = mAspectY;
    }

    public int getmOutputX() {
        return mOutputX;
    }

    public void setmOutputX(int mOutputX) {
        this.mOutputX = mOutputX;
    }

    public int getmOutputY() {
        return mOutputY;
    }

    public void setmOutputY(int mOutputY) {
        this.mOutputY = mOutputY;
    }
}
