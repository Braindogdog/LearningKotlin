package common.baselibrary.baseutil;

import android.app.Activity;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by chasen on 2018/3/21.
 */

public class ActivityManager {

    private static final ActivityManager mInstance = new ActivityManager();

    private static LinkedList<Activity> mQueue;

    private ActivityManager() {
        ActivityManager.mQueue = new LinkedList<>();
    }

    /**
     * 添加一个activity到队列
     */
    public void pushActivity(Activity activity) {
        mInstance.doPushActivity(activity);
    }

    /**
     * 删除队列中的指定activity
     */
    public void popActivity(Activity activity) {
        mInstance.doPopActivity(activity);
    }

    /**
     * 弹出位于栈顶的activity
     */
    public Activity pop() {
        if (mQueue != null && mQueue.size() > 0) {
            return mQueue.peek();
        } else {
            return null;
        }
    }

    /**
     * 获取指定位置的activity
     */
    public Activity popIndex(int postion) {
        if (mQueue != null && mQueue.size() > postion) {
            return mQueue.get(postion);
        } else {
            return null;
        }
    }

    /**
     * 结束所有activity
     */
    public void finishAllActivity() {
        mInstance.doFinishAll();
    }

    public void doPushActivity(Activity activity) {
        mQueue.push(activity);
    }

    public void doPopActivity(Activity activity) {
        if (ActivityManager.mQueue.contains(activity)) {
            ActivityManager.mQueue.remove(activity);
        }
    }

    public void doFinishAll() {
        Iterator<Activity> it = mQueue.iterator();
        while (it.hasNext()) {
            Activity a = it.next();
            it.remove();
            a.finish();
        }
    }

    public static ActivityManager getInstance() {
        return mInstance;
    }

    public LinkedList<Activity> getActivityLinkQueue() {
        return mQueue;
    }

    public int getSize() {
        return mQueue.size();
    }


    /**
     * 关闭N个activities
     *
     * @param closeNumberActivities 关闭activity的个数
     */
    public void closeNumberActivities(int closeNumberActivities) {
        // 关闭个数小于1的时候直接跳出
        if (closeNumberActivities <= 0) {
            return;
        }
        LinkedList<Activity> mActivities = mQueue;
        if (mActivities != null && mActivities.size() <= 1) {
            return;
        }

        try {
            int countTemp = 0;
            // 倒序遍历acitivty
            for (int i = mActivities.size() - 1; i >= 0; i--) {
                // 如果当前页面为NativeAppActivity,则直接finish();
                Activity mActivity = mActivities.get(i);
                if (mActivity != null) {
                    mActivity.finish();
                    mActivities.remove(mActivity);
                } else {// 其他情况下finish掉activity
                    // 当前页面不能是最后一页
                    if (mActivities.size() > 1) {
                        mActivity.finish();
                        mActivities.remove(mActivity);
                        countTemp++;
                    } else {
                        i = -1;
                    }
                }
                // 退出循环
                if (countTemp == closeNumberActivities) {
                    i = -1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
