package com.firebaselibrary.configure;

import android.content.Context;
import android.view.View;

import com.firebaselibrary.model.AppConfigureModel;
import com.firebaselibrary.model.AppConfigureModelImpl;
import com.firebaselibrary.model.CollectDataModel;
import com.firebaselibrary.model.CollectDataModelImpl;
import com.firebaselibrary.model.FireCaseModel;
import com.firebaselibrary.model.FireCaseModelImpl;
import com.firebaselibrary.model.FireUnitModel;
import com.firebaselibrary.model.FireUnitModelImpl;
import com.firebaselibrary.model.GpsModel;
import com.firebaselibrary.model.GpsModelImpl;
import com.firebaselibrary.model.HomePageActivityModel;
import com.firebaselibrary.model.HomePageActivityModelImpl;
import com.firebaselibrary.model.NomalDataModel;
import com.firebaselibrary.model.NomalDataModelImpl;
import com.firebaselibrary.model.PlanModel;
import com.firebaselibrary.model.PlanModelImpl;
import com.firebaselibrary.model.SearchResourceModel;
import com.firebaselibrary.model.SearchResourceModelImpl;
import com.firebaselibrary.model.UserModel;
import com.firebaselibrary.model.UserModelImpl;

import java.util.Map;

import common.baidunavigationlibrary.BaiduMapApiServiceImpl;
import common.baselibrary.baseutil.ioc.Injector;
import common.baselibrary.baseutil.ioc.ServerLocator;
import common.map.api.IMapApiService;

/**
 * Created by chasen on 2018/4/9.
 */

public class JiangXiConfigure implements IConfigure {
    private Context context;

    public JiangXiConfigure(Context context) {
        this.context = context;
    }

    @Override
    public void initIOC() {
        ServerLocator.init(context, new Injector() {
            @Override
            public void inject(Context context, Map<Object, Object> ioc) {
                //警情数据处理model
                ioc.put(FireCaseModel.class, new FireCaseModelImpl(context, "http://220.249.16.30:99/fireweb/api/v1"));
                //首页数据处理model
                ioc.put(HomePageActivityModel.class, new HomePageActivityModelImpl());
                //用户数据model
                ioc.put(UserModel.class, new UserModelImpl(context, "http://220.249.16.30:99/fireweb/api/v1"));
                //App信息、配置model
                ioc.put(AppConfigureModel.class, new AppConfigureModelImpl(context, "http://220.249.16.30:99/fireweb/api/v1"));
                //数据采集
                ioc.put(CollectDataModel.class, new CollectDataModelImpl(context, "http://220.249.16.30:99/fireweb/api/v1"));
                //通用数据
                ioc.put(NomalDataModel.class, new NomalDataModelImpl(context, "http://220.249.16.30:99/fireweb/api/v1"));
                //消防单位
                ioc.put(FireUnitModel.class, new FireUnitModelImpl(context, "http://220.249.16.30:99/fireweb/api/v1"));
                //综合查询
                ioc.put(SearchResourceModel.class, new SearchResourceModelImpl());
                //上传定位信息
                ioc.put(GpsModel.class, new GpsModelImpl());
                //预案信息
                ioc.put(PlanModel.class, new PlanModelImpl());

            }
        });
    }

    @Override
    public IMapApiService getMapService(View mapView, double currentMapX, double currentMapY, boolean openLoc) {
        return new BaiduMapApiServiceImpl(mapView, currentMapX, currentMapY, openLoc);
    }

}
