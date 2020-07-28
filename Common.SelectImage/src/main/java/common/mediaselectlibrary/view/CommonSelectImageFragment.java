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
import common.baselibrary.baseutil.ToastUtil;
import common.baselibrary.baseview.BaseFragment;
import common.baselibrary.baseview.CommomDialog;
import common.baselibrary.irecyclerview.IRecyclerView;
import common.baselibrary.irecyclerview.OnItemClickListener;
import common.baselibrary.irecyclerview.OnItemLongClickListener;
import common.mediaselectlibrary.R;
import common.mediaselectlibrary.model.ImageMessage;
import common.mediaselectlibrary.model.SelectImageParameter;

/**
 * Created by chasen on 2018/4/19.
 */

public class CommonSelectImageFragment extends BaseFragment {

    public static final int SELECT_IMAGES = 103;

    private IRecyclerView recyclerview;
    private int spanCount;
    private int maxNumber;
    private int requestCode = 0;
    private SelectImageAdapter selectImageAdapter;
    private ArrayList<ImageMessage> listImages;
    private CommomDialog commomDialog;
    private DeleteImageListener deleteImageListener;

    public void setDeleteImageListener(DeleteImageListener deleteImageListener) {
        this.deleteImageListener = deleteImageListener;
    }

    @NonNull
    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_select_image;
    }

    @Override
    protected void initWidget(View view) {
        recyclerview = view.findViewById(R.id.recyclerview);
    }

    @Override
    protected void setListener() {
        selectImageAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v, Object object) {
                if (position == listImages.size()) {
                    if (listImages.size() < maxNumber) {
                        if (requestCode == 0) {
                            SelectImageActivity.startActivityForResult(context, new SelectImageParameter(maxNumber - listImages.size()), SELECT_IMAGES);
                        } else {
                            SelectImageActivity.startActivityForResult(context, new SelectImageParameter(maxNumber - listImages.size()), requestCode);
                        }
                    } else {
                        ToastUtil.showShort(context, "最多只能选择" + maxNumber + "张图片");
                    }
                } else {
                    ShowImageActivity.startActivity(context, listImages, position);
                }
            }
        });
        selectImageAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public void onItemLongClick(final int position, View v, Object object) {
                if (position != listImages.size()) {
                    commomDialog.setOnSubmitListener(new CommomDialog.OnSubmitListener() {
                        @Override
                        public void onClick() {
                            commomDialog.dismiss();
                            if (ImageMessage.URL == listImages.get(position).getType() && deleteImageListener != null) {
                                deleteImageListener.deleteImage(listImages.get(position).getId(),position);
                            } else {
                                listImages.remove(position);
                                selectImageAdapter.setList(listImages);
                            }
                        }
                    }).show();
                }
            }
        });
    }

    public void removeImage(int position) {
        listImages.remove(position);
        selectImageAdapter.setList(listImages);
    }

    @Override
    protected void init() {
        commomDialog = new CommomDialog(context)
                .setContent("确定要删除此图片吗？");
        Bundle bundle = getArguments();
        if (bundle != null) {
            spanCount = bundle.getInt("spanCount", 3);
            maxNumber = bundle.getInt("maxNumber", 9);
            requestCode = bundle.getInt("requestCode", 0);
            listImages = bundle.getParcelableArrayList("listImages");
        } else {
            spanCount = 3;
            maxNumber = 9;
        }
        if (EmptyUtil.isEmpty(listImages)) {
            listImages = new ArrayList<>();
        }
        recyclerview.setLayoutManager(new GridLayoutManager(context, spanCount));
        selectImageAdapter = new SelectImageAdapter(context);
        recyclerview.setIAdapter(selectImageAdapter);
        selectImageAdapter.setList(listImages);
    }

    public void setListImage(ArrayList<ImageMessage> list) {
        listImages.clear();
        listImages.addAll(list);
        selectImageAdapter.setList(list);
    }

    public void addListImage(List<ImageMessage> list) {
        listImages.addAll(list);
        selectImageAdapter.setList(listImages);
    }

    public List<ImageMessage> getListImage() {
        return listImages;
    }

    public interface DeleteImageListener {
        void deleteImage(String imageId,int position);
    }

    public static Fragment newInstance(int spanCount, int maxNumber, @Nullable ArrayList<ImageMessage> listImage) {
        Bundle args = new Bundle();
        args.putInt("spanCount", spanCount);
        args.putInt("maxNumber", maxNumber);
        args.putParcelableArrayList("listImages", listImage);
        Fragment fgm = new CommonSelectImageFragment();
        fgm.setArguments(args);
        return fgm;
    }

    public static Fragment newInstance(int spanCount, int maxNumber, @Nullable ArrayList<ImageMessage> listImage, int requestCode) {
        Bundle args = new Bundle();
        args.putInt("spanCount", spanCount);
        args.putInt("maxNumber", maxNumber);
        args.putInt("requestCode", requestCode);
        args.putParcelableArrayList("listImages", listImage);
        Fragment fgm = new CommonSelectImageFragment();
        fgm.setArguments(args);
        return fgm;
    }
}
