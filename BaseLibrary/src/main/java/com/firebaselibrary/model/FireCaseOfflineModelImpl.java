package com.firebaselibrary.model;

import android.content.Context;
import android.support.annotation.NonNull;

import com.firebaselibrary.bean.CommonResultBean;
import com.firebaselibrary.bean.FireCaseBean;

import java.util.List;

import common.baselibrary.baseutil.GsonUtil;

/**
 * Created by chasen on 2018/4/4.
 */

public class FireCaseOfflineModelImpl implements FireCaseModel {

    private Context context;
    private String baseUrl;

    public FireCaseOfflineModelImpl(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    @Override
    public void getFireCaseList(final ILoadingResultListener<List<FireCaseBean>> listener, @NonNull String pageIndex, @NonNull String pageSize) {
        String result = "{\"isSuccess\":true,\"result\":[{\"callTime\":\"2018/6/1\",\"address\":{\"point\":{\"x\":117.206887,\"y\":39.145925},\"description\":\"天津市105国道\",\"id\":\"20180620094159802\",\"srid\":8307},\"doTime\":\"2018/6/1\",\"incepttype\":\"false\",\"caseType1\":{\"name\":\"公务执勤\",\"id\":\"20180620094159833\"},\"caseType2\":{\"name\":\"3\",\"id\":\"20180620094159833\"},\"caseType3\":{\"id\":\"20180620094159833\"},\"division\":{\"id\":\"360000\"},\"secondUnit\":{\"name\":\"天津市消防总队\",\"id\":\"159dddd872b14d04bebab955e5bb50\"},\"thirdUnit\":{\"name\":\"天津市中队\",\"id\":\"20180427102343309\"},\"level\":\"等级1\",\"state\":\"派遣\",\"description\":\"2018年6月1日17时38分，乐平市中队接到群众报警称：位于乐平市乐港镇传芳村有人跳桥，接到报警后，中队立即出动一辆抢险救援车\",\"id\":\"20180427102343304\"},{\"callTime\":\"2018/6/2\",\"address\":{\"point\":{\"x\":117.208899,\"y\":39.137922},\"description\":\"天津市民德路106号\",\"id\":\"20180620094159802\",\"srid\":8307},\"doTime\":\"2018/6/2\",\"incepttype\":\"false\",\"caseType1\":{\"name\":\"火灾扑救\",\"id\":\"20180620094159833\"},\"caseType2\":{\"name\":\"3\",\"id\":\"20180620094159833\"},\"caseType3\":{\"id\":\"20180620094159833\"},\"division\":{\"id\":\"360000\"},\"secondUnit\":{\"name\":\"天津市消防总队\",\"id\":\"159dddd872b14d04bebab955e5bb50\"},\"thirdUnit\":{\"name\":\"乐平市中队\",\"id\":\"20180427102343309\"},\"level\":\"等级1\",\"state\":\"派遣\",\"description\":\"2018年6月1日17时38分，乐平市中队接到群众报警称：位于乐平市乐港镇传芳村有人跳桥，接到报警后，中队立即出动一辆抢险救援车\",\"id\":\"20180427102343305\"},{\"callTime\":\"2018/6/3\",\"address\":{\"point\":{\"x\":175.849028,\"y\":28.718593},\"description\":\"天津市抚生路108号\",\"id\":\"20180620094159802\",\"srid\":8307},\"doTime\":\"2018/6/3\",\"incepttype\":\"false\",\"caseType1\":{\"name\":\"火灾扑救\",\"id\":\"20180620094159833\"},\"caseType2\":{\"name\":\"3\",\"id\":\"20180620094159833\"},\"caseType3\":{\"id\":\"20180620094159833\"},\"division\":{\"id\":\"360000\"},\"secondUnit\":{\"name\":\"天津市消防总队\",\"id\":\"159dddd872b14d04bebab955e5bb50\"},\"thirdUnit\":{\"name\":\"乐平市中队\",\"id\":\"20180427102343309\"},\"level\":\"等级1\",\"state\":\"派遣\",\"description\":\"2018年6月1日17时38分，乐平市中队接到群众报警称：位于乐平市乐港镇传芳村有人跳桥，接到报警后，中队立即出动一辆抢险救援车\",\"id\":\"20180427102343307\"},{\"callTime\":\"2018/6/4\",\"address\":{\"point\":{\"x\":117.2,\"y\":39.1411},\"description\":\"天津市洪城路99号\",\"id\":\"20180620094159802\",\"srid\":8307},\"doTime\":\"2018/6/4\",\"incepttype\":\"false\",\"caseType1\":{\"name\":\"火灾扑救\",\"id\":\"20180620094159833\"},\"caseType2\":{\"name\":\"3\",\"id\":\"20180620094159833\"},\"caseType3\":{\"id\":\"20180620094159833\"},\"division\":{\"id\":\"360000\"},\"secondUnit\":{\"name\":\"天津市消防总队\",\"id\":\"159dddd872b14d04bebab955e5bb50\"},\"thirdUnit\":{\"name\":\"天津市中队\",\"id\":\"20180427102343309\"},\"level\":\"等级1\",\"state\":\"派遣\",\"description\":\"2018年6月1日17时38分，乐平市中队接到群众报警称：位于乐平市乐港镇传芳村有人跳桥，接到报警后，中队立即出动一辆抢险救援车\",\"id\":\"20180427102343310\"},{\"callTime\":\"2018/6/5\",\"address\":{\"point\":{\"x\":117.210911,\"y\":39.14139},\"description\":\"天津市105国道\",\"id\":\"20180620094159802\",\"srid\":8307},\"doTime\":\"2018/6/5\",\"incepttype\":\"false\",\"caseType1\":{\"name\":\"社会救助\",\"id\":\"20180620094159833\"},\"caseType2\":{\"name\":\"3\",\"id\":\"20180620094159833\"},\"caseType3\":{\"id\":\"20180620094159833\"},\"division\":{\"id\":\"360000\"},\"secondUnit\":{\"name\":\"天津市消防总队\",\"id\":\"159dddd872b14d04bebab955e5bb50\"},\"thirdUnit\":{\"name\":\"乐平市中队\",\"id\":\"20180427102343309\"},\"level\":\"等级1\",\"state\":\"派遣\",\"description\":\"2018年6月1日17时38分，乐平市中队接到群众报警称：位于乐平市乐港镇传芳村有人跳桥，接到报警后，中队立即出动一辆抢险救援车\",\"id\":\"20180427102343309\"},{\"callTime\":\"2018/6/6\",\"address\":{\"point\":{\"x\":117.2019,\"y\":39.1451},\"description\":\"天津市广场南路205号\",\"id\":\"20180620094159802\",\"srid\":8307},\"doTime\":\"2018/6/6\",\"incepttype\":\"false\",\"caseType1\":{\"name\":\"社会救助\",\"id\":\"20180620094159833\"},\"caseType2\":{\"name\":\"3\",\"id\":\"20180620094159833\"},\"caseType3\":{\"id\":\"20180620094159833\"},\"division\":{\"id\":\"360000\"},\"secondUnit\":{\"name\":\"天津市消防总队\",\"id\":\"159dddd872b14d04bebab955e5bb50\"},\"thirdUnit\":{\"name\":\"天津市中队\",\"id\":\"20180427102343309\"},\"level\":\"等级1\",\"state\":\"派遣\",\"description\":\"2018年6月1日17时38分，乐平市中队接到群众报警称：位于乐平市乐港镇传芳村有人跳桥，接到报警后，中队立即出动一辆抢险救援车\",\"id\":\"20180427102343300\"},{\"callTime\":\"2018/6/7\",\"address\":{\"point\":{\"x\":117.21788,\"y\":39.14211},\"description\":\"天津市105国道\",\"id\":\"20180620094159802\",\"srid\":8307},\"doTime\":\"2018/6/7\",\"incepttype\":\"false\",\"caseType1\":{\"name\":\"社会救助\",\"id\":\"20180620094159833\"},\"caseType2\":{\"name\":\"3\",\"id\":\"20180620094159833\"},\"caseType3\":{\"id\":\"20180620094159833\"},\"division\":{\"id\":\"360000\"},\"secondUnit\":{\"name\":\"天津市消防总队\",\"id\":\"159dddd872b14d04bebab955e5bb50\"},\"thirdUnit\":{\"name\":\"天津市中队\",\"id\":\"20180427102343309\"},\"level\":\"等级1\",\"state\":\"派遣\",\"description\":\"2018年6月1日17时38分，乐平市中队接到群众报警称：位于乐平市乐港镇传芳村有人跳桥，接到报警后，中队立即出动一辆抢险救援车\",\"id\":\"20180427102343306\"}],\"exceptionString\":\"\"}";
        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
        if (resultBean.isSuccess()) {
            List<FireCaseBean> list = GsonUtil.json2List(GsonUtil.obj2String(resultBean.getResult()), FireCaseBean.class);
            listener.success(list);
        } else {
            listener.error(resultBean.getExceptionString());
        }
    }

    @Override
    public void getFireCaseByID(final ILoadingResultListener<FireCaseBean> listener, @NonNull String id) {
        String result = "{\"isSuccess\":true,\"exceptionString\":\"\"}";
        CommonResultBean resultBean = GsonUtil.json2Bean(result, CommonResultBean.class);
        if (resultBean.isSuccess()) {
            FireCaseBean fireCaseBean = GsonUtil.json2Bean(GsonUtil.obj2String(resultBean.getResult()), FireCaseBean.class);
            listener.success(fireCaseBean);
        } else {
            listener.error(resultBean.getExceptionString());
        }
    }
}
