package com.firebaselibrary.model;

import android.content.Context;

import com.firebaselibrary.bean.query.DispatchQuery;
import com.firebaselibrary.bean.query.DispatchTypeEnum;
import com.firebaselibrary.bean.search.CommunicationListBean;
import com.firebaselibrary.bean.search.CompanyListBean;
import com.firebaselibrary.bean.search.ExpertListBean;
import com.firebaselibrary.bean.search.GoodsListBean;
import com.firebaselibrary.bean.search.MedicalListBean;
import com.firebaselibrary.bean.search.TeamListBean;
import com.firebaselibrary.bean.search.TransportListBean;
import com.firebaselibrary.bean.search.dispatch.CommunicationListDisPatchBean;
import com.firebaselibrary.bean.search.dispatch.CompanyListDispatchBean;
import com.firebaselibrary.bean.search.dispatch.DispatchActionVM;
import com.firebaselibrary.bean.search.dispatch.ExpertListDispatchBean;
import com.firebaselibrary.bean.search.dispatch.GoodsListDispatchBean;
import com.firebaselibrary.bean.search.dispatch.MedicalListDispatchBean;
import com.firebaselibrary.bean.search.dispatch.TeamListDispatchBean;
import com.firebaselibrary.bean.search.dispatch.TransportListDispatchBean;
import com.firebaselibrary.bean.search.query.CommunicationQuery;
import com.firebaselibrary.bean.search.query.CompanyQuery;
import com.firebaselibrary.bean.search.query.ExpertQuery;
import com.firebaselibrary.bean.search.query.GoodsQuery;
import com.firebaselibrary.bean.search.query.MedicalQuery;
import com.firebaselibrary.bean.search.query.TeamQuery;
import com.firebaselibrary.bean.search.query.TransportQuery;

import java.util.List;

/**
 * @author Zyq
 * @date 2020/3/6
 */
public interface SearchCommandResourceModel {
    /**
     * 查询救援队伍
     *
     * @param context
     * @param listener
     */
    void getTeamList(Context context, ILoadingResultListener<List<TeamListBean>> listener, DispatchQuery teamQuery);

    /**
     * 查询物资
     *
     * @param context
     * @param listener
     */
    void getGoodsList(Context context, ILoadingResultListener<List<GoodsListBean>> listener, DispatchQuery teamQuery);

    /**
     * 查询通信保障
     *
     * @param context
     * @param listener
     */
    void getCommunicationList(Context context, ILoadingResultListener<List<CommunicationListBean>> listener, DispatchQuery teamQuery);

    /**
     * 查询物资企业
     *
     * @param context
     * @param listener
     */
    void getCompanyList(Context context, ILoadingResultListener<List<CompanyListBean>> listener, DispatchQuery teamQuery);

    /**
     * 查询专家
     *
     * @param context
     * @param listener
     */
    void getExpertList(Context context, ILoadingResultListener<List<ExpertListBean>> listener, DispatchQuery teamQuery);

    /**
     * 查询药物
     *
     * @param context
     * @param listener
     */
    void getMedicalList(Context context, ILoadingResultListener<List<MedicalListBean>> listener, DispatchQuery teamQuery);

    /**
     * 查询运输资源
     *
     * @param context
     * @param listener
     */
    void getTransportList(Context context, ILoadingResultListener<List<TransportListBean>> listener, DispatchQuery teamQuery);

    /**
     * 查询类型派遣状态
     *
     * @param context
     * @param listener 含返回值，返回值为“已经派遣的id”
     * @param resIds  需要对比的资源id
     * @param eventId  事件id
     */
    void checkDispatchState(Context context, ILoadingResultListener<List<String>> listener, List<String> resIds, String eventId, DispatchTypeEnum dispatchTypeEnum);


    /**
     * 查询救援队伍
     */
    void getTeamDispatchList(Context context, ILoadingResultListener<List<TeamListDispatchBean>> listener, TeamQuery query, String eventId);

    /**
     * 查询物资
     */
    void getGoodsDispatchList(Context context, ILoadingResultListener<List<GoodsListDispatchBean>> listener, GoodsQuery query, String eventId);

    /**
     * 查询物资
     */
    void getExpertDispatchList(Context context, ILoadingResultListener<List<ExpertListDispatchBean>> listener, ExpertQuery query, String eventId);

    /**
     * 查询企业
     */
    void getCompanyDispatchList(Context context, ILoadingResultListener<List<CompanyListDispatchBean>> listener, CompanyQuery query, String eventId);

    /**
     * 查询运输
     */
    void getTransportDispatchList(Context context, ILoadingResultListener<List<TransportListDispatchBean>> listener, TransportQuery query, String eventId);

    /**
     * 查询医疗
     */
    void getMedicalDispatchList(Context context, ILoadingResultListener<List<MedicalListDispatchBean>> listener, MedicalQuery query, String eventId);
    /**
     * 查询通讯
     */
    void getCommunicationDispatchList(Context context, ILoadingResultListener<List<CommunicationListDisPatchBean>> listener, CommunicationQuery query, String eventId);

    /**
     * 调派
     */
    void dispatchResource(Context context, ILoadingResultListener<Boolean> listener,DispatchActionVM dispatchActionVM);

}
