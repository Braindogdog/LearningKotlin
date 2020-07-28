package com.firebaselibrary.model;

import android.content.Context;

import com.firebaselibrary.bean.search.PlanExpertListBean;
import com.firebaselibrary.bean.search.PlanInfoListBean;
import com.firebaselibrary.bean.search.PlanListBean;
import com.firebaselibrary.bean.search.PlanResource;
import com.firebaselibrary.bean.search.PlanTeamListBean;
import com.firebaselibrary.bean.search.query.PlanQuery;
import com.firebaselibrary.bean.search.query.PlanResourceQuery;
import com.krx.ydzh.commoncore.basequery.Conditions;
import com.krx.ydzh.commoncore.basequery.ConstantsBase;
import com.krx.ydzh.commoncore.basequery.CreOperType;
import com.krx.ydzh.commoncore.basequery.CreParamsList;
import com.krx.ydzh.commoncore.basequery.CreParamsObject;
import com.krx.ydzh.commoncore.basequery.CreSortType;
import com.krx.ydzh.commoncore.basequery.CreTableType;
import com.krx.ydzh.commoncore.basequery.CreWorkType;
import com.krx.ydzh.commoncore.config.ConfigManager;
import com.krx.ydzh.commoncore.net.HttpUtils;
import com.krx.ydzh.commoncore.net.callback.OnResultCallBack;

import java.util.List;
import java.util.Map;

public class PlanModelImpl implements PlanModel {
    @Override
    public void getPlanList(Context context, ILoadingResultListener<List<PlanListBean>> listener, PlanQuery planQuery) {

    }

    @Override
    public void getPlanResource(Context context, ILoadingResultListener<PlanResource> listener, PlanResourceQuery planResourceQuery) {

    }

    @Override
    public void getPlanDetail(Context context, ILoadingResultListener<String> listener, String id) {

    }

    @Override
    public void getDownloadPlanDetail(Context context, ILoadingResultListener<String> listener, String id) {

    }

    @Override
    public void getPlanExpertList(Context context, ILoadingResultListener<List<PlanExpertListBean>> listener, String schemeId) {

    }

    @Override
    public void getPlanInfoList(Context context, ILoadingResultListener<List<PlanInfoListBean>> listener, String schemeId) {

    }

    @Override
    public void getPlanTeamList(Context context, ILoadingResultListener<List<PlanTeamListBean>> listener, String schemeId) {

    }

    @Override
    public void getPlanCommentList(Context context, String kid, OnResultCallBack callback) {



    }

    @Override
    public void sendPlanComment(Context context, Map<String, Object> params, OnResultCallBack callback) {

    }
}
