package common.mediaselectlibrary.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 *
 * @author zhangmh
 * @date 2020/2/11 10:36
 */
@Data
public class EventMedia implements Parcelable {

    /**
     * attachment表中的图片id
     */
    private String id;

    /**
     * 事件id
     */
    private String relId;

    /**
     * 类型
     * 事前、事中、事后
     */
    private String type;

    /**
     * 创建时间
     */
    private Date createtime = new Date();

    /**
     * 创建人
     */
    private String userid;

    private String username;

    /**
     * 创建单位
     */
    private String unitid;


    private String unitName;

    /**
     * 图片类型
     * 默认为 images
     * 图片应该都是这个类型，之后和PC端确认
     */
    private String filetype="images";

    /**
     * 存放路径
     * 无意义，不是请求路径，只有后台才会用到
     */
    private String url;

    /**
     * 文件名称
     */
    private String name;


    protected EventMedia(Parcel in) {
        id = in.readString();
        relId = in.readString();
        type = in.readString();
        userid = in.readString();
        username = in.readString();
        unitid = in.readString();
        unitName = in.readString();
        filetype = in.readString();
        url = in.readString();
        name = in.readString();
    }

    public static final Creator<EventMedia> CREATOR = new Creator<EventMedia>() {
        @Override
        public EventMedia createFromParcel(Parcel in) {
            return new EventMedia(in);
        }

        @Override
        public EventMedia[] newArray(int size) {
            return new EventMedia[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(relId);
        parcel.writeString(type);
        parcel.writeString(userid);
        parcel.writeString(username);
        parcel.writeString(unitid);
        parcel.writeString(unitName);
        parcel.writeString(filetype);
        parcel.writeString(url);
        parcel.writeString(name);
    }
}
