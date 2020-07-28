package common.mediaselectlibrary.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import common.mediaselectlibrary.model.ImageMessage;

/**
 * Created by chasen on 2018/4/20.
 */

public class ShowImageFragmentPageAdapter extends FragmentPagerAdapter {

    private ArrayList<ImageMessage> list = new ArrayList<>();

    public ShowImageFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ImageFragment.getInstance(list.get(position));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void setList(ArrayList<ImageMessage> list) {
        this.list = list;
    }
}
