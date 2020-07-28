package common.mediaselectlibrary.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.krx.ydzh.commoncore.config.ConfigManager;
import com.krx.ydzh.commoncore.constants.Constants;
import com.liulishuo.filedownloader.BaseDownloadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import common.baselibrary.baseutil.AppUtil;
import common.baselibrary.baseutil.EmptyUtil;
import common.mediaselectlibrary.R;
import common.mediaselectlibrary.model.EventMedia;
import common.mediaselectlibrary.model.ImageMessage;
import common.mediaselectlibrary.view.ShowImageActivity;

/**
 * Created by MHshachang on 2017/3/19.
 * 附件
 */

public class StagePicMiddleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<EventMedia> infos;

    public StagePicMiddleAdapter(Context context, String alarmId, ArrayList<EventMedia> infos) {
        this.context = context;
        this.infos = infos;
        if (TextUtils.isEmpty(alarmId)) {
            alarmId = "未知信息";
        }


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.show_item_middle, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder viewHolder = (MyViewHolder) holder;
        final EventMedia attachInfo = infos.get(position);
        if (attachInfo.getUrl() == null) {
            Log.e("tagger", "attachInfo.getHttp_Url() = " + attachInfo.getUrl());
        }

        /**
         * 根据type来显示三个位置的图片
         */
        String type = attachInfo.getType();

//        if(!EmptyUtil.isEmpty(type)&&type.equals("事中")) {
            viewHolder.vImage_early.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(ConfigManager.getInstance().getBaseConfig(Constants.IMAGE_RESOURCE) +attachInfo.getId())
                    .into(viewHolder.vImage_early);
//        }else {
//            viewHolder.vImage_early.setVisibility(View.GONE);
//        }



        viewHolder.vImage_early.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //展现大图
                ShowImageActivity.startActivity((Activity) context, getImageList(), getCurImageIndex(attachInfo));
            }
        });
    }

    private int getCurImageIndex(EventMedia attachInfo) {
        int i = -1;

        for (EventMedia en : infos) {
//            if (isImageType(en.getExtensionName())) {
                i++;
                if (en.getId().equals(attachInfo.getId())) {
                    break;
//                }
            }
        }

        return i == -1 ? 0:i;
    }

    private ArrayList<ImageMessage> getImageList() {
        ArrayList<ImageMessage> imageMessagesList = new ArrayList<>();
        for (EventMedia en : infos) {
                ImageMessage imageMessage = new ImageMessage();
                imageMessage.setId(en.getId());
                imageMessage.setPath(ConfigManager.getInstance().getBaseConfig(Constants.IMAGE_RESOURCE) +en.getId());
                imageMessage.setType(ImageMessage.URL);

                imageMessagesList.add(imageMessage);

        }
        return imageMessagesList;
    }

    @Override
    public int getItemCount() {
        return infos==null?0: infos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView vImage_early;



        public MyViewHolder(View itemView) {
            super(itemView);

            vImage_early = itemView.findViewById(R.id.vImage_middle);
        }

    }


}
