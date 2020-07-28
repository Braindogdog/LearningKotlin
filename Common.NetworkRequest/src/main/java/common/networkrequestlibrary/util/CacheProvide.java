package common.networkrequestlibrary.util;

import android.content.Context;

import okhttp3.Cache;

/**
 * Created by chasen on 2018/3/13.
 */

public class CacheProvide {
    Context mContext;

    public CacheProvide(Context context) {
        mContext = context;
    }

    public Cache provideCache() {
        return new Cache(mContext.getCacheDir(), 50 * 1024 * 1024);
    }
}
