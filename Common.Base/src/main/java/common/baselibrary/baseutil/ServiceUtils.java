package common.baselibrary.baseutil;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by Chasen on 2019/5/15
 */
public class ServiceUtils {
    /**
     * 判断服务是否开启
     *
     * @return
     */
    public static boolean isServiceRunning(Context context, String ServiceName) {
        boolean isWork = false;
        ActivityManager myAM = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(100);
        if (myList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < myList.size(); i++) {
            String mName = myList.get(i).service.getClassName().toString();
            if (mName.equals(ServiceName)) {
                isWork = true;
                break;
            }
        }
        return isWork;
    }
}
