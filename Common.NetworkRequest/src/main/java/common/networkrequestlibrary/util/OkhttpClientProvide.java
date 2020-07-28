package common.networkrequestlibrary.util;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import common.networkrequestlibrary.BuildConfig;
import common.networkrequestlibrary.interceptor.CacheInterceptor;
import common.networkrequestlibrary.interceptor.DownLoadInterceptor;
import common.networkrequestlibrary.interceptor.HttpLoggingInterceptor;
import okhttp3.OkHttpClient;

/**
 * Created by chasen on 2018/3/13.
 */

public class OkhttpClientProvide {
    static OkHttpClient okHttpClient;

    public static OkHttpClient okHttpClient(final Context context, String BASE_URL) {
        if (okHttpClient == null) {
            synchronized (OkhttpClientProvide.class) {
                if (okHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    //信任所有证书
                    HTTPSCerUtils.setTrustAllCertificate(builder);
                    OkHttpClient client = builder
//                            .addInterceptor(new DownLoadInterceptor(BASE_URL))
//                            .addNetworkInterceptor(new CacheInterceptor())
//                            .cache(new CacheProvide(context).provideCache())
                            .retryOnConnectionFailure(true)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .readTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(30, TimeUnit.SECONDS)
                            .build();
                    if (BuildConfig.DEBUG) {//printf logs while  debug
                        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                        client = client.newBuilder().addInterceptor(logging).build();
                    }
                    okHttpClient = client;
                }

            }

        }



        return okHttpClient;

    }
}
