package com.firebaselibrary.model;

import android.content.Context;

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

import java.util.List;

public interface SearchResourceModel {
    /**
     * 查询救援队伍
     * @param context
     * @param listener
     */
    void getTeamList(Context context, ILoadingResultListener<List<TeamListBean>> listener, TeamQuery teamQuery);

    /**
     * 专家查询
     * @param context
     * @param listener
     * @param expertQuery
     */
    void getExpertList(Context context, ILoadingResultListener<List<ExpertListBean>> listener, ExpertQuery expertQuery);

    /**
     * 危险源查询
      * @param context
     * @param listener
     * @param dangerQuery
     */
    void getDangerList(Context context, ILoadingResultListener<List<DangerListBean>> listener, DangerQuery dangerQuery);

    /**
     * 重点防护目标查询
     * @param context
     * @param listener
     * @param aimQuery
     */
    void getAimList(Context context, ILoadingResultListener<List<AimListBean>> listener, AimQuery aimQuery);

    /**
     * 避难场所查询
     * @param context
     * @param listener
     * @param shelterQuery
     */
    void getShelterList(Context context, ILoadingResultListener<List<ShelterListBean>> listener, ShelterQuery shelterQuery);

    /**
     * 物资企业查询
     * @param context
     * @param listener
     * @param companyQuery
     */
    void getCompanyList(Context context, ILoadingResultListener<List<CompanyListBean>> listener, CompanyQuery companyQuery);

    /**
     * 应急物资查询
     * @param context
     * @param listener
     * @param goodsQuery
     */
    void getGoodsList(Context context, ILoadingResultListener<List<GoodsListBean>> listener, GoodsQuery goodsQuery);

    /**
     * 物资仓库查询
     * @param context
     * @param listener
     * @param goodsQuery
     */
    void getGoodsStoreList(Context context, ILoadingResultListener<List<GoodsStoreListBean>> listener, GoodsStoreQuery goodsQuery);

    /**
     * 物资类型资源树查询
     * @param context
     * @param listener
     * @param resTypeQuery
     */
    void getResTypeList(Context context, ILoadingResultListener<List<ResTypeListBean>> listener, ResTypeQuery resTypeQuery);

    /**
     * 通讯保障查询
     * @param context
     * @param listener
     * @param communicationQuery
     */
    void getCommunicationList(Context context, ILoadingResultListener<List<CommunicationListBean>> listener, CommunicationQuery communicationQuery);

    /**
     * 运输资源管理查询
     * @param context
     * @param listener
     * @param transportQuery
     */
    void getTransportList(Context context, ILoadingResultListener<List<TransportListBean>> listener, TransportQuery transportQuery);

    /**
     * 医疗卫生资源查询
     * @param context
     * @param listener
     * @param medicalQuery
     */
    void getMedicalList(Context context, ILoadingResultListener<List<MedicalListBean>> listener, MedicalQuery medicalQuery);


    /**
     * 查询救援队伍
     * @param context
     * @param listener
     */
    void getTeamListPoint(Context context, ILoadingResultListener<List<TeamListBean>> listener, GisQuery gisQuery);

    /**
     * 专家查询
     * @param context
     * @param listener
     */
    void getExpertListPoint(Context context, ILoadingResultListener<List<ExpertListBean>> listener, GisQuery gisQuery);

    /**
     * 危险源查询
     * @param context
     * @param listener
     */
    void getDangerListPoint(Context context, ILoadingResultListener<List<DangerListBean>> listener, GisQuery gisQuery);

    /**
     * 重点防护目标查询
     * @param context
     * @param listener
     */
    void getAimListPoint(Context context, ILoadingResultListener<List<AimListBean>> listener, GisQuery gisQuery);

    /**
     * 避难场所查询
     * @param context
     * @param listener
     */
    void getShelterListPoint(Context context, ILoadingResultListener<List<ShelterListBean>> listener, GisQuery gisQuery);

    /**
     * 物资企业查询
     * @param context
     * @param listener
     */
    void getCompanyListPoint(Context context, ILoadingResultListener<List<CompanyListBean>> listener, GisQuery gisQuery);

    /**
     * 应急物资查询
     * @param context
     * @param listener
     */
    void getGoodsListPoint(Context context, ILoadingResultListener<List<GoodsListBean>> listener, GisQuery gisQuery);

    /**
     * 物资仓库查询
     * @param context
     * @param listener
     */
    void getGoodsStoreListPoint(Context context, ILoadingResultListener<List<GoodsStoreListBean>> listener, GisQuery gisQuery);

    /**
     * 通讯保障查询
     * @param context
     * @param listener
     */
    void getCommunicationListPoint(Context context, ILoadingResultListener<List<CommunicationListBean>> listener, GisQuery gisQuery);

    /**
     * 运输资源管理查询
     * @param context
     * @param listener
     */
    void getTransportListPoint(Context context, ILoadingResultListener<List<TransportListBean>> listener, GisQuery gisQuery);

    /**
     * 医疗卫生资源查询
     * @param context
     * @param listener
     */
    void getMedicalListPoint(Context context, ILoadingResultListener<List<MedicalListBean>> listener, GisQuery gisQuery);

    /**
     * 区域资源查询
     * @param context
     * @param listener
     */
    void getAreaList(Context context, ILoadingResultListener<List<AreaBean>> listener);

    /**
     * 查询设备分布
     * @param context
     * @param listener
     */
    void getDeviceWhereList(Context context, ILoadingResultListener<List<DeviceGps>> listener, DeviceGpsArgs args);

}
