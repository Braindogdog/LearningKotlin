package common.mediaselectlibrary.model;

import android.os.Parcel;
import android.os.Parcelable;

import common.baselibrary.baseutil.EmptyUtil;

/**
 * Created by chasen on 2018/4/23.
 */

public class ImageMessage implements Parcelable {
    public static final int URL = 0;
    public static final int PATH = 1;
    public static final int RESID = 2;
    private String id;
    private String path;
    private String thums;
    private int resId;
    private int type;

    public ImageMessage() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ImageMessage(String path, int type) {
        this.path = path;
        this.type = type;
    }

    public ImageMessage(Integer resId, int type) {
        this.resId = resId;
        this.type = type;
    }


    public String getThums() {
        if (EmptyUtil.isEmpty(thums))
            return path;
        else
            return thums;
    }

    public void setThums(String thums) {
        this.thums = thums;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private ImageMessage(Parcel in) {
        path = in.readString();
        thums = in.readString();
        type = in.readInt();
        resId = in.readInt();
    }

    public static final Creator<ImageMessage> CREATOR = new Creator<ImageMessage>() {
        @Override
        public ImageMessage createFromParcel(Parcel in) {
            return new ImageMessage(in);
        }

        @Override
        public ImageMessage[] newArray(int size) {
            return new ImageMessage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeString(thums);
        dest.writeInt(type);
        dest.writeInt(resId);
    }
}
