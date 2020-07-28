package common.networkrequestlibrary.util;

import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import common.networkrequestlibrary.download.DownInfo;
import common.networkrequestlibrary.download.DownInfoDb;
import common.networkrequestlibrary.interfaces.Progress;
import common.networkrequestlibrary.interfaces.Success;
import common.networkrequestlibrary.interfaces.Error;
import okhttp3.ResponseBody;

/**
 * Created by chasen on 2018/3/13.
 */

public class WriteFileUtil {
    public static Handler mHandler = new Handler(Looper.getMainLooper());

    public static void writeFile(ResponseBody body, final Progress progress, final Success mSuccessCallBack, final Error mErrorCallBack, final DownInfo downInfo) {
        try {
            File futureStudioIconFile = new File(downInfo.getSavePath());
            final String fileName = futureStudioIconFile.getName();
            InputStream inputStream = null;
            OutputStream outputStream = null;
            final ProgressInfo progressInfo = new ProgressInfo();
            try {
                byte[] fileReader = new byte[4096];
                progressInfo.total = body.contentLength();
                progressInfo.read = 0;
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    progressInfo.read += read;
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progress.progress(progressInfo.read, progressInfo.total, fileName);
                        }
                    });
                }
                outputStream.flush();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mSuccessCallBack.Success(downInfo.getSavePath());
                    }
                });
                HttpBuilder.cancelCallByTag(downInfo.getUrl());
            } catch (final IOException e) {
                HttpBuilder.cancelCallByTag(downInfo.getUrl());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mErrorCallBack.Error(e.getMessage());
                    }
                });
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (IOException e) {
                    HttpBuilder.cancelCallByTag(downInfo.getUrl());
                }

            }
        } catch (final Exception e) {
            HttpBuilder.cancelCallByTag(downInfo.getUrl());
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mErrorCallBack.Error(e.getMessage());
                }
            });
        }
    }

    public static void writeFileCanStop(ResponseBody body, final Progress progress, final Success mSuccessCallBack, final Error mErrorCallBack, final DownInfo downInfo, DownInfoDb downInfoDb) {
        try {
            RandomAccessFile randomAccessFile = null;
            FileChannel channelOut = null;
            InputStream inputStream = null;
            final ProgressInfo progressInfo = new ProgressInfo();
            try {
                File file = new File(downInfo.getSavePath());
                if (!file.getParentFile().exists())
                    file.getParentFile().mkdirs();
                final String fileName = file.getName();
                long allLength = 0 == downInfo.getReadLength() ? body.contentLength() : downInfo.getReadLength() + body
                        .contentLength();
                downInfo.setCountLength(allLength);
                downInfoDb.addDownInfo(downInfo);
                progressInfo.total = allLength;
                progressInfo.read = downInfo.getReadLength();
                inputStream = body.byteStream();
                randomAccessFile = new RandomAccessFile(file, "rwd");
                channelOut = randomAccessFile.getChannel();
                if ((allLength - progressInfo.read) < 10 * 1024 * 1024) {
                    MappedByteBuffer mappedBuffer = channelOut.map(FileChannel.MapMode.READ_WRITE,
                            progressInfo.read, allLength - progressInfo.read);
                    byte[] buffer = new byte[1024 * 4];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1) {
                        progressInfo.read += len;
                        mappedBuffer.put(buffer, 0, len);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                progress.progress(progressInfo.read, progressInfo.total, fileName);
                            }
                        });
                    }
                } else {//防止OOM，数据过大时分割MappedByteBuffer
                    MappedByteBuffer mappedBuffer = channelOut.map(FileChannel.MapMode.READ_WRITE,
                            progressInfo.read, 10 * 1024 * 1024);
                    byte[] buffer = new byte[1024 * 4];
                    int len;
                    long lengthTemp = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        lengthTemp += len;
                        if (lengthTemp > 10 * 1024 * 1024) {
                            if (progressInfo.read + 10 * 1024 * 1024 <= allLength) {
                                mappedBuffer = channelOut.map(FileChannel.MapMode.READ_WRITE,
                                        progressInfo.read, 10 * 1024 * 1024);
                            } else {
                                mappedBuffer = channelOut.map(FileChannel.MapMode.READ_WRITE,
                                        progressInfo.read, allLength - progressInfo.read);
                            }
                            lengthTemp = len;
                        }
                        progressInfo.read += len;
                        mappedBuffer.put(buffer, 0, len);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                progress.progress(progressInfo.read, progressInfo.total, fileName);
                            }
                        });
                    }
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mSuccessCallBack.Success(downInfo.getSavePath());
                    }
                });
                downInfoDb.deleteDownInfo(downInfo.getUrl());
                HttpBuilder.cancelCallByTag(downInfo.getUrl());
            } catch (final IOException e) {
                //网络连接错误在这里保存下载信息并清除下载中的线程
                downInfo.setReadLength(progressInfo.read);
                downInfoDb.addDownInfo(downInfo);
                HttpBuilder.cancelCallByTag(downInfo.getUrl());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mErrorCallBack.Error(e.getMessage());
                    }
                });
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (channelOut != null) {
                        channelOut.close();
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                } catch (IOException e) {
                    HttpBuilder.cancelCallByTag(downInfo.getUrl());
                }
            }
        } catch (final Exception e) {
            HttpBuilder.cancelCallByTag(downInfo.getUrl());
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mErrorCallBack.Error(e.getMessage());
                }
            });
        }

    }

    static class ProgressInfo {
        public long read = 0;
        public long total = 0;
    }
}
