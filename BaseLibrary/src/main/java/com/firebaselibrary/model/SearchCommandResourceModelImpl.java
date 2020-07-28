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
import com.firebaselibrary.bean.search.struct.DispatchListMapper;
import com.firebaselibrary.constant.UrlConstant;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.krx.ydzh.commoncore.config.ConfigManager;
import com.krx.ydzh.commoncore.net.HttpUtils;
import com.krx.ydzh.commoncore.net.callback.OnResultListCallBack;
import com.orhanobut.logger.Logger;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zyq
 * @date 2020/3/6
 */
public class SearchCommandResourceModelImpl implements SearchCommandResourceModel {

    private SearchResourceModel searchResourceModel;

    public SearchCommandResourceModelImpl(SearchResourceModel searchResourceModel) {
        this.searchResourceModel = searchResourceModel;
    }

    @Override
    public void getTeamList(Context context, final ILoadingResultListener<List<TeamListBean>> listener, DispatchQuery teamQuery) {


        HttpUtils.getInstance().setBaseUrl(getBaseUrl())
                .jsonRequest(UrlConstant.API_COMMAND_TEAM_URL, teamQuery, new OnResultListCallBack<List<TeamListBean>>() {


                    @Override
                    public void onSuccess(int code, Object tag, List<TeamListBean> response) {
                        if (listener == null) {
                            return;
                        }

                        listener.success(response);

                    }

                    @Override
                    public void onFailure(Object tag, Exception e) {
                        listener.error(e.getMessage());
                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }

    public void getGoodsList(Context context, final ILoadingResultListener<List<GoodsListBean>> listener, DispatchQuery teamQuery) {


        HttpUtils.getInstance().setBaseUrl(getBaseUrl())
                .jsonRequest(UrlConstant.API_COMMAND_TEAM_URL, teamQuery, new OnResultListCallBack<List<GoodsListBean>>() {


                    @Override
                    public void onSuccess(int code, Object tag, List<GoodsListBean> response) {
                        if (listener == null) {
                            return;
                        }

                        listener.success(response);

                    }

                    @Override
                    public void onFailure(Object tag, Exception e) {
                        listener.error(e.getMessage());
                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }

    @Override
    public void getCommunicationList(Context context, final ILoadingResultListener<List<CommunicationListBean>> listener, DispatchQuery teamQuery) {

        HttpUtils.getInstance().setBaseUrl(getBaseUrl())
                .jsonRequest(UrlConstant.API_COMMAND_TEAM_URL, teamQuery, new OnResultListCallBack<List<CommunicationListBean>>() {


                    @Override
                    public void onSuccess(int code, Object tag, List<CommunicationListBean> response) {
                        if (listener == null) {
                            return;
                        }

                        listener.success(response);

                    }

                    @Override
                    public void onFailure(Object tag, Exception e) {
                        listener.error(e.getMessage());
                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }

    @Override
    public void getCompanyList(Context context, final ILoadingResultListener<List<CompanyListBean>> listener, DispatchQuery teamQuery) {

        HttpUtils.getInstance().setBaseUrl(getBaseUrl())
                .jsonRequest(UrlConstant.API_COMMAND_TEAM_URL, teamQuery, new OnResultListCallBack<List<CompanyListBean>>() {


                    @Override
                    public void onSuccess(int code, Object tag, List<CompanyListBean> response) {
                        if (listener == null) {
                            return;
                        }

                        listener.success(response);

                    }

                    @Override
                    public void onFailure(Object tag, Exception e) {
                        listener.error(e.getMessage());
                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }

    @Override
    public void getExpertList(Context context, final ILoadingResultListener<List<ExpertListBean>> listener, DispatchQuery teamQuery) {
        HttpUtils.getInstance().setBaseUrl(getBaseUrl())
                .jsonRequest(UrlConstant.API_COMMAND_TEAM_URL, teamQuery, new OnResultListCallBack<List<ExpertListBean>>() {


                    @Override
                    public void onSuccess(int code, Object tag, List<ExpertListBean> response) {
                        if (listener == null) {
                            return;
                        }

                        listener.success(response);

                    }

                    @Override
                    public void onFailure(Object tag, Exception e) {
                        listener.error(e.getMessage());
                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }

    @Override
    public void getMedicalList(Context context, final ILoadingResultListener<List<MedicalListBean>> listener, DispatchQuery teamQuery) {
        HttpUtils.getInstance().setBaseUrl(getBaseUrl())
                .jsonRequest(UrlConstant.API_COMMAND_TEAM_URL, teamQuery, new OnResultListCallBack<List<MedicalListBean>>() {


                    @Override
                    public void onSuccess(int code, Object tag, List<MedicalListBean> response) {
                        if (listener == null) {
                            return;
                        }

                        listener.success(response);

                    }

                    @Override
                    public void onFailure(Object tag, Exception e) {
                        listener.error(e.getMessage());
                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }

    @Override
    public void getTransportList(Context context, final ILoadingResultListener<List<TransportListBean>> listener, DispatchQuery teamQuery) {
        HttpUtils.getInstance().setBaseUrl(getBaseUrl())
                .jsonRequest(UrlConstant.API_COMMAND_TEAM_URL, teamQuery, new OnResultListCallBack<List<TransportListBean>>() {


                    @Override
                    public void onSuccess(int code, Object tag, List<TransportListBean> response) {
                        if (listener == null) {
                            return;
                        }

                        listener.success(response);

                    }

                    @Override
                    public void onFailure(Object tag, Exception e) {
                        listener.error(e.getMessage());
                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }

    @Override
    public void checkDispatchState(Context context, final ILoadingResultListener<List<String>> listener, List<String> resIds, String eventId, DispatchTypeEnum dispatchTypeEnum) {
        Map<String, Object> params = new HashMap<>();
        params.put("eventId", eventId);
        params.put("type", dispatchTypeEnum.getValue());
        params.put("resIds", resIds);

        HttpUtils.getInstance().setBaseUrl(getBaseUrl())
                .jsonRequest(UrlConstant.API_COMMAND_CHECK_URL, params, new OnResultListCallBack<List<String>>() {
                    @Override
                    public void onSuccess(int code, Object tag, List<String> response) {
                        if (listener == null) {
                            return;
                        }
                        listener.success(response);
                    }

                    @Override
                    public void onFailure(Object tag, Exception e) {
                        listener.error(e.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                    }
                });
    }

    @Override
    public void getTeamDispatchList(final Context context, final ILoadingResultListener<List<TeamListDispatchBean>> listener, TeamQuery query, final String eventId) {
        final List<TeamListDispatchBean>[] datas = new List[]{new ArrayList<>()};

        final ILoadingResultListener<List<String>> checkListener = new ILoadingResultListener<List<String>>() {
            @Override
            public void success(List<String> result) {
                List<TeamListDispatchBean> dispatchBeans = datas[0];
                if (result == null || result.size() == 0) {
                    listener.success(dispatchBeans);
                    return;
                }
                for (TeamListDispatchBean team : dispatchBeans) {
                    if (result.contains(team.getId())) {
                        team.setDispatched(true);
                    }
                }
                listener.success(dispatchBeans);
            }

            @Override
            public void error(String error) {
                listener.error(error);
            }

            @Override
            public void progress(double progress) {

            }
        };
        ILoadingResultListener<List<TeamListBean>> teamListListener = new ILoadingResultListener<List<TeamListBean>>() {
            @Override
            public void success(List<TeamListBean> result) {
                if (result == null || result.size() == 0) {
                    listener.success(new ArrayList<TeamListDispatchBean>());
                    return;
                }
                datas[0] = DispatchListMapper.INSTANCE.teamToDispatchList(result);
                List<String> resIds = Lists.transform(result, new Function<TeamListBean, String>() {
                    @NullableDecl
                    @Override
                    public String apply(@NullableDecl TeamListBean team) {
                        return team.getId();
                    }
                });
                checkDispatchState(context, checkListener, resIds, eventId, DispatchTypeEnum.team);
            }

            @Override
            public void error(String error) {
                listener.error(error);
            }

            @Override
            public void progress(double progress) {
                listener.progress(progress);
            }
        };
        searchResourceModel.getTeamList(context, teamListListener, query);
    }

    @Override
    public void getGoodsDispatchList(final Context context, final ILoadingResultListener<List<GoodsListDispatchBean>> listener, GoodsQuery query, final String eventId) {
        final List<GoodsListDispatchBean>[] datas = new List[]{new ArrayList<>()};

        final ILoadingResultListener<List<String>> checkListener = new ILoadingResultListener<List<String>>() {
            @Override
            public void success(List<String> result) {
                List<GoodsListDispatchBean> dispatchBeans = datas[0];
                if (result == null || result.size() == 0) {
                    listener.success(dispatchBeans);
                    return;
                }
                for (GoodsListDispatchBean team : dispatchBeans) {
                    if (result.contains(team.getId())) {
                        team.setDispatched(true);
                    }
                }
                listener.success(dispatchBeans);
            }

            @Override
            public void error(String error) {
                listener.error(error);
            }

            @Override
            public void progress(double progress) {

            }
        };
        ILoadingResultListener<List<GoodsListBean>> listListener = new ILoadingResultListener<List<GoodsListBean>>() {
            @Override
            public void success(List<GoodsListBean> result) {
                if (result == null || result.size() == 0) {
                    listener.success(new ArrayList<GoodsListDispatchBean>());
                    return;
                }
                datas[0] = DispatchListMapper.INSTANCE.goodsToDispatchList(result);
                List<String> resIds = Lists.transform(result, new Function<GoodsListBean, String>() {
                    @NullableDecl
                    @Override
                    public String apply(@NullableDecl GoodsListBean team) {
                        return team.getId();
                    }
                });
                checkDispatchState(context, checkListener, resIds, eventId, DispatchTypeEnum.team);
            }

            @Override
            public void error(String error) {
                listener.error(error);
            }

            @Override
            public void progress(double progress) {
                listener.progress(progress);
            }
        };
        searchResourceModel.getGoodsList(context, listListener, query);
    }

    @Override
    public void getExpertDispatchList(final Context context, final ILoadingResultListener<List<ExpertListDispatchBean>> listener, ExpertQuery query, final String eventId) {
        final List<ExpertListDispatchBean>[] datas = new List[]{new ArrayList<>()};

        final ILoadingResultListener<List<String>> checkListener = new ILoadingResultListener<List<String>>() {
            @Override
            public void success(List<String> result) {
                List<ExpertListDispatchBean> dispatchBeans = datas[0];
                if (result == null || result.size() == 0) {
                    listener.success(dispatchBeans);
                    return;
                }
                for (ExpertListDispatchBean team : dispatchBeans) {
                    if (result.contains(team.getId())) {
                        team.setDispatched(true);
                    }
                }
                listener.success(dispatchBeans);
            }

            @Override
            public void error(String error) {
                listener.error(error);
            }

            @Override
            public void progress(double progress) {

            }
        };
        ILoadingResultListener<List<ExpertListBean>> listListener = new ILoadingResultListener<List<ExpertListBean>>() {
            @Override
            public void success(List<ExpertListBean> result) {
                if (result == null || result.size() == 0) {
                    listener.success(new ArrayList<ExpertListDispatchBean>());
                    return;
                }
                datas[0] = DispatchListMapper.INSTANCE.expertToDispatchList(result);
                List<String> resIds = Lists.transform(result, new Function<ExpertListBean, String>() {
                    @NullableDecl
                    @Override
                    public String apply(@NullableDecl ExpertListBean team) {
                        return team.getId();
                    }
                });
                checkDispatchState(context, checkListener, resIds, eventId, DispatchTypeEnum.expert);
            }

            @Override
            public void error(String error) {
                listener.error(error);
            }

            @Override
            public void progress(double progress) {
                listener.progress(progress);
            }
        };
        searchResourceModel.getExpertList(context, listListener, query);
    }

    @Override
    public void getCompanyDispatchList(final Context context, final ILoadingResultListener<List<CompanyListDispatchBean>> listener, CompanyQuery query, final String eventId) {
        final List<CompanyListDispatchBean>[] datas = new List[]{new ArrayList<>()};

        final ILoadingResultListener<List<String>> checkListener = new ILoadingResultListener<List<String>>() {
            @Override
            public void success(List<String> result) {
                List<CompanyListDispatchBean> dispatchBeans = datas[0];
                if (result == null || result.size() == 0) {
                    listener.success(dispatchBeans);
                    return;
                }
                for (CompanyListDispatchBean team : dispatchBeans) {
                    if (result.contains(team.getId())) {
                        team.setDispatched(true);
                    }
                }
                listener.success(dispatchBeans);
            }

            @Override
            public void error(String error) {
                listener.error(error);
            }

            @Override
            public void progress(double progress) {

            }
        };
        ILoadingResultListener<List<CompanyListBean>> listListener = new ILoadingResultListener<List<CompanyListBean>>() {
            @Override
            public void success(List<CompanyListBean> result) {
                if (result == null || result.size() == 0) {
                    listener.success(new ArrayList<CompanyListDispatchBean>());
                    return;
                }
                datas[0] = DispatchListMapper.INSTANCE.companyToDispatchList(result);
                List<String> resIds = Lists.transform(result, new Function<CompanyListBean, String>() {
                    @NullableDecl
                    @Override
                    public String apply(@NullableDecl CompanyListBean team) {
                        return team.getId();
                    }
                });
                checkDispatchState(context, checkListener, resIds, eventId, DispatchTypeEnum.company);
            }

            @Override
            public void error(String error) {
                listener.error(error);
            }

            @Override
            public void progress(double progress) {
                listener.progress(progress);
            }
        };
        searchResourceModel.getCompanyList(context, listListener, query);
    }

    @Override
    public void getTransportDispatchList(final Context context, final ILoadingResultListener<List<TransportListDispatchBean>> listener, TransportQuery query, final String eventId) {
        final List<TransportListDispatchBean>[] datas = new List[]{new ArrayList<>()};

        final ILoadingResultListener<List<String>> checkListener = new ILoadingResultListener<List<String>>() {
            @Override
            public void success(List<String> result) {
                List<TransportListDispatchBean> dispatchBeans = datas[0];
                if (result == null || result.size() == 0) {
                    listener.success(dispatchBeans);
                    return;
                }
                for (TransportListDispatchBean team : dispatchBeans) {
                    if (result.contains(team.getId())) {
                        team.setDispatched(true);
                    }
                }
                listener.success(dispatchBeans);
            }

            @Override
            public void error(String error) {
                listener.error(error);
            }

            @Override
            public void progress(double progress) {

            }
        };
        ILoadingResultListener<List<TransportListBean>> listListener = new ILoadingResultListener<List<TransportListBean>>() {
            @Override
            public void success(List<TransportListBean> result) {
                if (result == null || result.size() == 0) {
                    listener.success(new ArrayList<TransportListDispatchBean>());
                    return;
                }
                datas[0] = DispatchListMapper.INSTANCE.transportToDispatchList(result);
                List<String> resIds = Lists.transform(result, new Function<TransportListBean, String>() {
                    @NullableDecl
                    @Override
                    public String apply(@NullableDecl TransportListBean team) {
                        return team.getId();
                    }
                });
                checkDispatchState(context, checkListener, resIds, eventId, DispatchTypeEnum.transport);
            }

            @Override
            public void error(String error) {
                listener.error(error);
            }

            @Override
            public void progress(double progress) {
                listener.progress(progress);
            }
        };
        searchResourceModel.getTransportList(context, listListener, query);
    }

    @Override
    public void getMedicalDispatchList(final Context context, final ILoadingResultListener<List<MedicalListDispatchBean>> listener, MedicalQuery query, final String eventId) {
        final List<MedicalListDispatchBean>[] datas = new List[]{new ArrayList<>()};

        final ILoadingResultListener<List<String>> checkListener = new ILoadingResultListener<List<String>>() {
            @Override
            public void success(List<String> result) {
                List<MedicalListDispatchBean> dispatchBeans = datas[0];
                if (result == null || result.size() == 0) {
                    listener.success(dispatchBeans);
                    return;
                }
                for (MedicalListDispatchBean team : dispatchBeans) {
                    if (result.contains(team.getId())) {
                        team.setDispatched(true);
                    }
                }
                listener.success(dispatchBeans);
            }

            @Override
            public void error(String error) {
                listener.error(error);
            }

            @Override
            public void progress(double progress) {

            }
        };
        ILoadingResultListener<List<MedicalListBean>> listListener = new ILoadingResultListener<List<MedicalListBean>>() {
            @Override
            public void success(List<MedicalListBean> result) {
                if (result == null || result.size() == 0) {
                    listener.success(new ArrayList<MedicalListDispatchBean>());
                    return;
                }
                datas[0] = DispatchListMapper.INSTANCE.medicalToDispatchList(result);
                List<String> resIds = Lists.transform(result, new Function<MedicalListBean, String>() {
                    @NullableDecl
                    @Override
                    public String apply(@NullableDecl MedicalListBean team) {
                        return team.getId();
                    }
                });
                checkDispatchState(context, checkListener, resIds, eventId, DispatchTypeEnum.transport);
            }

            @Override
            public void error(String error) {
                listener.error(error);
            }

            @Override
            public void progress(double progress) {
                listener.progress(progress);
            }
        };
        searchResourceModel.getMedicalList(context, listListener, query);
    }

    @Override
    public void getCommunicationDispatchList(final Context context, final ILoadingResultListener<List<CommunicationListDisPatchBean>> listener, CommunicationQuery query, final String eventId) {
        final List<CommunicationListDisPatchBean>[] datas = new List[]{new ArrayList<>()};

        final ILoadingResultListener<List<String>> checkListener = new ILoadingResultListener<List<String>>() {
            @Override
            public void success(List<String> result) {
                List<CommunicationListDisPatchBean> dispatchBeans = datas[0];
                if (result == null || result.size() == 0) {
                    listener.success(dispatchBeans);
                    return;
                }
                for (CommunicationListDisPatchBean team : dispatchBeans) {
                    if (result.contains(team.getId())) {
                        team.setDispatched(true);
                    }
                }
                listener.success(dispatchBeans);
            }

            @Override
            public void error(String error) {
                listener.error(error);
            }

            @Override
            public void progress(double progress) {

            }
        };
        ILoadingResultListener<List<CommunicationListBean>> listListener = new ILoadingResultListener<List<CommunicationListBean>>() {
            @Override
            public void success(List<CommunicationListBean> result) {
                if (result == null || result.size() == 0) {
                    listener.success(new ArrayList<CommunicationListDisPatchBean>());
                    return;
                }
                datas[0] = DispatchListMapper.INSTANCE.communicationToDispatchList(result);
                List<String> resIds = Lists.transform(result, new Function<CommunicationListBean, String>() {
                    @NullableDecl
                    @Override
                    public String apply(@NullableDecl CommunicationListBean team) {
                        return team.getId();
                    }
                });
                checkDispatchState(context, checkListener, resIds, eventId, DispatchTypeEnum.transport);
            }

            @Override
            public void error(String error) {
                listener.error(error);
            }

            @Override
            public void progress(double progress) {
                listener.progress(progress);
            }
        };
        searchResourceModel.getCommunicationList(context, listListener, query);
    }

    @Override
    public void dispatchResource(Context context, final ILoadingResultListener<Boolean> listener, DispatchActionVM dispatchActionVM) {
        HttpUtils.getInstance().setBaseUrl(getBaseUrl())
                .jsonRequest(UrlConstant.API_COMMAND_DISPATCH_URL, dispatchActionVM, new OnResultListCallBack<Boolean>() {
                    @Override
                    public void onSuccess(int code, Object tag, Boolean result) {
                        if (listener == null) {
                            return;
                        }
                        listener.success(result);
                    }

                    @Override
                    public void onFailure(Object tag, Exception e) {
                        listener.error(e.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                    }
                });
    }

    private String getBaseUrl() {
        return ConfigManager.getInstance().getNetConfig(UrlConstant.API_COMMAND_RES_BASE_URL);
    }

}
