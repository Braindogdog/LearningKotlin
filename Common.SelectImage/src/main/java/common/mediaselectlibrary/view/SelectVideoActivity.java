package common.mediaselectlibrary.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.krx.ydzh.commoncore.constants.ARouterConfig;

import java.util.List;

import common.baselibrary.baseview.BaseActivity;
import common.mediaselectlibrary.R;
import common.mediaselectlibrary.presenter.SelectVideoPresenter;
import common.mediaselectlibrary.presenter.SelectVideoPresenterImpl;

/**
 * Created by chasen on 2018/3/12.
 */
@Route(path = ARouterConfig.IMAGE_RECORD_VIDEO_ACT)
public class SelectVideoActivity extends BaseActivity {
    public static final String EXTRA_RESULT_URI = "extra_result_uri";
    public static final String EXTRA_RESULT_PATH = "extra_result_path";
    public static final int ACTION_TAKE_VIDEO = 11;
    public static final int ACTION_SELECT_VIDEO = 12;
    public static final int TAKE_VIDEO = 13;
    public static final int SELECT_VIDEO = 14;
    public static final int GET_PREMISSION = 15;
    private TextView tv_select_video, tv_take_video;

    private SelectVideoPresenter presenter;
    private int maxNumber;


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_selectvideos;
    }

    @Override
    protected void getIntentData(Intent intent) {
        maxNumber = intent.getIntExtra("maxNumber", 1);
    }

    @Override
    protected void initWidget() {
        tv_select_video = (TextView) findViewById(R.id.tv_select_video);
        tv_take_video = (TextView) findViewById(R.id.tv_take_video);

        titleBar.setTitle(getString(R.string.chosse_video));
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle(R.string.chosse_video);
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void setListener() {
        tv_take_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.checkPermissions(ACTION_TAKE_VIDEO);
            }
        });
        tv_select_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.checkPermissions(ACTION_SELECT_VIDEO);
            }
        });
    }

    @Override
    protected void init() {
        presenter = new SelectVideoPresenterImpl(this, maxNumber);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    public static void startActivityForResult(Activity context, int requestCode, int maxNumber) {
        Intent intent = new Intent(context, SelectVideoActivity.class);
        intent.putExtra("maxNumber", maxNumber);
        context.startActivityForResult(intent, requestCode);
    }

    public static List<String> obtainResultPath(Intent data) {
        return data.getStringArrayListExtra(EXTRA_RESULT_PATH);
    }

    public static List<Uri> obtainResultUri(Intent data) {
        return data.getParcelableArrayListExtra(EXTRA_RESULT_URI);
    }
}
