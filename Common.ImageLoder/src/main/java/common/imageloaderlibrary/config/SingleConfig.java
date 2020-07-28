package common.imageloaderlibrary.config;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.Animation;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.io.File;

import common.imageloaderlibrary.util.ImageUtil;

/**
 * Created by chasen on 2018/3/12.
 */

public class SingleConfig {
    private Context context;
    private String url;

    private float thumbnail; //缩略图缩放倍数
    private String filePath; //文件路径

    private File file; //文件路径
    private int resId;  //资源id
    private String rawPath;  //raw路径
    private String assetspath;  //asserts路径
    private String contentProvider; //内容提供者
    private boolean isGif; //是否是GIF图
    private View target;
    private int width;
    private int height;
    private int oWidth;
    private int oHeight;

    private int priority;//优先级

    private int animationType;//动画类型
    private int animationId;
    private Animation animation;

    private int placeHolderResId;//占位图
    private int errorResId;//错误图

    private int shapeMode;//默认矩形,可选直角矩形,圆形/椭圆
    private int rectRoundRadius;//圆角矩形时圆角的半径
    private DiskCacheStrategy diskCacheStrategy;
    private int scaleMode;//填充模式,默认fitcenter

    private boolean asBitmap;//只获取bitmap
    private BitmapListener bitmapListener;

    public SingleConfig(ConfigBuilder builder) {
        this.url = builder.url;
        this.thumbnail = builder.thumbnail;
        this.filePath = builder.filePath;
        this.file = builder.file;
        this.resId = builder.resId;
        this.rawPath = builder.rawPath;
        this.assetspath = builder.assetspath;
        this.contentProvider = builder.contentProvider;

        this.target = builder.target;

        this.width = builder.width;
        this.height = builder.height;

        this.oWidth = builder.oWidth;
        this.oHeight = builder.oHeight;

        this.shapeMode = builder.shapeMode;
        if (shapeMode == ShapeMode.RECT_ROUND) {
            this.rectRoundRadius = builder.rectRoundRadius;
        }
        this.scaleMode = builder.scaleMode;

        this.diskCacheStrategy = builder.diskCacheStrategy;

        this.animationId = builder.animationId;
        this.animationType = builder.animationType;
        this.animation = builder.animation;

        this.priority = builder.priority;
        this.placeHolderResId = builder.placeHolderResId;

        this.asBitmap = builder.asBitmap;
        this.bitmapListener = builder.bitmapListener;
        this.isGif = builder.isGif;
        this.errorResId = builder.errorResId;
    }

    public boolean isAsBitmap() {
        return asBitmap;
    }

    public Context getContext() {
        if (context == null) {
            context = GlobalConfig.context;
        }
        return context;
    }

    public DiskCacheStrategy getDiskCacheStrategy() {
        return diskCacheStrategy;
    }

    public int getErrorResId() {
        return errorResId;
    }

    public String getContentProvider() {
        return contentProvider;
    }

    public String getFilePath() {
        return filePath;
    }

    public File getFile() {
        return file;
    }

    public int getPlaceHolderResId() {
        return placeHolderResId;
    }

    public int getRectRoundRadius() {
        return rectRoundRadius;
    }

    public int getResId() {
        return resId;
    }

    public String getRawPath() {
        return rawPath;
    }

    public String getAssetspath() {
        return assetspath;
    }

    public int getScaleMode() {
        return scaleMode;
    }

    public int getShapeMode() {
        return shapeMode;
    }

    public View getTarget() {
        return target;
    }

    public String getUrl() {
        return url;
    }

    public int getHeight() {
        if (height <= 0) {
            //先去imageview里取,如果为0,则赋值成matchparent
            if (target != null) {
                height = target.getMeasuredHeight();
            }
            if (height <= 0) {
                height = GlobalConfig.getWinHeight();
            }
        }
        return height;
    }

    public int getWidth() {
        if (width <= 0) {
            //先去imageview里取,如果为0,则赋值成matchparent
            if (target != null) {
                width = target.getMeasuredWidth();
            }
            if (width <= 0) {
                width = GlobalConfig.getWinWidth();
            }
        }

        return width;
    }

    public int getoWidth() {
        return oWidth;
    }

    public int getoHeight() {
        return oHeight;
    }

    public int getAnimationType() {
        return animationType;
    }

    public int getAnimationId() {
        return animationId;
    }

    public Animation getAnimation() {
        return animation;
    }

    public int getPriority() {
        return priority;
    }

    public BitmapListener getBitmapListener() {

        return bitmapListener;
    }

    public float getThumbnail() {
        return thumbnail;
    }

    private void show() {
        GlobalConfig.getLoader().request(this);
    }

    public boolean isGif() {
        return isGif;
    }

    public interface BitmapListener {
        void onSuccess(Bitmap bitmap);

        void onFail();
    }

    public static class ConfigBuilder {
        private Context context;

        /**
         * 图片源
         * 类型	SCHEME	示例
         * 远程图片	http://, https://	HttpURLConnection 或者参考 使用其他网络加载方案
         * 本地文件	file://	FileInputStream
         * Content provider	content://	ContentResolver
         * asset目录下的资源	asset://	AssetManager
         * res目录下的资源	  res://	Resources.openRawResource
         * Uri中指定图片数据	data:mime/type;base64,	数据类型必须符合 rfc2397规定 (仅支持 UTF-8)
         */
        private String url;
        private float thumbnail;
        private String filePath;
        private File file;
        private int resId;
        private String rawPath;
        private String assetspath;
        private String contentProvider;
        private boolean isGif = false;

        private View target;
        private boolean asBitmap;//只获取bitmap
        private BitmapListener bitmapListener;

        private int width;
        private int height;

        private int oWidth; //选择加载分辨率的宽
        private int oHeight; //选择加载分辨率的高

        //UI:
        private int placeHolderResId;

        private int errorResId;

        private int shapeMode;//默认矩形,可选直角矩形,圆形/椭圆
        private int rectRoundRadius;//圆角矩形时圆角的半径

        private DiskCacheStrategy diskCacheStrategy;

        private int scaleMode;//填充模式,默认centercrop,可选fitXY,centerInside...

        private int priority; //请求优先级

        public int animationId; //动画资源id
        public int animationType; //动画资源Type
        public Animation animation; //动画资源
        public ConfigBuilder(Context context) {
            this.context = context;
        }

        /**
         * 缩略图
         */
        public ConfigBuilder thumbnail(float thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        /**
         * error图
         */
        public ConfigBuilder error(int errorResId) {
            this.errorResId = errorResId;
            return this;
        }

        /**
         * 设置网络路径
         */
        public ConfigBuilder url(String url) {
            this.url = url;
            if (url.contains("gif")) {
                isGif = true;
            }
            return this;
        }

        /**
         * 加载SD卡资源
         */
        public ConfigBuilder file(String filePath) {
            if (filePath.startsWith("file:")) {
                this.filePath = filePath;
                return this;
            }

            if (!new File(filePath).exists()) {
                return this;
            }

            this.filePath = filePath;
            if (filePath.contains("gif")) {
                isGif = true;
            }
            return this;
        }

        /**
         * 加载SD卡资源
         */
        public ConfigBuilder file(File file) {
            this.file = file;

            return this;
        }

        /**
         * 加载drawable资源
         */
        public ConfigBuilder res(int resId) {
            this.resId = resId;
            return this;
        }

        /**
         * 加载ContentProvider资源
         */
        public ConfigBuilder content(String contentProvider) {
            if (contentProvider.startsWith("content:")) {
                this.contentProvider = contentProvider;
                return this;
            }

            if (contentProvider.contains("gif")) {
                isGif = true;
            }

            return this;
        }

        /**
         * 加载raw资源
         */
        public ConfigBuilder raw(String rawPath) {

            this.rawPath = rawPath;

            if (rawPath.contains("gif")) {
                isGif = true;
            }

            return this;
        }

        /**
         * 加载assets资源
         */
        public ConfigBuilder assets(String assetspath) {
            this.assetspath = assetspath;

            if (assetspath.contains("gif")) {
                isGif = true;
            }

            return this;
        }

        public void into(View targetView) {
            this.target = targetView;
            new SingleConfig(this).show();
        }

        public void asBitmap(BitmapListener bitmapListener) {
            this.bitmapListener = ImageUtil.getBitmapListenerProxy(bitmapListener);
            this.asBitmap = true;
            new SingleConfig(this).show();
        }

        /**
         * 加载图片的分辨率
         */
        public ConfigBuilder override(int oWidth, int oHeight) {
            this.oWidth = ImageUtil.dip2px(oWidth);
            this.oHeight = ImageUtil.dip2px(oHeight);
            return this;
        }

        /**
         * 占位图
         */
        public ConfigBuilder placeHolder(int placeHolderResId) {
            this.placeHolderResId = placeHolderResId;
            return this;
        }


        /**
         * 形状
         * @param shapeMode
         * @param rectRoundRadius ShapeMode.RECT_ROUND生效
         * @return
         */
        public ConfigBuilder shape(int shapeMode,int rectRoundRadius) {
            switch (shapeMode){
                case ShapeMode.RECT:
                    this.shapeMode = ShapeMode.RECT;
                    break;
                case ShapeMode.RECT_ROUND:
                    this.rectRoundRadius = ImageUtil.dip2px(rectRoundRadius);
                    this.shapeMode = ShapeMode.RECT_ROUND;
                    break;
                case ShapeMode.OVAL:
                    this.shapeMode = ShapeMode.OVAL;
                    break;
                case ShapeMode.SQUARE:
                    this.shapeMode = ShapeMode.SQUARE;
                    break;
            }
            return this;
        }

        /**
         * 磁盘缓存
         */
        public ConfigBuilder diskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
            this.diskCacheStrategy = diskCacheStrategy;
            return this;
        }

        /**
         * 拉伸/裁剪模式
         */
        public ConfigBuilder scale(int scaleMode) {
            this.scaleMode = scaleMode;
            return this;
        }


        public ConfigBuilder animate(int animationId) {
            this.animationType = AnimationMode.ANIMATIONID;
            this.animationId = animationId;
            return this;
        }

        public ConfigBuilder animate(Animation animation) {
            this.animationType = AnimationMode.ANIMATION;
            this.animation = animation;
            return this;
        }

        public ConfigBuilder priority(int priority) {
            this.priority = priority;

            return this;
        }
    }
}
