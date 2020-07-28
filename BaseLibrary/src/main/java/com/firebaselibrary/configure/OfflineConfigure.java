package com.firebaselibrary.configure;

import android.content.Context;
import android.view.View;

import com.firebaselibrary.model.AppConfigureModel;
import com.firebaselibrary.model.AppConfigureOfflineModelImpl;
import com.firebaselibrary.model.CollectDataModel;
import com.firebaselibrary.model.CollectDataOfflineModelImpl;
import com.firebaselibrary.model.ElinkModel;
import com.firebaselibrary.model.ElinkModelImpl;
import com.firebaselibrary.model.FireCaseModel;
import com.firebaselibrary.model.FireCaseOfflineModelImpl;
import com.firebaselibrary.model.FireUnitModel;
import com.firebaselibrary.model.FireUnitOfflineModelImpl;
import com.firebaselibrary.model.GpsModel;
import com.firebaselibrary.model.GpsModelOfflineImpl;
import com.firebaselibrary.model.HomePageActivityModel;
import com.firebaselibrary.model.HomePageActivityOfflineModelImpl;
import com.firebaselibrary.model.LiveModel;
import com.firebaselibrary.model.LiveModelImpl;
import com.firebaselibrary.model.NomalDataModel;
import com.firebaselibrary.model.NomalDataOfflineModelImpl;
import com.firebaselibrary.model.PlanModel;
import com.firebaselibrary.model.PlanModelOfflineImpl;
import com.firebaselibrary.model.SearchCommandResourceModel;
import com.firebaselibrary.model.SearchCommandResourceModelImpl;
import com.firebaselibrary.model.SearchResourceModel;
import com.firebaselibrary.model.SearchResourceModelImpl;
import com.firebaselibrary.model.UserModel;
import com.firebaselibrary.model.UserOfflineModelImpl;

import java.util.Map;

import common.baidunavigationlibrary.BaiduMapApiServiceImpl;
import common.baselibrary.baseutil.ioc.Injector;
import common.baselibrary.baseutil.ioc.ServerLocator;
import common.map.api.IMapApiService;

/**
 * Created by chasen on 2018/4/9.
 */

public class OfflineConfigure implements IConfigure {
    private Context context;

    public OfflineConfigure(Context context) {
        this.context = context;
    }

    @Override
    public void initIOC() {
        ServerLocator.init(context, new Injector() {
            @Override
            public void inject(Context context, Map<Object, Object> ioc) {
                //警情数据处理model
                ioc.put(FireCaseModel.class, new FireCaseOfflineModelImpl(context, "http://220.249.16.30:99/fireweb/api/v1"));
                //首页数据处理model
                ioc.put(HomePageActivityModel.class, new HomePageActivityOfflineModelImpl());
                //用户数据model
                ioc.put(UserModel.class, new UserOfflineModelImpl(context, "http://220.249.16.30:99/fireweb/api/v1"));
                //App信息、配置model
                ioc.put(AppConfigureModel.class, new AppConfigureOfflineModelImpl(context, "http://220.249.16.30:99/fireweb/api/v1"));
                //数据采集
                ioc.put(CollectDataModel.class, new CollectDataOfflineModelImpl(context, "http://220.249.16.30:99/fireweb/api/v1"));
                //通用数据
                ioc.put(NomalDataModel.class, new NomalDataOfflineModelImpl(context, "http://220.249.16.30:99/fireweb/api/v1"));
                //消防单位
                ioc.put(FireUnitModel.class, new FireUnitOfflineModelImpl(context, "http://220.249.16.30:99/fireweb/api/v1"));
                //综合查询
                ioc.put(SearchResourceModel.class, new SearchResourceModelImpl());
                //任务资源
                ioc.put(SearchCommandResourceModel.class, new SearchCommandResourceModelImpl((SearchResourceModel) ioc.get(SearchResourceModel.class)));
                //上传定位信息
                ioc.put(GpsModel.class, new GpsModelOfflineImpl());
                //预案信息
                ioc.put(PlanModel.class, new PlanModelOfflineImpl());
                //预案信息
                ioc.put(LiveModel.class, new LiveModelImpl());
                //elink即时通讯附加功能
                ioc.put(ElinkModel.class, new ElinkModelImpl());
            }
        });
    }

    @Override
    public IMapApiService getMapService(View mapView, double currentMapX, double currentMapY,
                                        boolean openLoc) {
        return new BaiduMapApiServiceImpl(mapView, currentMapX, currentMapY, openLoc);
    }

}
