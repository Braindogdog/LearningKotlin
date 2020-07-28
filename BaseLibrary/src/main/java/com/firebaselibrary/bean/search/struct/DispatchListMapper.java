package com.firebaselibrary.bean.search.struct;

import com.firebaselibrary.bean.search.CommunicationListBean;
import com.firebaselibrary.bean.search.CompanyListBean;
import com.firebaselibrary.bean.search.ExpertListBean;
import com.firebaselibrary.bean.search.GoodsListBean;
import com.firebaselibrary.bean.search.MedicalListBean;
import com.firebaselibrary.bean.search.TeamListBean;
import com.firebaselibrary.bean.search.TransportListBean;
import com.firebaselibrary.bean.search.dispatch.CommunicationListDisPatchBean;
import com.firebaselibrary.bean.search.dispatch.CompanyListDispatchBean;
import com.firebaselibrary.bean.search.dispatch.ExpertListDispatchBean;
import com.firebaselibrary.bean.search.dispatch.GoodsListDispatchBean;
import com.firebaselibrary.bean.search.dispatch.MedicalListDispatchBean;
import com.firebaselibrary.bean.search.dispatch.TeamListDispatchBean;
import com.firebaselibrary.bean.search.dispatch.TransportListDispatchBean;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 实体转换类
 * Created by zhangmh on 2020/4/24.
 */
@Mapper
public interface DispatchListMapper {


    DispatchListMapper INSTANCE = Mappers.getMapper(DispatchListMapper.class);

    /**
     * 队伍
     */
    TeamListDispatchBean teamToDispatch(TeamListBean team);

    /**
     * 队伍列表
     */
    List<TeamListDispatchBean> teamToDispatchList(List<TeamListBean> team);

    /**
     * 物资
     */
    GoodsListDispatchBean goodsToDispatch(GoodsListBean team);

    /**
     * 物资列表
     */
    List<GoodsListDispatchBean> goodsToDispatchList(List<GoodsListBean> team);

    /**
     * 专家
     */
    ExpertListDispatchBean expertToDispatch(ExpertListBean team);

    /**
     * 专家列表
     */
    List<ExpertListDispatchBean> expertToDispatchList(List<ExpertListBean> team);

    /**
     * 物资企业
     */
    CompanyListDispatchBean companyToDispatch(CompanyListBean team);

    /**
     * 物资企业
     */
    List<CompanyListDispatchBean> companyToDispatchList(List<CompanyListBean> team);

    /**
     * 医疗
     */
    MedicalListDispatchBean medicalToDispatch(MedicalListBean team);

    /**
     * 医疗
     */
    List<MedicalListDispatchBean> medicalToDispatchList(List<MedicalListBean> team);

    /**
     * 运输资源
     */
    TransportListDispatchBean transportToDispatch(TransportListBean team);

    /**
     * 运输资源
     */
    List<TransportListDispatchBean> transportToDispatchList(List<TransportListBean> team);

    /**
     * 通信保障
     */
    CommunicationListDisPatchBean communicationToDispatch(CommunicationListBean team);

    /**
     * 通信保障
     */
    List<CommunicationListDisPatchBean> communicationToDispatchList(List<CommunicationListBean> team);

}
