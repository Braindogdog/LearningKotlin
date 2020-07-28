package com.firebaselibrary.model;

import android.content.Context;
import android.support.annotation.Nullable;

import com.firebaselibrary.bean.CommonResultBean;
import com.firebaselibrary.bean.SimpUnit;
import com.firebaselibrary.bean.UnitQueryArg;
import com.firebaselibrary.bean.fireunit.FireUnitBean;
import com.firebaselibrary.constant.UrlConstant;
import com.krx.ydzh.commoncore.config.ConfigManager;

import java.util.List;

import common.baselibrary.baseutil.GsonUtil;
import common.networkrequestlibrary.interfaces.Error;
import common.networkrequestlibrary.interfaces.Success;
import common.networkrequestlibrary.util.HttpUtil;

/**
 * Created by chasen on 2018/5/21.
 */

public class FireUnitOfflineModelImpl implements FireUnitModel {

    private Context context;
    private String baseUrl;

    public FireUnitOfflineModelImpl(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    @Override
    public void getFireUnitList(final ILoadingResultListener<List<FireUnitBean>> listener, @Nullable String shortName, @Nullable String parent, String pageIndex, String pageSize) {
        String result = "{\"isSuccess\":true,\"result\":[{\"address\":{\"point\":{\"x\":106.13501,\"y\":38.43641},\"description\":\"江西省南昌市南昌县富山大道416号\",\"id\":\"20180620101834512\",\"srid\":8307},\"parent\":{\"id\":\"\"},\"name\":\"北京市消防总队\",\"id\":\"fdd5eced27624f7da0c2cfb7f\"},{\"address\":{\"point\":{\"x\":117.21096,\"y\":29.35947},\"description\":\"\",\"id\":\"20180620101834512\",\"srid\":8307},\"parent\":{\"id\":\"02821fc9091348599b54a83104513d\"},\"name\":\"浮梁县公安消防大队\",\"id\":\"1509332bce7d4f34954a97c1b21597\"},{\"address\":{\"point\":{\"x\":115.88537,\"y\":28.66083},\"description\":\"江西省南昌市西湖区洪城路966号\",\"id\":\"20180620101834512\",\"srid\":8307},\"parent\":{\"id\":\"\"},\"name\":\"江西省公安消防总队\",\"id\":\"e23a1005fe7b45c5ade1d029d8e3ed\"},{\"address\":{\"point\":{\"x\":106.14133,\"y\":38.40565},\"description\":\"\",\"id\":\"20180620101834512\",\"srid\":8307},\"parent\":{\"id\":\"fdd5eced27624f7da0c2cfb7f\"},\"name\":\"东城区消防支队\",\"id\":\"20180418065956635\"},{\"address\":{\"point\":{\"x\":105.94989,\"y\":38.63307},\"id\":\"20180620101834512\",\"srid\":8307},\"parent\":{\"id\":\"20180321033808948abcde\"},\"name\":\"北京市消防总队\",\"id\":\"20180321033808948abcde\"},{\"address\":{\"point\":{\"x\":116.23543,\"y\":29.73057},\"description\":\"江西省九江市湖口县通站大道\",\"id\":\"20180620101834512\",\"srid\":8307},\"parent\":{\"id\":\"35d6383badb14e08ad0f64ce0a133e\"},\"name\":\"湖口县公安消防大队\",\"id\":\"0181cc33fd114bf3bc34a82d8a6c50\"},{\"address\":{\"point\":{\"x\":117.07159,\"y\":27.72083},\"description\":\"江西省抚州市资溪县鹤城镇解放北路1号\",\"id\":\"20180620101834512\",\"srid\":8307},\"parent\":{\"id\":\"ebe7a2dbc43546ed882352a0524d06\"},\"name\":\"资溪县中队\",\"id\":\"024e8f1ab4d2448ebea8a54e096374\"},{\"address\":{\"point\":{\"x\":117.19328,\"y\":29.28092},\"id\":\"20180620101834528\",\"srid\":8307},\"parent\":{\"id\":\"e23a1005fe7b45c5ade1d029d8e3ed\"},\"name\":\"景德镇市公安消防支队\",\"id\":\"02821fc9091348599b54a83104513d\"},{\"address\":{\"point\":{\"x\":114.79089,\"y\":27.73226},\"id\":\"20180620101834528\",\"srid\":8307},\"parent\":{\"id\":\"d479400131e54cfbba500ffaad1d78\"},\"name\":\"仙女湖中队\",\"id\":\"02e3ffa5d4924ea3955f2eee2c6e3e\"},{\"address\":{\"point\":{\"x\":0.0,\"y\":0.0},\"id\":\"20180620101834528\",\"srid\":8307},\"parent\":{\"id\":\"8475bd62ea2947a2866fe5cf849c93\"},\"name\":\"特勤中队\",\"id\":\"04fc6b8e99514bd69fa2484d0ae8ea\"}],\"exceptionString\":\"\"}";
        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
        if (resultBean.isSuccess()) {
            List<FireUnitBean> list = GsonUtil.json2List(GsonUtil.obj2String(resultBean.getResult()), FireUnitBean.class);
            listener.success(list);
        } else {
            listener.error(resultBean.getExceptionString());
        }
    }

    @Override
    public void getFireUnit(final ILoadingResultListener<FireUnitBean> listener, String id) {
        String result = "{\"isSuccess\":true,\"exceptionString\":\"\"}";
        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
        if (resultBean.isSuccess()) {
            FireUnitBean fireUnitBean = GsonUtil.json2Bean(GsonUtil.obj2String(resultBean.getResult()), FireUnitBean.class);
            listener.success(fireUnitBean);
        } else {
            listener.error(resultBean.getExceptionString());
        }
    }

    @Override
    public void getUnitList(final ILoadingResultListener<List<SimpUnit>> listener, UnitQueryArg args) {
        new HttpUtil.Builder(context)
                .url(ConfigManager.getInstance().getNetConfig(UrlConstant.BASE_PERSON_LIST_URL) + UrlConstant.GET_UNIT_LIST)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {
                        if (listener == null) {
                            return;
                        }
                        try {
                            CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
                            if (resultBean.isSuccess()) {
                                List<SimpUnit> list = GsonUtil.json2List(GsonUtil.obj2String(resultBean.getResult()), SimpUnit.class);
                                listener.success(list);
                            } else {
                                listener.error(resultBean.getExceptionString());
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            listener.error(e.getMessage());
                        }
                    }
                })
                .error(new Error() {
                    @Override
                    public void Error(String error) {
                        listener.error(error);
                    }
                })
                .postWithJson(GsonUtil.obj2String(args));
    }
}
