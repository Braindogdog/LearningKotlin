package com.firebaselibrary.model;

import android.content.Context;
import android.support.annotation.Nullable;

import com.firebaselibrary.bean.CarBean;
import com.firebaselibrary.bean.CommonResultBean;
import com.firebaselibrary.bean.ExpertBean;
import com.firebaselibrary.bean.LeaderBean;

import java.util.List;

import common.baselibrary.baseutil.GsonUtil;

/**
 * Created by chasen on 2018/5/21.
 */

public class NomalDataOfflineModelImpl implements NomalDataModel {

    private Context context;
    private String baseUrl;

    public NomalDataOfflineModelImpl(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    @Override
    public void getExpertList(final ILoadingResultListener<List<ExpertBean>> listener, @Nullable String name, @Nullable String unit, String pageIndex, String pageSize) {
        String result = "{\"isSuccess\":true,\"result\":[{\"name\":\"专家1\",\"sex\":\"0\",\"duty\":\"2525\",\"workPhone\":\"119\",\"unit\":{\"id\":\"157a9ef413b3469b863fadc7a2c1c9\"},\"id\":\"20180522095846259\"},{\"name\":\"专家2\",\"sex\":\"1\",\"duty\":\"入团\",\"workPhone\":\"119\",\"unit\":{\"id\":\"\"},\"id\":\"20180521035552268\"},{\"name\":\"专家3\",\"sex\":\"1\",\"duty\":\"4\",\"workPhone\":\"119\",\"unit\":{\"id\":\"\"},\"id\":\"20180521035350754\"},{\"name\":\"专家4\",\"sex\":\"1\",\"duty\":\"4\",\"workPhone\":\"119\",\"unit\":{\"id\":\"\"},\"id\":\"20180521035335100\"},{\"name\":\"专家5\",\"sex\":\"1\",\"duty\":\"1\",\"workPhone\":\"119\",\"unit\":{\"id\":\"04fc6b8e99514bd69fa2484d0ae8ea\"},\"id\":\"20180521033147893\"},{\"name\":\"专家6\",\"sex\":\"1\",\"duty\":\"任务\",\"workPhone\":\"119\",\"unit\":{\"id\":\"157a9ef413b3469b863fadc7a2c1c9\"},\"id\":\"20180521032917385\"},{\"name\":\"专家7\",\"sex\":\"1\",\"duty\":\"re\",\"workPhone\":\"119\",\"unit\":{\"id\":\"20180418065956635\"},\"id\":\"20180518055709792\"},{\"name\":\"专家8\",\"sex\":\"1\",\"duty\":\"wqe诶我去\",\"workPhone\":\"119\",\"unit\":{\"id\":\"92495ba8188248d68df03e65b7f0ac\"},\"id\":\"20180517123041904\"},{\"name\":\"专家9\",\"sex\":\"1\",\"duty\":\"2525\",\"workPhone\":\"119\",\"unit\":{\"id\":\"024e8f1ab4d2448ebea8a54e096374\"},\"id\":\"20180516093033033\"},{\"name\":\"专家10\",\"sex\":\"1\",\"duty\":\"74127\",\"workPhone\":\"119\",\"unit\":{\"id\":\"157a9ef413b3469b863fadc7a2c1c9\"},\"id\":\"20180516032510103\"}],\"exceptionString\":\"\"}";
        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
        if (resultBean.isSuccess()) {
            List<ExpertBean> list = GsonUtil.json2List(GsonUtil.obj2String(resultBean.getResult()), ExpertBean.class);
            listener.success(list);
        } else {
            listener.error(resultBean.getExceptionString());
        }
    }

    @Override
    public void getExpert(final ILoadingResultListener<ExpertBean> listener, String id) {
        String result = "{\"isSuccess\":true,\"result\":{\"name\":\"专家\",\"sex\":\"0\",\"duty\":\"2525\",\"workPhone\":\"119\",\"unit\":{\"name\":\"特勤中队\",\"id\":\"157a9ef413b3469b863fadc7a2c1c9\"},\"id\":\"20180522095846259\"},\"exceptionString\":\"\"}";
        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
        if (resultBean.isSuccess()) {
            ExpertBean expertBean = GsonUtil.json2Bean(GsonUtil.obj2String(resultBean.getResult()), ExpertBean.class);
            listener.success(expertBean);
        } else {
            listener.error(resultBean.getExceptionString());
        }
    }

    @Override
    public void getLeaderList(final ILoadingResultListener<List<LeaderBean>> listener, String unitID, String pageIndex, String pageSize) {
        String result = "{\"isSuccess\":true,\"result\":[{\"name\":\"赵永强\",\"id\":\"12121\"},{\"name\":\"张梦辉\",\"id\":\"12122\"},{\"name\":\"梁广兴\",\"id\":\"12123\"},{\"name\":\"李寻欢\",\"id\":\"12124\"},{\"name\":\"周大庆\",\"id\":\"12125\"},{\"name\":\"王水泵\",\"id\":\"12126\"}],\"exceptionString\":\"\"}";
        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
        if (resultBean.isSuccess()) {
            List<LeaderBean> list = GsonUtil.json2List(GsonUtil.obj2String(resultBean.getResult()), LeaderBean.class);
            listener.success(list);
        } else {
            listener.error(resultBean.getExceptionString());
        }

    }

    @Override
    public void getCarList(final ILoadingResultListener<List<CarBean>> listener, String unitID, String pageIndex, String pageSize) {
        String result = "{\"isSuccess\":true,\"result\":[{\"name\":\"五十铃水罐消防车\",\"water\":0.0,\"froth\":0.0,\"dryDust\":0.0,\"state\":\"待命\",\"unit\":{\"name\":\"江西省消防总队\",\"id\":\"123\"},\"id\":\"v1\"},{\"name\":\"五十铃水罐消防车\",\"water\":0.0,\"froth\":0.0,\"dryDust\":0.0,\"state\":\"待命\",\"unit\":{\"name\":\"江西省消防总队\",\"id\":\"123\"},\"id\":\"v2\"},{\"name\":\"五十铃水罐消防车\",\"water\":0.0,\"froth\":0.0,\"dryDust\":0.0,\"state\":\"待命\",\"unit\":{\"name\":\"江西省消防总队\",\"id\":\"123\"},\"id\":\"v3\"},{\"name\":\"五十铃水罐消防车\",\"water\":0.0,\"froth\":0.0,\"dryDust\":0.0,\"state\":\"待命\",\"unit\":{\"name\":\"江西省消防总队\",\"id\":\"123\"},\"id\":\"v4\"},{\"name\":\"五十铃水罐消防车\",\"water\":0.0,\"froth\":0.0,\"dryDust\":0.0,\"state\":\"待命\",\"unit\":{\"name\":\"江西省消防总队\",\"id\":\"123\"},\"id\":\"v5\"}],\"exceptionString\":\"\"}";
        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
        if (resultBean.isSuccess()) {
            List<CarBean> list = GsonUtil.json2List(GsonUtil.obj2String(resultBean.getResult()), CarBean.class);
            listener.success(list);
        } else {
            listener.error(resultBean.getExceptionString());
        }

    }
}
