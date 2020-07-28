package com.firebaselibrary.model;

import android.content.Context;
import android.util.Log;

import com.firebaselibrary.bean.AreaBean;
import com.firebaselibrary.bean.search.AimListBean;
import com.firebaselibrary.bean.search.CommunicationListBean;
import com.firebaselibrary.bean.search.CompanyListBean;
import com.firebaselibrary.bean.search.DangerListBean;
import com.firebaselibrary.bean.search.DeviceGps;
import com.firebaselibrary.bean.search.DeviceGpsArgs;
import com.firebaselibrary.bean.search.ExpertListBean;
import com.firebaselibrary.bean.search.GoodsListBean;
import com.firebaselibrary.bean.search.GoodsStoreListBean;
import com.firebaselibrary.bean.search.MedicalListBean;
import com.firebaselibrary.bean.search.ResTypeListBean;
import com.firebaselibrary.bean.search.SearchDBResult;
import com.firebaselibrary.bean.search.ShelterListBean;
import com.firebaselibrary.bean.search.TeamListBean;
import com.firebaselibrary.bean.search.TransportListBean;
import com.firebaselibrary.bean.search.dispatch.TeamListDispatchBean;
import com.firebaselibrary.bean.search.query.AimQuery;
import com.firebaselibrary.bean.search.query.CommunicationQuery;
import com.firebaselibrary.bean.search.query.CompanyQuery;
import com.firebaselibrary.bean.search.query.DangerQuery;
import com.firebaselibrary.bean.search.query.ExpertQuery;
import com.firebaselibrary.bean.search.query.GisQuery;
import com.firebaselibrary.bean.search.query.GoodsQuery;
import com.firebaselibrary.bean.search.query.GoodsStoreQuery;
import com.firebaselibrary.bean.search.query.MedicalQuery;
import com.firebaselibrary.bean.search.query.ResTypeQuery;
import com.firebaselibrary.bean.search.query.ShelterQuery;
import com.firebaselibrary.bean.search.query.TeamQuery;
import com.firebaselibrary.bean.search.query.TransportQuery;
import com.firebaselibrary.constant.UrlConstant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.krx.ydzh.commoncore.config.ConfigManager;

import org.creation.common.models.IBaseResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.baselibrary.baseutil.EmptyUtil;
import common.baselibrary.baseutil.GsonUtil;
import common.networkrequestlibrary.interfaces.Error;
import common.networkrequestlibrary.interfaces.Success;
import common.networkrequestlibrary.util.HttpUtil;
import common.networkrequestlibrary.util.NewDBResult;

public class SearchResourceModelImpl implements SearchResourceModel {
    @Override
    public void getTeamList(Context context, final ILoadingResultListener<List<TeamListBean>> listener, TeamQuery teamQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_TEAM_LIST);
        params.put("page", teamQuery.getPage());
        params.put("pageSize", teamQuery.getPageSize());
        if (!EmptyUtil.isEmpty(teamQuery.getTeamName()))
            params.put("teamName", teamQuery.getTeamName());
        if (!EmptyUtil.isEmpty(teamQuery.getId()))
            params.put("id", teamQuery.getId());
        if (!EmptyUtil.isEmpty(teamQuery.getTeamCode()))
            params.put("teamCode", teamQuery.getTeamCode());
        if (!EmptyUtil.isEmpty(teamQuery.getAreaCode()))
            params.put("areaCode", teamQuery.getAreaCode());
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
                            SearchDBResult<List<TeamListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                    new TypeToken<SearchDBResult<List<TeamListBean>>>() {
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
    public void getExpertList(Context context, final ILoadingResultListener<List<ExpertListBean>> listener, ExpertQuery expertQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_EXPERT_LIST);
        params.put("page", expertQuery.getPage());
        params.put("pageSize", expertQuery.getPageSize());
        if (!EmptyUtil.isEmpty(expertQuery.getExpertName()))
            params.put("expertName", expertQuery.getExpertName());
        if (!EmptyUtil.isEmpty(expertQuery.getAreaCode()))
            params.put("areaCode", expertQuery.getAreaCode());
        if (!EmptyUtil.isEmpty(expertQuery.getExpertCode()))
            params.put("expertCode", expertQuery.getExpertCode());
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
                            SearchDBResult<List<ExpertListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                    new TypeToken<SearchDBResult<List<ExpertListBean>>>() {
                                    }.getType());
                            if (resRsp.getData() != null)
                                listener.success(resRsp.getData().getResultList());
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
    public void getDangerList(Context context, final ILoadingResultListener<List<DangerListBean>> listener, DangerQuery dangerQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_DANGER_LIST);
        params.put("page", dangerQuery.getPage());
        params.put("pageSize", dangerQuery.getPageSize());
        if (!EmptyUtil.isEmpty(dangerQuery.getDangerName()))
            params.put("dangerName", dangerQuery.getDangerName());
        if (!EmptyUtil.isEmpty(dangerQuery.getId()))
            params.put("id", dangerQuery.getId());
        if (!EmptyUtil.isEmpty(dangerQuery.getAreaCode()))
            params.put("areaCode", dangerQuery.getAreaCode());
        if (!EmptyUtil.isEmpty(dangerQuery.getDangerLevel()))
            params.put("dangerLevel", dangerQuery.getDangerLevel());
        if (!EmptyUtil.isEmpty(dangerQuery.getDangerCode()))
            params.put("dangerCode", dangerQuery.getDangerCode());
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
                            SearchDBResult<List<DangerListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                    new TypeToken<SearchDBResult<List<DangerListBean>>>() {
                                    }.getType());
                            if (resRsp.getData() != null)
                                listener.success(resRsp.getData().getResultList());
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
    public void getAimList(Context context, final ILoadingResultListener<List<AimListBean>> listener, AimQuery aimQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_AIM_LIST);
        params.put("page", aimQuery.getPage());
        params.put("pageSize", aimQuery.getPageSize());
        if (!EmptyUtil.isEmpty(aimQuery.getAimName()))
            params.put("aimName", aimQuery.getAimName());
        if (!EmptyUtil.isEmpty(aimQuery.getAreaCode()))
            params.put("areaCode", aimQuery.getAreaCode());
        if (!EmptyUtil.isEmpty(aimQuery.getAimLevel()))
            params.put("aimLevel", aimQuery.getAimLevel());
        if (!EmptyUtil.isEmpty(aimQuery.getAimCode()))
            params.put("aimCode", aimQuery.getAimCode());
        if (!EmptyUtil.isEmpty(aimQuery.getId()))
            params.put("id", aimQuery.getId());
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
                            SearchDBResult<List<AimListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                    new TypeToken<SearchDBResult<List<AimListBean>>>() {
                                    }.getType());
                            if (resRsp.getData() != null)
                                listener.success(resRsp.getData().getResultList());
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
    public void getShelterList(Context context, final ILoadingResultListener<List<ShelterListBean>> listener, ShelterQuery shelterQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_SHELTER_LIST);
        params.put("page", shelterQuery.getPage());
        params.put("pageSize", shelterQuery.getPageSize());
        if (!EmptyUtil.isEmpty(shelterQuery.getShelterName()))
            params.put("shelterName", shelterQuery.getShelterName());
        if (!EmptyUtil.isEmpty(shelterQuery.getShelterCode()))
            params.put("shelterCode", shelterQuery.getShelterCode());
        if (!EmptyUtil.isEmpty(shelterQuery.getAreaCode()))
            params.put("areaCode", shelterQuery.getAreaCode());
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
                            SearchDBResult<List<ShelterListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                    new TypeToken<SearchDBResult<List<ShelterListBean>>>() {
                                    }.getType());
                            if (resRsp.getData() != null)
                                listener.success(resRsp.getData().getResultList());
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
    public void getCompanyList(Context context, final ILoadingResultListener<List<CompanyListBean>> listener, CompanyQuery companyQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_COMPANY_LIST);
        params.put("page", companyQuery.getPage());
        params.put("pageSize", companyQuery.getPageSize());
        if (!EmptyUtil.isEmpty(companyQuery.getCompanyName()))
            params.put("companyName", companyQuery.getCompanyName());
        if (!EmptyUtil.isEmpty(companyQuery.getCompanyCode()))
            params.put("companyCode", companyQuery.getCompanyCode());
        if (!EmptyUtil.isEmpty(companyQuery.getAreaCode()))
            params.put("areaCode", companyQuery.getAreaCode());
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
                            SearchDBResult<List<CompanyListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                    new TypeToken<SearchDBResult<List<CompanyListBean>>>() {
                                    }.getType());
                            if (resRsp.getData() != null)
                                listener.success(resRsp.getData().getResultList());
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
    public void getGoodsList(Context context, final ILoadingResultListener<List<GoodsListBean>> listener, GoodsQuery goodsQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_GOODS_LIST);
        params.put("page", goodsQuery.getPage());
        params.put("pageSize", goodsQuery.getPageSize());
        if (!EmptyUtil.isEmpty(goodsQuery.getGoodsName()))
            params.put("goodsName", goodsQuery.getGoodsName());
        if (!EmptyUtil.isEmpty(goodsQuery.getResTypeCode()))
            params.put("resTypeCode", goodsQuery.getResTypeCode());
        if (!EmptyUtil.isEmpty(goodsQuery.getAreaCode()))
            params.put("areaCode", goodsQuery.getAreaCode());
        if (!EmptyUtil.isEmpty(goodsQuery.getId()))
            params.put("id", goodsQuery.getId());
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
                            SearchDBResult<List<GoodsListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                    new TypeToken<SearchDBResult<List<GoodsListBean>>>() {
                                    }.getType());
                            if (resRsp.getData() != null)
                                listener.success(resRsp.getData().getResultList());
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
    public void getGoodsStoreList(Context context, final ILoadingResultListener<List<GoodsStoreListBean>> listener, GoodsStoreQuery goodsQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_GOODS_STORE_LIST);
        params.put("page", goodsQuery.getPage());
        params.put("pageSize", goodsQuery.getPageSize());
        if (!EmptyUtil.isEmpty(goodsQuery.getStoreName()))
            params.put("storeName", goodsQuery.getStoreName());
        if (!EmptyUtil.isEmpty(goodsQuery.getStoreCode()))
            params.put("storeCode", goodsQuery.getStoreCode());
        if (!EmptyUtil.isEmpty(goodsQuery.getAreaCode()))
            params.put("areaCode", goodsQuery.getAreaCode());
        if (!EmptyUtil.isEmpty(goodsQuery.getId()))
            params.put("id", goodsQuery.getId());
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
                            SearchDBResult<List<GoodsStoreListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                    new TypeToken<SearchDBResult<List<GoodsStoreListBean>>>() {
                                    }.getType());
                            if (resRsp.getData() != null)
                                listener.success(resRsp.getData().getResultList());
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
    public void getResTypeList(Context context, final ILoadingResultListener<List<ResTypeListBean>> listener, ResTypeQuery resTypeQuery) {
        new HttpUtil.Builder(context)
                .url(ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_RESTYPE_LIST)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {
                        if (listener == null) {
                            return;
                        }
                        SearchDBResult<List<ResTypeListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                new TypeToken<SearchDBResult<List<ResTypeListBean>>>() {
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
    public void getCommunicationList(Context context, final ILoadingResultListener<List<CommunicationListBean>> listener, CommunicationQuery communicationQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_COMMUNICATION_LIST);
        params.put("page", communicationQuery.getPage());
        params.put("pageSize", communicationQuery.getPageSize());
        if (!EmptyUtil.isEmpty(communicationQuery.getCommunicationName()))
            params.put("communicationName", communicationQuery.getCommunicationName());
        if (!EmptyUtil.isEmpty(communicationQuery.getCommunicationCode()))
            params.put("communicationCode", communicationQuery.getCommunicationName());
        if (!EmptyUtil.isEmpty(communicationQuery.getAreaCode()))
            params.put("areaCode", communicationQuery.getAreaCode());
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
                            SearchDBResult<List<CommunicationListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                    new TypeToken<SearchDBResult<List<CommunicationListBean>>>() {
                                    }.getType());
                            if (resRsp.getData() != null)
                                listener.success(resRsp.getData().getResultList());
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
    public void getTransportList(Context context, final ILoadingResultListener<List<TransportListBean>> listener, TransportQuery transportQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_TRANSPORT_LIST);
        params.put("page", transportQuery.getPage());
        params.put("pageSize", transportQuery.getPageSize());
        if (!EmptyUtil.isEmpty(transportQuery.getTransportName()))
            params.put("transportName", transportQuery.getTransportName());
        if (!EmptyUtil.isEmpty(transportQuery.getTransportCode()))
            params.put("transportCode", transportQuery.getTransportCode());
        if (!EmptyUtil.isEmpty(transportQuery.getAreaCode()))
            params.put("areaCode", transportQuery.getAreaCode());
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
                            SearchDBResult<List<TransportListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                    new TypeToken<SearchDBResult<List<TransportListBean>>>() {
                                    }.getType());
                            if (resRsp.getData() != null)
                                listener.success(resRsp.getData().getResultList());
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
    public void getMedicalList(Context context, final ILoadingResultListener<List<MedicalListBean>> listener, MedicalQuery medicalQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_MEDICAL_LIST);
        params.put("page", medicalQuery.getPage());
        params.put("pageSize", medicalQuery.getPageSize());
        if (!EmptyUtil.isEmpty(medicalQuery.getMedicalName()))
            params.put("medicalName", medicalQuery.getMedicalName());
        if (!EmptyUtil.isEmpty(medicalQuery.getMedicalCode()))
            params.put("medicalCode", medicalQuery.getMedicalCode());
        if (!EmptyUtil.isEmpty(medicalQuery.getAreaCode()))
            params.put("areaCode", medicalQuery.getAreaCode());
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
                            SearchDBResult<List<MedicalListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                    new TypeToken<SearchDBResult<List<MedicalListBean>>>() {
                                    }.getType());
                            if (resRsp.getData() != null)
                                listener.success(resRsp.getData().getResultList());
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
    public void getTeamListPoint(Context context, final ILoadingResultListener<List<TeamListBean>> listener, GisQuery gisQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_GIS_RESOURCE);
        params.put("longitude", gisQuery.getLongitude());
        params.put("latitude", gisQuery.getLatitude());
        params.put("radius", gisQuery.getRadius());
        params.put("page", gisQuery.getPage());
        params.put("pageSize", gisQuery.getPageSize());
        params.put("tableNameType", "team");
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
                        SearchDBResult<List<TeamListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                new TypeToken<SearchDBResult<List<TeamListBean>>>() {
                                }.getType());
                        if (resRsp.getData() != null)
                            listener.success(resRsp.getData().getResultList());
                        else
                            listener.success(null);
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
    public void getExpertListPoint(Context context, final ILoadingResultListener<List<ExpertListBean>> listener, GisQuery gisQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_GIS_RESOURCE);
        params.put("longitude", gisQuery.getLongitude());
        params.put("latitude", gisQuery.getLatitude());
        params.put("radius", gisQuery.getRadius());
        params.put("page", gisQuery.getPage());
        params.put("pageSize", gisQuery.getPageSize());
        params.put("tableNameType", "expert");
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
                        SearchDBResult<List<ExpertListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                new TypeToken<SearchDBResult<List<ExpertListBean>>>() {
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
    public void getDangerListPoint(Context context, final ILoadingResultListener<List<DangerListBean>> listener, GisQuery gisQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_GIS_RESOURCE);
        params.put("longitude", gisQuery.getLongitude());
        params.put("latitude", gisQuery.getLatitude());
        params.put("radius", gisQuery.getRadius());
        params.put("page", gisQuery.getPage());
        params.put("pageSize", gisQuery.getPageSize());
        params.put("tableNameType", "danger");
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
                        SearchDBResult<List<DangerListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                new TypeToken<SearchDBResult<List<DangerListBean>>>() {
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
    public void getAimListPoint(Context context, final ILoadingResultListener<List<AimListBean>> listener, GisQuery gisQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_GIS_RESOURCE);
        params.put("longitude", gisQuery.getLongitude());
        params.put("latitude", gisQuery.getLatitude());
        params.put("radius", gisQuery.getRadius());
        params.put("page", gisQuery.getPage());
        params.put("pageSize", gisQuery.getPageSize());
        params.put("tableNameType", "aim");
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
                        SearchDBResult<List<AimListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                new TypeToken<SearchDBResult<List<AimListBean>>>() {
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
    public void getShelterListPoint(Context context, final ILoadingResultListener<List<ShelterListBean>> listener, GisQuery gisQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_GIS_RESOURCE);
        params.put("longitude", gisQuery.getLongitude());
        params.put("latitude", gisQuery.getLatitude());
        params.put("radius", gisQuery.getRadius());
        params.put("page", gisQuery.getPage());
        params.put("pageSize", gisQuery.getPageSize());
        params.put("tableNameType", "shelter");
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
                        SearchDBResult<List<ShelterListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                new TypeToken<SearchDBResult<List<ShelterListBean>>>() {
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
    public void getCompanyListPoint(Context context, final ILoadingResultListener<List<CompanyListBean>> listener, GisQuery gisQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_GIS_RESOURCE);
        params.put("longitude", gisQuery.getLongitude());
        params.put("latitude", gisQuery.getLatitude());
        params.put("radius", gisQuery.getRadius());
        params.put("page", gisQuery.getPage());
        params.put("pageSize", gisQuery.getPageSize());
        params.put("tableNameType", "company");
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
                        SearchDBResult<List<CompanyListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                new TypeToken<SearchDBResult<List<CompanyListBean>>>() {
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
    public void getGoodsListPoint(Context context, final ILoadingResultListener<List<GoodsListBean>> listener, GisQuery gisQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_GIS_RESOURCE);
        params.put("longitude", gisQuery.getLongitude());
        params.put("latitude", gisQuery.getLatitude());
        params.put("radius", gisQuery.getRadius());
        params.put("page", gisQuery.getPage());
        params.put("pageSize", gisQuery.getPageSize());
        params.put("tableNameType", "goods");
        new HttpUtil.Builder(context)
                .url(ConfigManager.getInstance().getNetConfig(UrlConstant.RESOURCE_GET_URL) + UrlConstant.GET_RESOURCE)
                .addParams(params)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {
                        Log.e("111111111111111", "222222222222222222222222");
                        try {
                            if (listener == null) {
                                return;
                            }
                            SearchDBResult<List<GoodsListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                    new TypeToken<SearchDBResult<List<GoodsListBean>>>() {
                                    }.getType());
                            if (resRsp.getData() != null)
                                listener.success(resRsp.getData().getResultList());
                        } catch (Exception e) {
                            Log.e("111111111111111", "111111111111111111111");
                            listener.error(e.getMessage());

                        }
                    }
                })
                .error(new Error() {
                    @Override
                    public void Error(String error) {
                        Log.e("111111111111111", "333333333333333333333333");
                        listener.error(error);
                    }
                }).get();
    }

    @Override
    public void getGoodsStoreListPoint(Context context, final ILoadingResultListener<List<GoodsStoreListBean>> listener, GisQuery gisQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_GIS_RESOURCE);
        params.put("longitude", gisQuery.getLongitude());
        params.put("latitude", gisQuery.getLatitude());
        params.put("radius", gisQuery.getRadius());
        params.put("page", gisQuery.getPage());
        params.put("pageSize", gisQuery.getPageSize());
        params.put("tableNameType", "store");
        new HttpUtil.Builder(context)
                .url(ConfigManager.getInstance().getNetConfig(UrlConstant.RESOURCE_GET_URL) + UrlConstant.GET_RESOURCE)
                .addParams(params)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {
                        try {

                            if (listener == null) {
                                return;
                            }
                            SearchDBResult<List<GoodsStoreListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                    new TypeToken<SearchDBResult<List<GoodsStoreListBean>>>() {
                                    }.getType());
                            if (resRsp.getData() != null)
                                listener.success(resRsp.getData().getResultList());
                        } catch (Exception e) {

                            listener.error(e.getMessage());
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
    public void getCommunicationListPoint(Context context, final ILoadingResultListener<List<CommunicationListBean>> listener, GisQuery gisQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_GIS_RESOURCE);
        params.put("longitude", gisQuery.getLongitude());
        params.put("latitude", gisQuery.getLatitude());
        params.put("radius", gisQuery.getRadius());
        params.put("page", gisQuery.getPage());
        params.put("pageSize", gisQuery.getPageSize());
        params.put("tableNameType", "communication");
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
                        SearchDBResult<List<CommunicationListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                new TypeToken<SearchDBResult<List<CommunicationListBean>>>() {
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
    public void getTransportListPoint(Context context, final ILoadingResultListener<List<TransportListBean>> listener, GisQuery gisQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_GIS_RESOURCE);
        params.put("longitude", gisQuery.getLongitude());
        params.put("latitude", gisQuery.getLatitude());
        params.put("radius", gisQuery.getRadius());
        params.put("page", gisQuery.getPage());
        params.put("pageSize", gisQuery.getPageSize());
        params.put("tableNameType", "transport");
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
                        SearchDBResult<List<TransportListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                new TypeToken<SearchDBResult<List<TransportListBean>>>() {
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
    public void getMedicalListPoint(Context context, final ILoadingResultListener<List<MedicalListBean>> listener, GisQuery gisQuery) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_GIS_RESOURCE);
        params.put("longitude", gisQuery.getLongitude());
        params.put("latitude", gisQuery.getLatitude());
        params.put("radius", gisQuery.getRadius());
        params.put("page", gisQuery.getPage());
        params.put("pageSize", gisQuery.getPageSize());
        params.put("tableNameType", "medical");
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
                        SearchDBResult<List<MedicalListBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                new TypeToken<SearchDBResult<List<MedicalListBean>>>() {
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
    public void getAreaList(Context context, final ILoadingResultListener<List<AreaBean>> listener) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", ConfigManager.getInstance().getNetConfig(UrlConstant.SEARCH_RESOURCE) + UrlConstant.GET_AREA);
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
                        SearchDBResult<List<AreaBean>> resRsp = GsonUtil.createLongGson().fromJson(result,
                                new TypeToken<SearchDBResult<List<AreaBean>>>() {
                                }.getType());
                        if (resRsp != null && resRsp.getData() != null)
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
    public void getDeviceWhereList(Context context, final ILoadingResultListener<List<DeviceGps>> listener, DeviceGpsArgs args) {
        new HttpUtil.Builder(context)
                .url(ConfigManager.getInstance().getNetConfig(UrlConstant.API_EMERGENCY_SERVER_URL) + UrlConstant.DEVICE_WHERE)
                .build()
                .success(new Success() {
                    @Override
                    public void Success(String result) {
                        if (listener == null) {
                            return;
                        }
                        NewDBResult<List<DeviceGps>> jsonResult = new Gson().fromJson(result,
                                new TypeToken<NewDBResult<List<DeviceGps>>>() {
                                }.getType());

                        if (jsonResult.getCode() == 200) {
                            listener.success(jsonResult.getResult());
                        } else {
                            listener.error(jsonResult.getMsg());
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
