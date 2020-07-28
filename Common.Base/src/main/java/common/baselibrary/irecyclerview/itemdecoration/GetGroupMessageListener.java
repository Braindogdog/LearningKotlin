package common.baselibrary.irecyclerview.itemdecoration;

import android.view.View;

/**
 * Created by chasen on 2018/4/16.
 * 获取分组信息的回调接口
 */

public interface GetGroupMessageListener {

    String getGroupName(int position);

    View getGroupView(int position);
}
