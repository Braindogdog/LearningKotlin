package common.baidunavigationlibrary.offlinemap.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by chasen on 2018/4/13.
 */

public class OfflineMapTabAdapter extends FragmentPagerAdapter {

    private List<OfflineMapActivity.TabInfo> listTitle;
    private String city;
    private OfflineMapDownLoadFragment offlineMapDownLoadFragment;
    private OfflineMapCityListFragment offlineMapCityListFragment;

    public OfflineMapTabAdapter(FragmentManager fm, List<OfflineMapActivity.TabInfo> list, String city) {
        super(fm);
        this.listTitle = list;
        this.city = city;
        offlineMapDownLoadFragment = OfflineMapDownLoadFragment.getInstance(city);
        offlineMapCityListFragment = OfflineMapCityListFragment.getInstance(city);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new Fragment();
        switch (listTitle.get(position).id) {
            case OfflineMapActivity.TabInfo.DOWNLOAD:
                fragment = offlineMapDownLoadFragment;
                break;
            case OfflineMapActivity.TabInfo.CITY_LIST:
                fragment = offlineMapCityListFragment;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return listTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position).title;
    }

    public void updateDownload() {
        offlineMapDownLoadFragment.refresh();
    }
    public void updateCityList() {
        offlineMapCityListFragment.refresh();
    }
}
