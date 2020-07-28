package common.mediaselectlibrary.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import common.baselibrary.baseview.BaseActivity;
import common.mediaselectlibrary.R;
import common.mediaselectlibrary.model.SelectImageParameter;
import common.mediaselectlibrary.presenter.SelectImagePresenter;
import common.mediaselectlibrary.presenter.SelectImagePresenterImpl;

/**
 * Created by chasen on 2018/3/12.
 */

public class SelectImageActivity extends BaseActivity {
    public static final String EXTRA_RESULT_URI = "extra_result_uri";
    public static final String EXTRA_RESULT_PATH = "extra_result_path";
    public static final int TAKE_PHOTO = 1;
    public static final int SELECT_IMAGE = 2;
    public static final int SELECT_IMAGES = 3;
    public static final int GET_PREMISSION = 4;
    public static final int ACTION_TAKE_PHOTO = 5;
    public static final int ACTION_SELECT_IMAGE = 6;
    public static final int ACTION_SELECT_IMAGES = 7;
    public static final int ZOOM_PHOTO = 8;

    private TextView tv_select_image, tv_take_photo;

    private SelectImagePresenter presenter;

    private SelectImageParameter parameter;
    private int maxNumber;


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_selectimages;
    }

    @Override
    protected void getIntentData(Intent intent) {
        try {
            parameter = (SelectImageParameter) getIntent().getSerializableExtra("parameter");
            maxNumber = parameter.getMaxNumber();
        } catch (Exception e) {
            e.printStackTrace();
            maxNumber = 1;
            parameter = new SelectImageParameter();
        }
    }

    @Override
    protected void initWidget() {
        tv_select_image = (TextView) findViewById(R.id.tv_select_image);
        tv_take_photo = (TextView) findViewById(R.id.tv_take_photo);

        titleBar.setTitle(getString(R.string.chosse_iamge));
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle(R.string.chosse_iamge);
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void setListener() {
        tv_take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.checkPermissions(ACTION_TAKE_PHOTO);
            }
        });
        tv_select_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.checkPermissions(ACTION_SELECT_IMAGES);
//                if (maxNumber > 1) {
//                    presenter.checkPermissions(ACTION_SELECT_IMAGES);
//                } else {
//                    presenter.checkPermissions(ACTION_SELECT_IMAGE);
//                }
            }
        });
    }

    @Override
    protected void init() {
        presenter = new SelectImagePresenterImpl(this, parameter, maxNumber);
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

    public static void startActivityForResult(Activity context, SelectImageParameter parameter, int requestCode) {
        Intent intent = new Intent(context, SelectImageActivity.class);
        intent.putExtra("parameter", parameter);
        context.startActivityForResult(intent, requestCode);
    }

    public static List<String> obtainResultPath(Intent data) {
        return data.getStringArrayListExtra(EXTRA_RESULT_PATH);
    }

    public static List<Uri> obtainResultUri(Intent data) {
        return data.getParcelableArrayListExtra(EXTRA_RESULT_URI);
    }
}
