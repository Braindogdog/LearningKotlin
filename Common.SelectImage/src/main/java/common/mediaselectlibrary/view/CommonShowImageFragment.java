package common.mediaselectlibrary.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import common.baselibrary.baseutil.EmptyUtil;
import common.baselibrary.baseview.BaseFragment;
import common.baselibrary.irecyclerview.IRecyclerView;
import common.baselibrary.irecyclerview.OnItemClickListener;
import common.mediaselectlibrary.R;
import common.mediaselectlibrary.model.ImageMessage;

/**
 * Created by chasen on 2018/4/24.
 */

public class CommonShowImageFragment extends BaseFragment {

    private IRecyclerView recyclerview;
    private int spanCount;
    private ShowImageAdapter showImageAdapter;
    private ArrayList<ImageMessage> listImages;

    @NonNull
    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_show_image;
    }

    @Override
    protected void initWidget(View view) {
        recyclerview = view.findViewById(R.id.recyclerview);
    }

    @Override
    protected void setListener() {
        showImageAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v, Object object) {
                ShowImageActivity.startActivity(context, listImages, position);
            }
        });
    }

    @Override
    protected void init() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            spanCount = bundle.getInt("spanCount", 3);
            listImages = bundle.getParcelableArrayList("listImages");
        } else {
            spanCount = 3;
        }
        if (EmptyUtil.isEmpty(listImages)) {
            listImages = new ArrayList<>();
        }
        recyclerview.setLayoutManager(new GridLayoutManager(context, spanCount));
        showImageAdapter = new ShowImageAdapter(context);
        recyclerview.setIAdapter(showImageAdapter);
        showImageAdapter.setList(listImages);
    }

    public void setListImage(ArrayList<ImageMessage> list) {
        listImages.clear();
        listImages.addAll(list);
        showImageAdapter.setList(list);
    }

    public void addListImage(List<ImageMessage> list) {
        listImages.addAll(list);
        showImageAdapter.setList(listImages);
    }

    public List<ImageMessage> getListImage() {
        return listImages;
    }

    public static CommonShowImageFragment newInstance(int spanCount, @Nullable ArrayList<ImageMessage> listImage) {
        Bundle args = new Bundle();
        args.putInt("spanCount", spanCount);
        args.putParcelableArrayList("listImages", listImage);
        CommonShowImageFragment fgm = new CommonShowImageFragment();
        fgm.setArguments(args);
        return fgm;
    }
}
