package com.firebaselibrary.model;

import android.content.Context;

import com.firebaselibrary.bean.search.PlanExpertListBean;
import com.firebaselibrary.bean.search.PlanInfoListBean;
import com.firebaselibrary.bean.search.PlanListBean;
import com.firebaselibrary.bean.search.PlanResource;
import com.firebaselibrary.bean.search.PlanTeamListBean;
import com.firebaselibrary.bean.search.SearchDBResult;
import com.firebaselibrary.bean.search.StringDBResult;
import com.firebaselibrary.bean.search.query.PlanQuery;
import com.firebaselibrary.bean.search.query.PlanResourceQuery;
import com.firebaselibrary.constant.UrlConstant;
import com.google.gson.reflect.TypeToken;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.baselibrary.baseutil.EmptyUtil;
import common.baselibrary.baseutil.GsonUtil;
import common.networkrequestlibrary.interfaces.Error;
import common.networkrequestlibrary.interfaces.Success;
import common.networkrequestlibrary.util.HttpUtil;

public class PlanModelOfflineImpl implements PlanModel {
    @Override
    public void getPlanList(Context context, final ILoadingResultListener<List<PlanListBean>> listener, PlanQuery planQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_PLAN) + UrlConstant.GET_PLAN_LIST);
        if (planQuery.getPage() != 0)
            params.put("page", planQuery.getPage());
        if (planQuery.getPageSize() != 0)
            params.put("pageSize", planQuery.getPageSize());
        if (!EmptyUtil.isEmpty(planQuery.getName()))
            params.put("name", planQuery.getName());
        if (!EmptyUtil.isEmpty(planQuery.getType()))
            params.put("type", planQuery.getType());
        if (!EmptyUtil.isEmpty(planQuery.getEventType()))
            params.put("eventType", planQuery.getEventType());
        new HttpUtil.Builder(context)
                .url(ConfigManager.getInstance().getNetConfig(UrlConstant.RESOURCE_GET_URL) + UrlConstant.GET_RESOURCE)
                .addParams(params)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {
                        if (listener == null) {
                            return;
                        }
                        SearchDBResult<List<PlanListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                new TypeToken<SearchDBResult<List<PlanListBean>>>() {
                                }.getType());
                        if (resRsp.getData() != null)
                            listener.success(resRsp.getData().getResultList());
                    }
                })
                .error(new Error() {
                    @Override
                    public void Error(String error) {
                        listener.error(error);
                    }
                }).get();
    }

    @Override
    public void getPlanResource(Context context,final ILoadingResultListener<PlanResource> listener, PlanResourceQuery planResourceQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_PLAN) + UrlConstant.GET_PLAN_RESOURCE);
        params.put("digitalId", planResourceQuery.getDigitalId());
        new HttpUtil.Builder(context)
                .url(ConfigManager.getInstance().getNetConfig(UrlConstant.RESOURCE_GET_URL) + UrlConstant.GET_RESOURCE)
                .addParams(params)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {
                        if (listener == null) {
                            return;
                        }
                        SearchDBResult<PlanResource> resRsp = GsonUtil.createLongGson().fromJson(result,
                                new TypeToken<SearchDBResult<PlanResource>>() {
                                }.getType());
                        if (resRsp.getData() != null)
                            listener.success(resRsp.getData().getResultList());
                    }
                })
                .error(new Error() {
                    @Override
                    public void Error(String error) {
                        listener.error(error);
                    }
                }).get();
    }

    @Override
    public void getPlanDetail(Context context, final ILoadingResultListener<String> listener, String id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_PLAN) + UrlConstant.GET_PLAN_DETAIL + id);
        new HttpUtil.Builder(context)
                .url(ConfigManager.getInstance().getNetConfig(UrlConstant.RESOURCE_GET_URL) + UrlConstant.GET_RESOURCE)
                .addParams(params)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {
                        if (listener == null) {
                            return;
                        }
                        try {
                            StringDBResult resRsp = GsonUtil.createLongGson().fromJson(result,
                                    new TypeToken<StringDBResult>() {
                                    }.getType());
                            if (resRsp.getData() != null)
                                listener.success(resRsp.getData());
                        }catch (Exception e){
                            listener.success("");
                        }
                    }
                })
                .error(new Error() {
                    @Override
                    public void Error(String error) {
                        listener.error(error);
                    }
                }).get();
    }

    @Override
    public void getDownloadPlanDetail(Context context, final ILoadingResultListener<String> listener, String id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_PLAN) + UrlConstant.GET_PLAN_PDF + id);
        new HttpUtil.Builder(context)
                .url(ConfigManager.getInstance().getNetConfig(UrlConstant.RESOURCE_GET_URL) + UrlConstant.GET_RESOURCE)
                .addParams(params)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {
                        if (listener == null) {
                            return;
                        }
                        StringDBResult resRsp = GsonUtil.createLongGson().fromJson(result,
                                new TypeToken<StringDBResult>() {
                                }.getType());
                        if (resRsp.getData() != null)
                            listener.success(resRsp.getData());
                    }
                })
                .error(new Error() {
                    @Override
                    public void Error(String error) {
                        listener.error(error);
                    }
                }).get();
    }

    @Override
    public void getPlanExpertList(Context context, final ILoadingResultListener<List<PlanExpertListBean>> listener, String schemeId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_PLAN) + UrlConstant.GET_PLAN_EXPERT_LIST);
        params.put("digitalId", schemeId);
        new HttpUtil.Builder(context)
                .url(ConfigManager.getInstance().getNetConfig(UrlConstant.RESOURCE_GET_URL) + UrlConstant.GET_RESOURCE)
                .addParams(params)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {
                        if (listener == null) {
                            return;
                        }
                        try {
                            SearchDBResult<List<PlanExpertListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                    new TypeToken<SearchDBResult<List<PlanExpertListBean>>>() {
                                    }.getType());
                            if (resRsp.getData() != null)
                                listener.success(resRsp.getData().getResultList());
                            else
                                listener.success(null);
                        } catch (Exception e) {
                            listener.success(null);
                        }
                    }
                })
                .error(new Error() {
                    @Override
                    public void Error(String error) {
                        listener.error(error);
                    }
                }).get();
    }

    @Override
    public void getPlanInfoList(Context context, final ILoadingResultListener<List<PlanInfoListBean>> listener, String schemeId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_PLAN) + UrlConstant.GET_PLAN_NODE_LIST);
        params.put("digitalId", schemeId);
        new HttpUtil.Builder(context)
                .url(ConfigManager.getInstance().getNetConfig(UrlConstant.RESOURCE_GET_URL) + UrlConstant.GET_RESOURCE)
                .addParams(params)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {
                        if (listener == null) {
                            return;
                        }
                        try {
                            SearchDBResult<List<PlanInfoListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                    new TypeToken<SearchDBResult<List<PlanInfoListBean>>>() {
                                    }.getType());
                            if (resRsp.getData() != null)
                                listener.success(resRsp.getData().getResultList());
                            else
                                listener.success(null);
                        } catch (Exception e) {
                            listener.success(null);
                        }
                    }
                })
                .error(new Error() {
                    @Override
                    public void Error(String error) {
                        listener.error(error);
                    }
                }).get();
    }

    @Override
    public void getPlanTeamList(Context context, final ILoadingResultListener<List<PlanTeamListBean>> listener, String schemeId) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_PLAN) + UrlConstant.GET_PLAN_TEAM_LIST);
        params.put("digitalId", schemeId);
        new HttpUtil.Builder(context)
                .url(ConfigManager.getInstance().getNetConfig(UrlConstant.RESOURCE_GET_URL) + UrlConstant.GET_RESOURCE)
                .addParams(params)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {
                        if (listener == null) {
                            return;
                        }
                        try {
                            SearchDBResult<List<PlanTeamListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                    new TypeToken<SearchDBResult<List<PlanTeamListBean>>>() {
                                    }.getType());
                            if (resRsp.getData() != null)
                                listener.success(resRsp.getData().getResultList());
                            else
                                listener.success(null);
                        } catch (Exception e) {
                            listener.success(null);
                        }
                    }
                })
                .error(new Error() {
                    @Override
                    public void Error(String error) {
                        listener.error(error);
                    }
                }).get();
    }

    @Override
    public void getPlanCommentList(Context context, String kid, OnResultCallBack callback) {
        CreParamsList creParams = new CreParamsList("jczc", "knowledge_dp_yuan", CreTableType.Table)
                .setOrder("time")
                .setSort(CreSortType.DESC)
                .setPage(1,10)
                .addConditions(new Conditions("kid", CreOperType.EQ, kid));
        HttpUtils.getInstance().setBaseUrl(ConfigManager.getInstance().getNetConfig(com.firebaselibrary.constant.UrlConstant.API_BASE_CONSTANT) + "/")
                .creGetRequest(ConstantsBase.BASE_GETLIST, creParams.buildRequestParam(), callback);
    }

    @Override
    public void sendPlanComment(Context context, Map<String, Object> params, OnResultCallBack callback) {
        CreParamsObject creParams = new CreParamsObject("jczc", "knowledge_dp_yuan", CreWorkType.Save, params);
        HttpUtils.getInstance().setBaseUrl(ConfigManager.getInstance().getNetConfig(com.firebaselibrary.constant.UrlConstant.API_BASE_CONSTANT) + "/")
                .creJsonRequest(ConstantsBase.BASE_EXECUTE, creParams, null, callback);
    }
}
