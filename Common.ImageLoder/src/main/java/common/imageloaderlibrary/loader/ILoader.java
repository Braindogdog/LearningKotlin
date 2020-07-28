package common.imageloaderlibrary.loader;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.MemoryCategory;

import common.imageloaderlibrary.config.SingleConfig;
import common.imageloaderlibrary.util.DownLoadImageService;

/**
 * Created by chasen on 2018/3/12.
 * 图片加载实现的接口
 */

public interface ILoader {
    //初始化
    void init(Context context, int cacheSizeInM, MemoryCategory memoryCategory, boolean isCacheInternal);
    //加载图片的最终实现
    void request(SingleConfig config);

    void pause();

    void resume();
    //清除磁盘缓存（耗时操作）
    void clearDiskCache();
    //清除指定view缓存
    void clearMomoryCache(View view);
    //清除内存缓存，必须在UI线程调用
    void clearMomory();
    //判断是否缓存过
    boolean isCached(String url);

    void trimMemory(int level);
    //清除所有内存缓存
    void clearAllMemoryCaches();
    //保存图片
    void saveImageIntoGallery(DownLoadImageService downLoadImageService);
}
