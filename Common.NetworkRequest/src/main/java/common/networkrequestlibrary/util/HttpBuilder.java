package common.networkrequestlibrary.util;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import common.networkrequestlibrary.converter.StringConverterFactory;
import common.networkrequestlibrary.interceptor.HeadersInterceptor;
import common.networkrequestlibrary.interceptor.ParamsInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by chasen on 2018/3/13.
 */

public class HttpBuilder {
    private Context mContext;
    private static volatile HttpBuilder mInstance;
    private static volatile RetrofitHttpService mService;
    //请求参数拦截器，可以添加参数拦截器添加全局通用参数
    private ParamsInterceptor mParamsInterceptor;
    //请求头拦截器，可以添加请求头拦截器添加全局通用参数
    private HeadersInterceptor mHeadersInterceptor;

    //构造函数私有，不允许外部调用
    private HttpBuilder(RetrofitHttpService mService, Context mContext, ParamsInterceptor mParamsInterceptor, HeadersInterceptor mHeadersInterceptor) {
        this.mService = mService;
        this.mContext = mContext;
        this.mParamsInterceptor = mParamsInterceptor;
        this.mHeadersInterceptor = mHeadersInterceptor;
    }

    public static SingleBuilder init(Context context) {
        return new SingleBuilder(context);
    }

    public static RetrofitHttpService getService() {
        if (mInstance == null) {
            throw new NullPointerException("HttpBuilder has not be initialized");
        }
        return mService;
    }

    public static HttpBuilder getmInstance() {
        if (mInstance == null) {
            throw new NullPointerException("HttpBuilder has not be initialized");
        }
        return mInstance;
    }

    public static class SingleBuilder {
        private Context mContext;
        private ParamsInterceptor paramsInterceptor;
        private HeadersInterceptor headersInterceptor;
        private List<Converter.Factory> converterFactories = new ArrayList<>();
        private List<CallAdapter.Factory> adapterFactories = new ArrayList<>();
        private OkHttpClient client;

        public SingleBuilder(Context mContext) {
            mContext = mContext.getApplicationContext();
            //这里的url并无实际意义
                client = OkhttpClientProvide.okHttpClient(mContext, "https://www.baidu.com/");
        }

        public SingleBuilder client(OkHttpClient client) {
            this.client = client;
            return this;
        }


        /**
         * 添加请求头拦截器
         *
         * @param interceptor
         * @return
         */
        public SingleBuilder headersInterceptor(HeadersInterceptor interceptor) {
            this.headersInterceptor = interceptor;
            return this;
        }

        /**
         * 添加参数拦截器
         *
         * @param interceptor
         * @return
         */
        public SingleBuilder paramsInterceptor(ParamsInterceptor interceptor) {
            this.paramsInterceptor = interceptor;
            return this;
        }

        /**
         * 添加转换器
         *
         * @param factory
         * @return
         */
        public SingleBuilder converterFactory(Converter.Factory factory) {
            this.converterFactories.add(factory);
            return this;
        }

        /**
         * 添加请求适配器
         *
         * @param factory
         * @return
         */
        public SingleBuilder callFactory(CallAdapter.Factory factory) {
            this.adapterFactories.add(factory);
            return this;
        }

        public HttpBuilder build() {
            if (converterFactories.size() == 0) {
                converterFactories.add(StringConverterFactory.create());
            }
            if (adapterFactories.size() == 0) {
                adapterFactories.add(RxJava2CallAdapterFactory.create());
            }

            Retrofit.Builder builder = new Retrofit.Builder();

            for (Converter.Factory converterFactory : converterFactories) {
                builder.addConverterFactory(converterFactory);
            }
            for (CallAdapter.Factory adapterFactory : adapterFactories) {
                builder.addCallAdapterFactory(adapterFactory);
            }
            Retrofit retrofit = builder
                    //这里的url同样无实际意义
                    .baseUrl("https://www.baidu.com/")
                    .client(client).build();
            RetrofitHttpService retrofitHttpService =
                    retrofit.create(RetrofitHttpService.class);

            mInstance = new HttpBuilder(retrofitHttpService, mContext, paramsInterceptor, headersInterceptor);
            return mInstance;
        }
    }

    /**
     * 每次网络请求时进行参数检查，添加通用参数
     *
     * @param params
     * @return
     */
    public static Map<String, Object> checkParams(Map<String, Object> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        if (mInstance.mParamsInterceptor != null) {
            params = mInstance.mParamsInterceptor.checkParams(params);
        }
        //retrofit的params的值不能为null，此处做下校验，防止出错
        Iterator<Map.Entry<String, Object>> it = params.entrySet().iterator();

        while (it.hasNext()){
            Map.Entry<String, Object> entry = it.next();
            if (entry.getValue() == null) {
                it.remove();
            }
        }

//        for (Map.Entry<String, Object> entry : params.entrySet()) {
////            if (entry.getValue() == null) {
////                params.put(entry.getKey(), "");
////            }
//            //做一个变化，如果是null，则不需要上传
//            params.remove(entry.getKey());
//        }
        return params;
    }

    /**
     * 每次请求时进行请求头检查，添加通用请求头
     *
     * @param headers
     * @return
     */
    public static Map<String, String> checkHeaders(Map<String, String> headers) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        if (mInstance.mHeadersInterceptor != null) {
            headers = mInstance.mHeadersInterceptor.checkHeaders(headers);
        }
        //retrofit的params的值不能为null，此处做下校验，防止出错
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            if (entry.getValue() == null) {
                headers.put(entry.getKey(), "");
            }
        }
        return headers;
    }

    public static boolean checkNULL(String str) {
        return str == null || "null".equals(str.trim()) || "".equals(str.trim());

    }


    public static Map<String, Call> CALL_MAP = new HashMap<>();

    public static synchronized void putCall(Object tag, String url, Call call) {
        if (tag == null)
            return;
        synchronized (CALL_MAP) {
            CALL_MAP.put(tag.toString() + url, call);
        }
    }

    public static synchronized void cancelCallByTag(Object tag) {
        if (tag == null)
            return;
        List<String> list = new ArrayList<>();
        synchronized (CALL_MAP) {
            for (String key : CALL_MAP.keySet()) {
                if (key.contains(tag.toString())) {
                    CALL_MAP.get(key).cancel();
                    list.add(key);
                }
            }
        }
        for (String s : list) {
            removeCallByUrl(s);
        }
    }

    public static synchronized boolean checkCallByUrl(String url) {
        if (url == null)
            return true;
        synchronized (CALL_MAP) {
            for (String key : CALL_MAP.keySet()) {
                if (key.contains(url)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static synchronized void cancelAllCall() {
        List<String> list = new ArrayList<>();
        synchronized (CALL_MAP) {
            for (String key : CALL_MAP.keySet()) {
                CALL_MAP.get(key).cancel();
                list.add(key);
            }
        }
        for (String s : list) {
            removeCallByUrl(s);
        }
    }

    public static synchronized void removeCallByUrl(String url) {
        if (CommonUtil.stringIsEmpty(url))
            return;
        synchronized (CALL_MAP) {
            for (String key : CALL_MAP.keySet()) {
                if (key.contains(url)) {
                    url = key;
                    break;
                }
            }
            CALL_MAP.remove(url);
        }
    }

}
