package common.mediaselectlibrary.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import common.baselibrary.baseview.CommonProgressDialog;

public class CompressPhotoUtils {
    private List<String> fileList = new ArrayList<>();
    private CommonProgressDialog progressDialog;

    public void CompressPhoto(Context context, List<String> list, CompressCallBack callBack) {
        CompressTask task = new CompressTask(context, list, callBack);
        task.execute();
    }

    class CompressTask extends AsyncTask<Void, Integer, Integer> {
        private Context context;
        private List<String> list;
        private CompressCallBack callBack;

        CompressTask(Context context, List<String> list, CompressCallBack callBack) {
            this.context = context;
            this.list = list;
            this.callBack = callBack;
        }

        /**
         * 运行在UI线程中，在调用doInBackground()之前执行
         */
        @Override
        protected void onPreExecute() {
            progressDialog = new CommonProgressDialog(context);
            progressDialog.show();
        }

        /**
         * 后台运行的方法，可以运行非UI线程，可以执行耗时的方法
         */
        @Override
        protected Integer doInBackground(Void... params) {
            for (String oldPath : list) {
                try {
                    File compressFile = new Compressor(context)
                            .setMaxWidth(640)
                            .setMaxHeight(480)
                            .setQuality(75)
                            .setCompressFormat(Bitmap.CompressFormat.JPEG)
                            .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                    Environment.DIRECTORY_PICTURES).getAbsolutePath())
                            .compressToFile(new File(oldPath));
                    fileList.add(compressFile.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        /**
         * 运行在ui线程中，在doInBackground()执行完毕后执行
         */
        @Override
        protected void onPostExecute(Integer integer) {
            progressDialog.dismiss();
            callBack.success(fileList);
        }

        /**
         * 在publishProgress()被调用以后执行，publishProgress()用于更新进度
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
        }
    }


    public interface CompressCallBack {
        void success(List<String> list);
    }
}
