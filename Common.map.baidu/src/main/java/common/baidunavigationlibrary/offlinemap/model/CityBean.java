package common.baidunavigationlibrary.offlinemap.model;

import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.map.offline.MKOLUpdateElement;

import java.util.List;

import common.baselibrary.irecyclerview.expandable.model.ExpandableListItem;

/**
 * Created by chasen on 2018/4/16.
 */

public class CityBean implements ExpandableListItem {

    public static final String HOT_CITY = "热门地区";
    public static final String ALL_CITY = "全部地区";
    public static final String HAVE_DOWNLOAD_CITY = "已下载";
    public static final String RECOMMEND_CITY = "推荐城市";

    private String groupName;
    private MKOLSearchRecord mkolSearchRecord;
    private MKOLUpdateElement mkolUpdateElement;
    private boolean isChildren;
    private boolean expanded;
    private List<CityBean> listChild;
    private boolean isComplete;
    private boolean isLoading;

    public MKOLUpdateElement getMkolUpdateElement() {
        return mkolUpdateElement;
    }

    public void setMkolUpdateElement(MKOLUpdateElement mkolUpdateElement) {
        this.mkolUpdateElement = mkolUpdateElement;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public List<CityBean> getListChild() {
        return listChild;
    }

    public void setListChild(List<CityBean> listChild) {
        this.listChild = listChild;
    }

    public boolean isChildren() {
        return isChildren;
    }

    public void setChildren(boolean children) {
        isChildren = children;
    }

    public MKOLSearchRecord getMkolSearchRecord() {
        return mkolSearchRecord;
    }

    public void setMkolSearchRecord(MKOLSearchRecord mkolSearchRecord) {
        this.mkolSearchRecord = mkolSearchRecord;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public List<?> getChildItemList() {
        return listChild;
    }

    @Override
    public boolean isExpanded() {
        return expanded;
    }

    @Override
    public void setExpanded(boolean isExpanded) {
        this.expanded = isExpanded;
    }
}
