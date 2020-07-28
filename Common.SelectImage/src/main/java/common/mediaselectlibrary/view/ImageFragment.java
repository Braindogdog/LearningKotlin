package common.mediaselectlibrary.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

import common.baselibrary.baseutil.EmptyUtil;
import common.baselibrary.baseview.BaseFragment;
import common.mediaselectlibrary.R;
import common.mediaselectlibrary.model.ImageMessage;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

/**
 * Created by chasen on 2018/4/20.
 */

public class ImageFragment extends BaseFragment {

    private ImageViewTouch ivt_image;
    private ImageMessage imageMessage;

    @NonNull
    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_image_self;
    }

    @Override
    protected void initWidget(View view) {
        ivt_image = view.findViewById(R.id.ivt_image);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void init() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            imageMessage = bundle.getParcelable("imageMessage");
        }
        ivt_image.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);
        if (!EmptyUtil.isEmpty(imageMessage)) {
            if (imageMessage.getType() == ImageMessage.PATH) {
                File file = new File(imageMessage.getPath());
                Glide.with(context)
                        .load(file)
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.icon_load_error))
                        .into(ivt_image);
            } else if (imageMessage.getType() == ImageMessage.URL) {
                Glide.with(context)
                        .load(imageMessage.getPath())
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.icon_load_error))
                        .into(ivt_image);
            } else if (imageMessage.getType() == ImageMessage.RESID) {
                Glide.with(context)
                        .load(imageMessage.getResId())
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.icon_load_error))
                        .into(ivt_image);
            }
        }
    }

    public static Fragment getInstance(ImageMessage imageMessage) {
        Bundle args = new Bundle();
        args.putParcelable("imageMessage", imageMessage);
        Fragment fgm = new ImageFragment();
        fgm.setArguments(args);
        return fgm;
    }
}
