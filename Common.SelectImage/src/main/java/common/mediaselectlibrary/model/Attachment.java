package common.mediaselectlibrary.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Zyq
 * @date 2019/12/11
 * 附件
 */
public class Attachment implements Parcelable {
    /**
     * id
     */
    @SerializedName("Id")
    private String id;
    /**
     * name
     */
    @SerializedName("Name")
    private String name;
    /**
     * 地址
     */
    @SerializedName("Url")
    private String url;
    /**
     * 文件大小
     */
    @SerializedName("Filesize")
    private String fileSize;
    /**
     * 后缀名
     */
    @SerializedName("Extensionname")
    private String extensionName;

    public Attachment() {

    }
    protected Attachment(Parcel in) {
        id = in.readString();
        name = in.readString();
        url = in.readString();
        fileSize = in.readString();
        extensionName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(url);
        dest.writeString(fileSize);
        dest.writeString(extensionName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Attachment> CREATOR = new Creator<Attachment>() {
        @Override
        public Attachment createFromParcel(Parcel in) {
            return new Attachment(in);
        }

        @Override
        public Attachment[] newArray(int size) {
            return new Attachment[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getExtensionName() {
        return extensionName;
    }

    public void setExtensionName(String extensionName) {
        this.extensionName = extensionName;
    }

}
