package common.networkrequestlibrary.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.networkrequestlibrary.download.DownInfo;
import common.networkrequestlibrary.download.DownInfoDb;
import common.networkrequestlibrary.interfaces.Error;
import common.networkrequestlibrary.interfaces.Progress;
import common.networkrequestlibrary.interfaces.Success;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static common.networkrequestlibrary.util.HttpBuilder.cancelCallByTag;
import static common.networkrequestlibrary.util.HttpBuilder.checkCallByUrl;
import static common.networkrequestlibrary.util.HttpBuilder.checkHeaders;
import static common.networkrequestlibrary.util.HttpBuilder.checkParams;
import static common.networkrequestlibrary.util.HttpBuilder.putCall;

/**
 * Created by chasen on 2018/3/13.
 */

public class HttpUtil {
    //下载文件请求头
    public static final String DOWNLOAD = "DOWNLOAD";
    public static final String DOWNLOAD_URL = "DOWNLOAD_URL";
    //上传参数
    private Map<String, Object> params;
    //请求头
    private Map<String, String> headers;
    private String url;
    private Error mErrorCallBack;
    private Success mSuccessCallBack;
    private Progress mProgressListener;
    //请求的tag，添加tag方便对请求进行操作
    private Object tag;
    private Context mContext;

    private DownInfoDb downInfoDb;

    private HttpUtil() {
    }

    private HttpUtil(HttpUtil httpUtil) {
        try {
            if (HttpBuilder.getmInstance() == null) {
                throw new NullPointerException("HttpBuilder has not be initialized");
            }
            if (CommonUtil.stringIsEmpty(httpUtil.url))
                throw new NullPointerException("url can't be null");
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(httpUtil.mContext, e.toString(), Toast.LENGTH_SHORT).show();
        }
        this.params = httpUtil.params;
        this.headers = httpUtil.headers;
        this.url = httpUtil.url;
        this.mErrorCallBack = httpUtil.mErrorCallBack;
        this.mProgressListener = httpUtil.mProgressListener;
        this.mSuccessCallBack = httpUtil.mSuccessCallBack;
        this.tag = httpUtil.tag;
        this.mContext = httpUtil.mContext;
        if (this.params == null)
            this.params = new HashMap<>();
        if (this.headers == null)
            this.headers = new HashMap<>();
        if (this.mErrorCallBack == null)
            this.mErrorCallBack = new Error() {
                @Override
                public void Error(String error) {

                }
            };
        if (this.mSuccessCallBack == null)
            this.mSuccessCallBack = new Success() {
                @Override
                public void Success(String result) {

                }
            };
        if (this.mProgressListener == null)
            this.mProgressListener = new Progress() {
                @Override
                public void progress(long readLength, long totallength, String fileName) {

                }
            };
    }

    /**
     * 设置缓存时间
     *
     * @param time
     * @return
     */
    public HttpUtil cacheTime(int time) {
        addHeader("Cache-Time", time + "");
        return this;
    }

    public HttpUtil setTag(@NonNull Object tag) {
        this.tag = tag;
        return this;
    }

    public HttpUtil addParams(@NonNull Map<String, Object> params) {
        if (this.params == null)
            this.params = new HashMap<>();
        this.params.putAll(params);
        return this;
    }

    public HttpUtil addParam(@NonNull String key, String value) {
        if (this.params == null)
            this.params = new HashMap<>();
        this.params.put(key, value);
        return this;
    }

    public HttpUtil addHeaders(@NonNull Map<String, String> headers) {
        if (this.headers == null)
            this.headers = new HashMap<>();
        this.headers.putAll(headers);
        return this;
    }

    public HttpUtil addHeader(@NonNull String key, String value) {
        if (this.headers == null)
            this.headers = new HashMap<>();
        this.headers.put(key, value);
        return this;
    }

    public HttpUtil success(@NonNull Success success) {
        this.mSuccessCallBack = success;
        return this;
    }

    public HttpUtil progress(@NonNull Progress progress) {
        this.mProgressListener = progress;
        return this;
    }

    public HttpUtil error(@NonNull Error error) {
        this.mErrorCallBack = error;
        return this;
    }

    public static class Builder {
        private HttpUtil httpUtil;
        private Map<String, String> params;
        private Map<String, String> headers;
        private String url;
        private Error mErrorCallBack;
        private Success mSuccessCallBack;
        private Progress mProgressListener;
        private Object tag;
        private Context mContext;

        public Builder(Context mContext) {
            httpUtil = new HttpUtil();
            httpUtil.mContext = mContext;
        }

        public Builder cacheTime(int time) {
            httpUtil.addHeader("Cache-Time", time + "");
            return this;
        }

        public Builder url(@NonNull String url) {
            httpUtil.url = url;
            return this;
        }

        public Builder tag(@NonNull Object tag) {
            httpUtil.tag = tag;
            return this;
        }

        public Builder addParams(@NonNull Map<String, Object> params) {
            if (httpUtil.params == null)
                httpUtil.params = new HashMap<>();
            httpUtil.params.putAll(params);
            return this;
        }

        public Builder addParam(@NonNull String key, Object value) {
            if (httpUtil.params == null)
                httpUtil.params = new HashMap<>();
            httpUtil.params.put(key, value);
            return this;
        }

        public Builder addHeaders(@NonNull Map<String, String> headers) {
            if (httpUtil.headers == null)
                httpUtil.headers = new HashMap<>();
            httpUtil.headers.putAll(headers);
            return this;
        }

        public Builder addHeader(@NonNull String key, String value) {
            if (httpUtil.headers == null)
                httpUtil.headers = new HashMap<>();
            httpUtil.headers.put(key, value);
            return this;
        }

        public Builder success(@NonNull Success success) {
            httpUtil.mSuccessCallBack = success;
            return this;
        }

        public Builder progress(@NonNull Progress progress) {
            httpUtil.mProgressListener = progress;
            return this;
        }

        public Builder error(@NonNull Error error) {
            httpUtil.mErrorCallBack = error;
            return this;
        }

        public HttpUtil build() {
            return new HttpUtil(httpUtil);
        }

    }

    @CheckResult
    private String checkUrl(String url) {
        if (HttpBuilder.checkNULL(url)) {
            throw new NullPointerException("absolute url can not be empty");
        }
        return url;
    }

    @CheckResult
    public String checkResultMessage(String message) {
        if (HttpBuilder.checkNULL(message)) {
            message = "服务器异常，请稍后再试";
        }

        if (message.equals("timeout") || message.equals("SSL handshake timed out")) {
            return "网络请求超时";
        } else {
            return message;
        }

    }

    @CheckResult
    public String checkResultMessage(Response<String> response) {
        int code = response.code();
        if (code == 500 || code == 502 || code == 503 || code == 400 || code == 404) {
            return "服务器连接失败:" + code;
        } else {
            return "发生未知错误:" + code;
        }
    }

    @CheckResult
    public String checkResultMessage(Throwable err) {
        String message = "服务器连接失败:101";
        if (err == null) {
            return message;
        }
        if (err instanceof SocketTimeoutException) {
            message = "网络请求超时";
        } else if (HttpBuilder.checkNULL(err.getMessage())) {
            message = "服务器连接失败:102";
        } else {
            message = err.getMessage();
        }
        return message;

    }

    /**
     * 请求前初始检查,结果正常返回true
     */
    boolean checkNetworkState() {
        if (mContext == null) {
            return true;
        }
        if (!NetworkUtil.isConnected(mContext)) {
            Toast.makeText(mContext, "您已断开网络连接", Toast.LENGTH_SHORT).show();
            mErrorCallBack.Error("没有网络连接");
            return false;
        }
        return true;
    }

    /**
     * get请求方法
     */
    public void get() {
        if (!checkNetworkState()) {
            return;
        }
        Call call = HttpBuilder.getService().get(checkUrl(url), checkParams(params), checkHeaders(headers));
        putCall(tag, url, call);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    mSuccessCallBack.Success(response.body());
                } else {
                    mErrorCallBack.Error(checkResultMessage(response));
                }
                if (tag != null)
                    cancelCallByTag(url);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                t.printStackTrace();
                mErrorCallBack.Error(checkResultMessage(t));
                if (tag != null)
                    cancelCallByTag(url);
            }
        });
    }

    /**
     * post请求方法
     */
    public void post() {
        if (!checkNetworkState()) {
            return;
        }
        Call call = HttpBuilder.getService().post(checkUrl(this.url), checkParams(params), checkHeaders(headers));
        putCall(tag, url, call);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    mSuccessCallBack.Success(response.body());
                } else {
                    mErrorCallBack.Error(checkResultMessage(response));
                }
                if (tag != null)
                    cancelCallByTag(url);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                mErrorCallBack.Error(checkResultMessage(t));
                if (tag != null)
                    cancelCallByTag(url);
            }
        });
    }

    public void postWithJson(String json) {
        if (!checkNetworkState()) {
            return;
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), json);
        Call call = HttpBuilder.getService().postWithJson(checkUrl(this.url), body);
        putCall(tag, url, call);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    mSuccessCallBack.Success(response.body());
                } else if(response.code() == 401) {
                    try {
                        mErrorCallBack.Error(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    mErrorCallBack.Error(checkResultMessage(response));
                }
                if (tag != null)
                    cancelCallByTag(url);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                mErrorCallBack.Error(checkResultMessage(t));
                if (tag != null)
                    cancelCallByTag(url);
            }
        });
    }

    public void putWithJson(String json) {
        if (!checkNetworkState()) {
            return;
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), json);
        Call call = HttpBuilder.getService().putWithJson(checkUrl(this.url), body);
        putCall(tag, url, call);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    mSuccessCallBack.Success(response.body());
                } else if(response.code() == 401) {
                    try {
                        mErrorCallBack.Error(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    mErrorCallBack.Error(checkResultMessage(response));
                }
                if (tag != null)
                    cancelCallByTag(url);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                mErrorCallBack.Error(checkResultMessage(t));
                if (tag != null)
                    cancelCallByTag(url);
            }
        });
    }

    /**
     * 上传文件方法
     *
     * @param listFile
     */
    public void uploadFile(List<File> listFile, String timestamp) {
        if (!checkNetworkState()) {
            return;
        }
        HashMap<String, RequestBody> map = new HashMap<>();
        for (int i = 0; i < listFile.size(); i++) {
            File file = listFile.get(i);
            RequestBody requestBody =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);
            RequestBodyProgress requestBodyProgress = new RequestBodyProgress(requestBody, mProgressListener, file.getName());
            map.put(timestamp + i + "\"; filename=\"" + file.getName(), requestBodyProgress);
        }
        Call<String> call = HttpBuilder.getService().uploadFile(checkUrl(url), checkParams(params), map, checkHeaders(headers));
        putCall(tag, url, call);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    mSuccessCallBack.Success(response.body());
                } else {
                    mErrorCallBack.Error(checkResultMessage(response.body()));
                }
                if (tag != null)
                    cancelCallByTag(url);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                mErrorCallBack.Error(checkResultMessage(t));
                if (tag != null)
                    cancelCallByTag(url);
            }
        });
    }

    public void uploadFile(List<File> listFile) {
        if (!checkNetworkState()) {
            return;
        }
        HashMap<String, RequestBody> map = new HashMap<>();
        for (int i = 0; i < listFile.size(); i++) {
            File file = listFile.get(i);
//            RequestBody requestBody =
//                    RequestBody.create(MediaType.parse("multipart/form-data"), file);
//            RequestBodyProgress requestBodyProgress = new RequestBodyProgress(requestBody, mProgressListener, file.getName());
//            map.put(timestamp + i + "\"; filename=\"" + file.getName(), requestBodyProgress);
            RequestBody requestBody =
                    RequestBody.create(MediaType.parse("image/jpg"), file);
            RequestBodyProgress requestBodyProgress = new RequestBodyProgress(requestBody, mProgressListener, file.getName());
            map.put("file\"; filename=\"" + file.getName(), requestBodyProgress);
        }
        Call<String> call = HttpBuilder.getService().uploadFile(checkUrl(url), checkParams(params), map, checkHeaders(headers));
        putCall(tag, url, call);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    mSuccessCallBack.Success(response.body());
                } else {
                    mErrorCallBack.Error(checkResultMessage(response.body()));
                }
                if (tag != null)
                    cancelCallByTag(url);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                mErrorCallBack.Error(checkResultMessage(t));
                if (tag != null)
                    cancelCallByTag(url);
            }
        });
    }

    public void postWithText(String json) {
        if (!checkNetworkState()) {
            return;
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/text;charset=UTF-8"), json);
        Call call = HttpBuilder.getService().postWithText(checkUrl(this.url), body);
        putCall(tag, url, call);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    mSuccessCallBack.Success(response.body());
                } else {
                    mErrorCallBack.Error(checkResultMessage(response));
                }
                if (tag != null)
                    cancelCallByTag(url);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                mErrorCallBack.Error(checkResultMessage(t));
                if (tag != null)
                    cancelCallByTag(url);
            }
        });
    }

    /**
     * 下载文件方法
     */
    public void download(final DownInfo downInfo) {
        if (!checkNetworkState() || checkCallByUrl(url)) {
            return;
        }
        if (CommonUtil.stringIsEmpty(downInfo.getUrl())) {
            downInfo.setUrl(url);
        }
        this.headers.put(DOWNLOAD, DOWNLOAD);
        this.headers.put(DOWNLOAD_URL, url);
        final Call<ResponseBody> call = HttpBuilder.getService().download(checkHeaders(headers), checkUrl(url), checkParams(params));
        putCall(tag, url, call);
        Observable.create(new ObservableOnSubscribe<ResponseBody>() {
            @Override
            public void subscribe(final ObservableEmitter<ResponseBody> emitter) throws Exception {
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        emitter.onNext(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        mErrorCallBack.Error(checkResultMessage(t));
                        if (tag != null)
                            cancelCallByTag(url);
                    }
                });
            }
        })
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<ResponseBody>() {
                               @Override
                               public void accept(ResponseBody responseBody) throws Exception {
                                   if (CommonUtil.stringIsEmpty(downInfo.getSavePath())) {
                                       downInfo.setSavePath(CommonUtil.getFilePathNomal(mContext, responseBody));
                                   }
                                   WriteFileUtil.writeFile(responseBody, mProgressListener,
                                           mSuccessCallBack, mErrorCallBack, downInfo);
                               }

                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(final Throwable throwable) throws Exception {
                                   Handler handler = new Handler(Looper.getMainLooper());
                                   handler.post(new Runnable() {
                                       @Override
                                       public void run() {
                                           mErrorCallBack.Error(checkResultMessage(throwable.getMessage()));
                                       }
                                   });
                                   if (tag != null)
                                       cancelCallByTag(url);
                               }
                           }
                );
    }

    /**
     * 支持断点续传下载
     *
     * @param downInfo
     */
    public void downloadWithPause(final DownInfo downInfo) {
        if (!checkNetworkState() || checkCallByUrl(url)) {
            return;
        }
        if (downInfoDb == null)
            downInfoDb = new DownInfoDb(mContext);
        DownInfo info = downInfoDb.getDownInfo(url);
        if (info == null) {
            downInfo.setUrl(url);
        } else {
            downInfo.setUrl(info.getUrl());
            if (fileIsExists(info.getSavePath())) {
                downInfo.setSavePath(info.getSavePath());
                downInfo.setReadLength(info.getReadLength());
                downInfo.setCountLength(info.getCountLength());
            }
        }
        this.headers.put(DOWNLOAD, DOWNLOAD);
        this.headers.put(DOWNLOAD_URL, url);
        if (this.tag == null) {
            this.tag = "downloadWithPause";
        }
        final Call<ResponseBody> call = HttpBuilder.getService().downloadWithPause(checkHeaders(headers), "bytes=" + downInfo.getReadLength() + "-", checkUrl(url));
        putCall(tag, url, call);
        Observable<ResponseBody> observable = Observable.create(new ObservableOnSubscribe<ResponseBody>() {

            @Override
            public void subscribe(final ObservableEmitter<ResponseBody> emitter) throws Exception {
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        emitter.onNext(response.body());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        mErrorCallBack.Error(checkResultMessage(t));
                        if (tag != null)
                            cancelCallByTag(url);
                    }
                });
            }
        });
        observable.observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new io.reactivex.Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        if (CommonUtil.stringIsEmpty(downInfo.getSavePath())) {
                            downInfo.setSavePath(CommonUtil.getFilePathNomal(mContext, responseBody));
                        }
                        if (downInfoDb == null) {
                            downInfoDb = new DownInfoDb(mContext);
                        }
                        WriteFileUtil.writeFileCanStop(responseBody, mProgressListener,
                                mSuccessCallBack, mErrorCallBack, downInfo, downInfoDb);
                    }

                    @Override
                    public void onError(final Throwable e) {
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                mErrorCallBack.Error(checkResultMessage(e));
                            }
                        });
                        if (tag != null)
                            cancelCallByTag(url);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 暂停下载
     */
    public void pauseDownLoadByUrl() {
        cancelCallByTag(url);
    }

    //判断文件是否存在
    public boolean fileIsExists(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
