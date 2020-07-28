package common.networkrequestlibrary.util;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;

import common.networkrequestlibrary.interfaces.Progress;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by chasen on 2018/3/14.
 */

public class RequestBodyProgress extends RequestBody {
    //实际请求体
    private RequestBody mRequestBody;
    //上传进度回调接口
    private Progress mProgress;
    //上传文件名
    private String fileName;
    //包装过的BufferedSink
    private BufferedSink bufferedSink;

    public static Handler mHandler = new Handler(Looper.getMainLooper());

    public RequestBodyProgress(RequestBody mRequestBody, Progress mProgress, String fileName) {
        this.mRequestBody = mRequestBody;
        this.mProgress = mProgress;
        this.fileName = fileName;
    }

    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return mRequestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        bufferedSink = Okio.buffer(sink(sink));
        mRequestBody.writeTo(bufferedSink);
        //必须调用flush，否则最后一部分数据可能不会被写入
        bufferedSink.flush();
    }

    /**
     * 写入上传进度进度接口
     */
    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {
            //当前写入字节数
            long bytesWritten = 0L;
            //总字节长度，避免多次调用contentLength()方法
            long contentLength = 0L;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength == 0) {
                    contentLength = contentLength();
                }
                bytesWritten += byteCount;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mProgress.progress(bytesWritten, contentLength, fileName);
                    }
                });
            }
        };
    }
}
