package com.firebaselibrary.model;

import android.content.Context;

import com.firebaselibrary.bean.search.PlanExpertListBean;
import com.firebaselibrary.bean.search.PlanInfoListBean;
import com.firebaselibrary.bean.search.PlanListBean;
import com.firebaselibrary.bean.search.PlanResource;
import com.firebaselibrary.bean.search.PlanTeamListBean;
import com.firebaselibrary.bean.search.query.PlanQuery;
import com.firebaselibrary.bean.search.query.PlanResourceQuery;
import com.krx.ydzh.commoncore.net.callback.OnResultCallBack;

import java.util.List;
import java.util.Map;

/**
 * 预案
 */
public interface PlanModel {
    /**
     * 获取预案列表
     *
     * @param context
     * @param listener
     * @param planQuery
     */
    void getPlanList(Context context, ILoadingResultListener<List<PlanListBean>> listener, PlanQuery planQuery);

    /**
     * 获取预案列表
     *
     * @param context
     * @param listener
     * @param planResourceQuery
     */
    void getPlanResource(Context context, ILoadingResultListener<PlanResource> listener, PlanResourceQuery planResourceQuery);

    /**
     * 获取预案详情
     *
     * @param context
     * @param listener
     * @param id
     */
    void getPlanDetail(Context context, ILoadingResultListener<String> listener, String id);

    /**
     * 获取预案pdf下载地址
     *
     * @param context
     * @param listener
     * @param id
     */
    void getDownloadPlanDetail(Context context, ILoadingResultListener<String> listener, String id);

    /**
     * 获取预案专家列表
     *
     * @param context
     * @param listener
     * @param schemeId
     */
    void getPlanExpertList(Context context, ILoadingResultListener<List<PlanExpertListBean>> listener, String schemeId);

    /**
     * 获取预案机构列表
     *
     * @param context
     * @param listener
     * @param schemeId
     */
    void getPlanInfoList(Context context, ILoadingResultListener<List<PlanInfoListBean>> listener, String schemeId);

    /**
     * 获取预案队伍列表
     *
     * @param context
     * @param listener
     * @param schemeId
     */
    void getPlanTeamList(Context context, ILoadingResultListener<List<PlanTeamListBean>> listener, String schemeId);

    /**
     * 预案评论列表
     */
    void getPlanCommentList(Context context,String kid, OnResultCallBack callback);

    /**
     * 预案发送评论
     */
    void sendPlanComment(Context context, Map<String, Object> params, OnResultCallBack callback);
}
