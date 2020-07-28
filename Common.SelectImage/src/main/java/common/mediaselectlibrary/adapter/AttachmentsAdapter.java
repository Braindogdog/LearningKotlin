package common.mediaselectlibrary.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.krx.ydzh.commoncore.config.ConfigManager;
import com.krx.ydzh.commoncore.constants.Constants;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import common.baselibrary.baseutil.AppUtil;
import common.mediaselectlibrary.R;
import common.mediaselectlibrary.model.Attachment;
import common.mediaselectlibrary.model.ImageMessage;
import common.mediaselectlibrary.view.ShowImageActivity;

/**
 * Created by MHshachang on 2017/3/19.
 * 附件
 */

public class AttachmentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Attachment> infos;
    private File dir;
    private List<BaseDownloadTask> tasks;

    public AttachmentsAdapter(Context context, String alarmId, ArrayList<Attachment> infos) {
        this.context = context;
        this.infos = infos;
        if (TextUtils.isEmpty(alarmId)) {
            alarmId = "未知信息";
        }
        dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator + AppUtil.getAppInfo(context).packageName + "/File", alarmId);
        tasks = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.show_item_attachments, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder viewHolder = (MyViewHolder) holder;
        final Attachment attachInfo = infos.get(position);
        if (attachInfo.getUrl() == null) {
            Log.e("tagger", "attachInfo.getHttp_Url() = " + attachInfo.getUrl());
        }

        String fileName = getFileName(attachInfo.getName());
        viewHolder.vLogo.setImageResource(getFileType(attachInfo.getExtensionName()));
        //服务端端午名字属性，手机默认赋值
        viewHolder.vName.setText(attachInfo.getName());
        final File file = new File(dir, attachInfo.getName());
        if (file.exists()) {
            viewHolder.tvDownLoad.setVisibility(View.GONE);
        } else {
            viewHolder.tvDownLoad.setVisibility(View.VISIBLE);
        }

        if (isImageType(attachInfo.getExtensionName())) {
            viewHolder.vFile.setVisibility(View.GONE);
            viewHolder.vImage.setVisibility(View.VISIBLE);
            viewHolder.tvDownLoad.setVisibility(View.GONE);
            Glide.with(context)
                    .load(ConfigManager.getInstance().getBaseConfig(Constants.IMAGE_RESOURCE) +attachInfo.getId())
                    .into(viewHolder.vImage);
        } else {
            viewHolder.vFile.setVisibility(View.VISIBLE);
            viewHolder.vImage.setVisibility(View.GONE);
        }

        //最终名称
        final String completePath = file.getAbsolutePath();
        viewHolder.tvDownLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.vDownLoad.setVisibility(View.VISIBLE);
                BaseDownloadTask task = createDownloadTask(viewHolder, ConfigManager.getInstance().getBaseConfig(Constants.IMAGE_RESOURCE) +attachInfo.getId(), completePath);
                tasks.add(task);
                task.start();
            }
        });

        viewHolder.vFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.tvDownLoad.getVisibility() == View.GONE) {
                    try {
                        openFile(file, context);
                    } catch (Exception e) {
                        Toast.makeText(context, "无法打开此类型的文件", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        viewHolder.vImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //展现大图
                ShowImageActivity.startActivity((Activity) context, getImageList(), getCurImageIndex(attachInfo));
            }
        });
    }

    private int getCurImageIndex(Attachment attachInfo) {
        int i = -1;

        for (Attachment en : infos) {
            if (isImageType(en.getExtensionName())) {
                i++;
                if (en.getId().equals(attachInfo.getId())) {
                    break;
                }
            }
        }

        return i == -1 ? 0:i;
    }

    private ArrayList<ImageMessage> getImageList() {
        ArrayList<ImageMessage> imageMessagesList = new ArrayList<>();
        for (Attachment en : infos) {
            if (isImageType(en.getExtensionName())) {
                ImageMessage imageMessage = new ImageMessage();
                imageMessage.setId(en.getId());
                imageMessage.setPath(ConfigManager.getInstance().getBaseConfig(Constants.IMAGE_RESOURCE) +en.getId());
                imageMessage.setType(ImageMessage.URL);

                imageMessagesList.add(imageMessage);
            }
        }
        return imageMessagesList;
    }

    @Override
    public int getItemCount() {
        return infos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView vLogo;
        ImageView vImage;
        TextView vName;
        View vDownLoad;
        TextView tvDownLoad;
        ProgressBar vProgressBar;
        View vFile;

        public MyViewHolder(View itemView) {
            super(itemView);
            vLogo = (ImageView) itemView.findViewById(R.id.vLogo);
            vName = (TextView) itemView.findViewById(R.id.vName);
            vDownLoad = itemView.findViewById(R.id.vDownLoad);
            tvDownLoad = (TextView) itemView.findViewById(R.id.tvDownLoad);
            vProgressBar = (ProgressBar) itemView.findViewById(R.id.vProgressBar);
            vFile = itemView.findViewById(R.id.vFile);
            vImage = itemView.findViewById(R.id.vImage);
        }

        public void updateError(BaseDownloadTask task) {
            vDownLoad.setVisibility(View.GONE);
            tvDownLoad.setText("继续下载");
            task.pause();
            if (tasks.contains(task)) {
                tasks.remove(task);
            }
        }

        public void updateConnected(int totalBytes) {
            vProgressBar.setMax(totalBytes);
        }

        public void updateCompleted() {
            vDownLoad.setVisibility(View.GONE);
            tvDownLoad.setVisibility(View.GONE);
        }

        public void updateProgress(int soFarBytes) {
            vProgressBar.setProgress(soFarBytes);
        }
    }

    private String getFileName(String url) {
        if (!TextUtils.isEmpty(url)) {
            try {
                return url.substring(url.lastIndexOf("/") + 1);
            } catch (Exception e) {
            }
        }
        return "未知文件";
    }

    private BaseDownloadTask createDownloadTask(MyViewHolder viewHolder, String url, final String completePath) {
        MyViewHolder tag;
        boolean isDir = false;
        tag = viewHolder;

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return FileDownloader.getImpl().create(url)
                .setPath(completePath, isDir)
                .setCallbackProgressTimes(300)
                .setMinIntervalUpdateSpeed(400)
                .setTag(tag)
                .setListener(new FileDownloadSampleListener() {

                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        super.pending(task, soFarBytes, totalBytes);
//                        ((MyViewHolder) task.getTag()).updatePending(task);
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        super.progress(task, soFarBytes, totalBytes);
                        ((MyViewHolder) task.getTag()).updateProgress(soFarBytes);
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        super.error(task, e);
                        ((MyViewHolder) task.getTag()).updateError(task);
                    }

                    @Override
                    protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
                        super.connected(task, etag, isContinue, soFarBytes, totalBytes);
                        ((MyViewHolder) task.getTag()).updateConnected(totalBytes);

                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        super.paused(task, soFarBytes, totalBytes);
//                        ((MyViewHolder) task.getTag()).updatePaused(task.getSpeed());
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        super.completed(task);
                        ((MyViewHolder) task.getTag()).updateCompleted();

                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        super.warn(task);
//                        ((MyViewHolder) task.getTag()).updateWarn();
                    }
                });
    }


    private void openFile(File file, Context context) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);  //设置intent的Action属性
        String type = getMIMEType(file);  //获取文件file的MIME类型
        Uri fileUri = null;
        if (Build.VERSION.SDK_INT < 24) {
            //安卓7.0特性
            fileUri = Uri.fromFile(file);
        } else {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            fileUri = FileProvider.getUriForFile(context, AppUtil.getAppInfo(context).packageName + ".fileprovider", file);
        }
        intent.setDataAndType(fileUri, type);   //设置intent的data和Type属性。
        try {
            context.startActivity(intent);     //比如说你的MIME类型是打开邮箱，但是你手机里面没装邮箱客户端，就会报错。
        } catch (Exception e) {
            Toast.makeText(context, "无法打开此类型的文件", Toast.LENGTH_SHORT).show();
        }

    }

    private String getMIMEType(File file) {

        String type = "*/*";
        String fName = file.getName();
        int dotIndex = fName.lastIndexOf(".");  //获取后缀名前的分隔符"."在fName中的位置。
        if (dotIndex < 0) {
            return type;
        }

        String end = fName.substring(dotIndex, fName.length()).toLowerCase();  /* 获取文件的后缀名*/
        if (TextUtils.isEmpty(end)) return type;
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < MIME_MapTable.length; i++) { //MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
            if (end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }

    private final String[][] MIME_MapTable = {
            //{后缀名，MIME类型}
            {".3gp", "video/3gpp"},
            {".apk", "application/vnd.android.package-archive"},
            {".asf", "video/x-ms-asf"},
            {".avi", "video/x-msvideo"},
            {".bin", "application/octet-stream"},
            {".bmp", "image/bmp"},
            {".c", "text/plain"},
            {".class", "application/octet-stream"},
            {".conf", "text/plain"},
            {".cpp", "text/plain"},
            {".doc", "application/msword"},
            {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls", "application/vnd.ms-excel"},
            {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe", "application/octet-stream"},
            {".gif", "image/gif"},
            {".gtar", "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h", "text/plain"},
            {".htm", "text/html"},
            {".html", "text/html"},
            {".jar", "application/java-archive"},
            {".java", "text/plain"},
            {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log", "text/plain"},
            {".m3u", "audio/x-mpegurl"},
            {".m4a", "audio/mp4a-latm"},
            {".m4b", "audio/mp4a-latm"},
            {".m4p", "audio/mp4a-latm"},
            {".m4u", "video/vnd.mpegurl"},
            {".m4v", "video/x-m4v"},
            {".mov", "video/quicktime"},
            {".mp2", "audio/x-mpeg"},
            {".mp3", "audio/x-mpeg"},
            {".mp4", "video/mp4"},
            {".mpc", "application/vnd.mpohun.certificate"},
            {".mpe", "video/mpeg"},
            {".mpeg", "video/mpeg"},
            {".mpg", "video/mpeg"},
            {".mpg4", "video/mp4"},
            {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"},
            {".ogg", "audio/ogg"},
            {".pdf", "application/pdf"},
            {".png", "image/png"},
            {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop", "text/plain"},
            {".rc", "text/plain"},
            {".rmvb", "audio/x-pn-realaudio"},
            {".rtf", "application/rtf"},
            {".sh", "text/plain"},
            {".tar", "application/x-tar"},
            {".tgz", "application/x-compressed"},
            {".txt", "text/plain"},
            {".wav", "audio/x-wav"},
            {".wma", "audio/x-ms-wma"},
            //{".wmv", "audio/x-ms-wmv"},
            {".wmv", "video/mp4"},
            {".wps", "application/vnd.ms-works"},
            {".xml", "text/plain"},
            {".z", "application/x-compress"},
            {".zip", "application/x-zip-compressed"},
            {"", "*/*"}
    };

    private int getFileType(String fileName) {
        String suffix = "";
        try {
            suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return R.drawable.moudle_image_icon_unkown;
        }
        //文本文件
        for (String t : docType) {
            if (t.equalsIgnoreCase(suffix)) {
                return R.drawable.moudle_image_icon_doc;
            }
        }

//        表格
        for (String t : xlsType) {
            if (t.equalsIgnoreCase(suffix)) {
                return R.drawable.moudle_image_icon_xls;
            }
        }

        //图像
        for (String t : jpgType) {
            if (t.equalsIgnoreCase(suffix)) {
                return R.drawable.moudle_image_icon_picture;
            }
        }

        //声音
        for (String t : mp3Type) {
            if (t.equalsIgnoreCase(suffix)) {
                return R.drawable.moudle_image_icon_music;
            }
        }

        //视频
        for (String t : mp4Type) {
            if (t.equalsIgnoreCase(suffix)) {
                return R.drawable.moudle_image_icon_video;
            }
        }

        //压缩包
        for (String t : zipType) {
            if (t.equalsIgnoreCase(suffix)) {
                return R.drawable.moudle_image_icon_zip;
            }
        }
        return R.drawable.moudle_image_icon_unkown;
    }

    private boolean isImageType(String fileName) {
        boolean isImage = false;
        //图像
        for (String t : jpgType) {
            if (t.equalsIgnoreCase(fileName)) {
                isImage = true;
            }
        }

        return isImage;

    }

    private List<String> docType = Arrays.asList("doc", "docx", "txt", "pdf");
    private List<String> xlsType = Arrays.asList("xls", "xlsx");
    private List<String> jpgType = Arrays.asList("jpg", "png", "jepg");
    private List<String> mp3Type = Arrays.asList("mp3", "wav", "wma", "amr");
    private List<String> mp4Type = Arrays.asList("mp4", "avi", "mov", "rmvb", "wmv");
    private List<String> zipType = Arrays.asList("zip", "rar", "7z");


    public void onDestory() {
        if (tasks != null && tasks.size() > 0) {
            for (BaseDownloadTask task : tasks) {
                task.pause();
            }
        }
    }

}
