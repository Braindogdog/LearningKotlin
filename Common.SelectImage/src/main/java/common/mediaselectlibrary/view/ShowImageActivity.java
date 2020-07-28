package common.mediaselectlibrary.view;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.krx.ydzh.commoncore.config.ConfigManager;
import com.krx.ydzh.commoncore.constants.ARouterConfig;
import com.krx.ydzh.commoncore.constants.Constants;

import java.util.ArrayList;
import java.util.List;

import common.baselibrary.baseutil.EmptyUtil;
import common.baselibrary.baseutil.SystemBarUtil;
import common.baselibrary.baseview.BaseActivity;
import common.mediaselectlibrary.R;
import common.mediaselectlibrary.model.EventMedia;
import common.mediaselectlibrary.model.ImageMessage;

@Route(path = ARouterConfig.CORE_IMAGE_BROWSER_FRAG)
public class ShowImageActivity extends BaseActivity {

    private ViewPager viewpager;
    private TextView tv_number;
    private ArrayList<ImageMessage> listImages;
    private int index;

    @NonNull
    @Override
    protected int getLayoutResID() {
        return R.layout.activity_show_image;
    }

    @Override
    protected void getIntentData(Intent intent) {
        index = intent.getIntExtra("index", 0);
        List<String> imgPath = intent.getStringArrayListExtra("listStrs");
        if(imgPath != null){
            listImages = getImageList(imgPath);
        }else{
            listImages = intent.getParcelableArrayListExtra("listPath");
        }
        if (EmptyUtil.isEmpty(listImages))
            listImages = new ArrayList<>();
    }

    private ArrayList<ImageMessage> getImageList(List<String> urls) {
        ArrayList<ImageMessage> imageMessagesList = new ArrayList<>();
        for (String url : urls) {
            ImageMessage imageMessage = new ImageMessage();
            imageMessage.setId(url);
            imageMessage.setPath(url);
            imageMessage.setType(ImageMessage.URL);
            imageMessagesList.add(imageMessage);
        }
        return imageMessagesList;
    }

    @Override
    protected void initWidget() {
        viewpager = findViewById(R.id.viewpager);
        tv_number = findViewById(R.id.tv_number);
    }

    @Override
    protected void setListener() {
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_number.setText((position + 1) + "/" + listImages.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void init() {
        SystemBarUtil.setTranslucent(this, 0);
        tv_number.setText((index + 1) + "/" + listImages.size());
        ShowImageFragmentPageAdapter pageAdapter = new ShowImageFragmentPageAdapter(getSupportFragmentManager());
        viewpager.setAdapter(pageAdapter);
        pageAdapter.setList(listImages);
        pageAdapter.notifyDataSetChanged();
        if (listImages.size() > index)
            viewpager.setCurrentItem(index);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public static void startActivity(Activity context, ArrayList<ImageMessage> listPath, int index) {
        Intent intent = new Intent(context, ShowImageActivity.class);
        intent.putParcelableArrayListExtra("listPath", listPath);
        intent.putExtra("index", index);
        context.startActivity(intent);
        context.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
