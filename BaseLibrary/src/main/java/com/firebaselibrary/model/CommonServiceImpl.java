package com.firebaselibrary.model;

import java.util.ArrayList;
import java.util.List;

import org.creation.common.string.StringUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.basestrut.extend.modal.HttpHeadersExtensions;
import com.basestrut.extend.modal.SystemConfig;
import com.basestrut.extend.service.ICommonService;
import com.basestrut.extend.service.IHttpClient;
import com.basestrut.repository.modal.Condition;
import com.basestrut.repository.modal.ConditionOper;
import com.basestrut.repository.modal.DynamicQuery;
import com.basestrut.repository.modal.DynamicTable;
import com.basestrut.repository.modal.QueryArgs;
import com.basestrut.repository.modal.TableViewType;
import com.basestrut.repository.modal.WorkType;
import com.basestrut.repository.modal.inputparam.MultiDynamicTable;
import com.basestrut.repository.modal.inputparam.SingleDynamicQuery;
import com.basestrut.repository.modal.inputparam.SingleDynamicTable;
import com.basestrut.repository.modal.outparam.ResponseCode;
import com.basestrut.repository.modal.outparam.ResponseResult;

public abstract class CommonServiceImpl<T>
        implements ICommonService<T>
{
    private IHttpClient httpClient;
    private SystemConfig systemConfig;

    @Override
    public Boolean Exists(String idField,String id, String tableName, String database) {
        SingleDynamicTable singleDynamicTable = new SingleDynamicTable();
        singleDynamicTable.DataBase =  StringUtil.isEmpty(database) ? systemConfig.getDatabase() : database;

        DynamicTable dynamicTable = new DynamicTable();
        dynamicTable.TableName=tableName;
        List<Condition>list=new ArrayList<Condition>();
        list.add(new Condition(idField,ConditionOper.eq,id));
        dynamicTable.Conditions=list;
        dynamicTable.WorkType= WorkType.Get;
        singleDynamicTable.DynamicTable = dynamicTable;

        String json=JSONObject.toJSONString(singleDynamicTable);
        ResponseResult responseResult = httpClient.PostAsync(
                systemConfig.getBaseUrl()+"base/get",
                json,
                HttpHeadersExtensions.BuilderHeadersParam(systemConfig.getToken()));
        if (responseResult.Result==ResponseCode.Ok.getValue())
        {
            JSONArray array=JSONArray.parseArray(responseResult.Data.toString());
            if (array.size() > 0)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public ResponseResult Save(T data, String tableName, String database) {
        SingleDynamicTable singleDynamicTable = new SingleDynamicTable();
        singleDynamicTable.DataBase = StringUtil.isEmpty(database) ? systemConfig.getDatabase() : database;
        DynamicTable dynamicTable = new DynamicTable();
        dynamicTable.WorkType = WorkType.Save;
        dynamicTable.TableName = tableName;
        dynamicTable.TableData = data;
        singleDynamicTable.DynamicTable = dynamicTable;

        String json=JSONObject.toJSONString(singleDynamicTable);
        ResponseResult responseResult =
                httpClient.PostAsync(systemConfig.getBaseUrl()+"base/execute",
                        json,
                        HttpHeadersExtensions.BuilderHeadersParam(systemConfig.getToken()));
        return responseResult;
    }

    @Override
    public ResponseResult Save(List<T> datas, String tableName, String database) {
        MultiDynamicTable multiDynamicTable = new MultiDynamicTable();
        multiDynamicTable.DataBase = StringUtil.isEmpty(database) ? systemConfig.getDatabase() : database;

        multiDynamicTable.DynamicTableList = new ArrayList<DynamicTable>();
        for(int i=0;i<datas.size();i++)
        {
            T item = datas.get(i);
            DynamicTable dynamicTable = new DynamicTable();
            dynamicTable.WorkType = WorkType.Save;
            dynamicTable.TableName = tableName;
            dynamicTable.TableData = item;
            multiDynamicTable.DynamicTableList.add(dynamicTable);
        }

        String url = systemConfig.getBaseUrl()+"base/tranexecute";
        String json=JSONObject.toJSONString(multiDynamicTable);
        ResponseResult responseResult =
                httpClient.PostAsync(url,
                        json, HttpHeadersExtensions.BuilderHeadersParam(systemConfig.getToken()));
        return responseResult;
    }

    @Override
    public ResponseResult Update(T data,String idField, String id, String tableName, String database) {
        SingleDynamicTable singleDynamicTable = new SingleDynamicTable();
        singleDynamicTable.DataBase = StringUtil.isEmpty(database) ? systemConfig.getDatabase() : database;
        DynamicTable dynamicTable = new DynamicTable();
        dynamicTable.WorkType = WorkType.Update;
        dynamicTable.TableName = tableName;
        dynamicTable.TableData = data;
        List<Condition>list=new ArrayList<Condition>();
        list.add(new Condition(idField,ConditionOper.eq,id));
        dynamicTable.Conditions=list;
        singleDynamicTable.DynamicTable = dynamicTable;

        String json=JSONObject.toJSONString(singleDynamicTable);
        ResponseResult responseResult =
                httpClient.PostAsync(systemConfig.getBaseUrl()+"base/execute",
                        json,
                        HttpHeadersExtensions.BuilderHeadersParam(systemConfig.getToken()));
        return responseResult;
    }

    @Override
    public ResponseResult DeleteDatas(String idField,List<String> ids, String tableName, String database) {
        MultiDynamicTable multiDynamicTable = new MultiDynamicTable();
        multiDynamicTable.DataBase = StringUtil.isEmpty(database) ? systemConfig.getDatabase() : database;
        List<DynamicTable> list=new ArrayList<DynamicTable>();

        for(int i=0;i<ids.size();i++)
        {
            String id=ids.get(i);
            DynamicTable dt1 = new DynamicTable();
            dt1.WorkType = WorkType.Delete;
            dt1.TableName = tableName;
            List<Condition> conditionlist=new ArrayList<Condition>();
            conditionlist.add(new Condition(idField,ConditionOper.eq,id));
            dt1.Conditions=conditionlist;

            list.add(dt1);
        }
        multiDynamicTable.DynamicTableList = list;
        String json=JSONObject.toJSONString(multiDynamicTable);
        String url = systemConfig.getBaseUrl()+"base/tranexecute";
        ResponseResult responseResult = httpClient.PostAsync(url,
                json,
                HttpHeadersExtensions.BuilderHeadersParam(systemConfig.getToken()));
        return responseResult;
    }

    @Override
    public ResponseResult BaseTrans(List<DynamicTable> datas, String database) {
        MultiDynamicTable multiDynamicTable = new MultiDynamicTable();
        multiDynamicTable.DataBase = StringUtil.isEmpty(database) ? systemConfig.getDatabase() : database;

        List<DynamicTable> list=new ArrayList<DynamicTable>();
        list.addAll(datas);
        multiDynamicTable.DynamicTableList = list;

        String url = systemConfig.getBaseUrl()+"base/tranexecute";
        String json=JSONObject.toJSONString(multiDynamicTable);
        ResponseResult responseResult =
                httpClient.PostAsync(url,
                        json, HttpHeadersExtensions.BuilderHeadersParam(systemConfig.getToken()));
        return responseResult;
    }

    @Override
    public ResponseResult Get(String idField,String id, String tableName, String database) {
        SingleDynamicTable singleDynamicTable = new SingleDynamicTable();
        singleDynamicTable.DataBase =  StringUtil.isEmpty(database) ? systemConfig.getDatabase() : database;

        DynamicTable dynamicTable = new DynamicTable();
        dynamicTable.TableName=tableName;
        List<Condition>list=new ArrayList<Condition>();
        list.add(new Condition(idField,ConditionOper.eq,id));
        dynamicTable.Conditions=list;
        dynamicTable.WorkType= WorkType.Get;
        singleDynamicTable.DynamicTable = dynamicTable;

        String json=JSONObject.toJSONString(singleDynamicTable);
        ResponseResult responseResult = httpClient.PostAsync(
                systemConfig.getBaseUrl()+"base/get",
                json,
                HttpHeadersExtensions.BuilderHeadersParam(systemConfig.getToken()));
        if (responseResult.Result==ResponseCode.Ok.getValue())
        {
            JSONArray array=JSONArray.parseArray(responseResult.Data.toString());
            if (array.size() > 0)
            {
                Object obj= array.get(0);
                responseResult.Data = obj;
            }
            else
            {
                responseResult.Data=null;
            }
        }
        return responseResult;
    }

    @Override
    public ResponseResult GetAttachmentList(String id,String order,String sort) {
        SingleDynamicQuery singleDynamicQuery = new SingleDynamicQuery();
        singleDynamicQuery.DataBase = systemConfig.getYhtxdatabase();
        DynamicQuery dynamicQuery = new DynamicQuery();
        dynamicQuery.TableView = "Attachment";
        dynamicQuery.Type = TableViewType.Table;

        QueryArgs queryArgs = new QueryArgs();
        queryArgs.Order = order;
        queryArgs.Sort = sort;

        List<Condition> conditionList=new ArrayList<Condition>();
        conditionList.add(new Condition("Businessid",ConditionOper.eq,id));
        queryArgs.Conditions = conditionList;
        dynamicQuery.QueryArgs = queryArgs;
        singleDynamicQuery.DynamicQuery = dynamicQuery;

        String json=JSONObject.toJSONString(singleDynamicQuery);
        ResponseResult responseResult = httpClient.PostAsync(systemConfig.getBaseUrl()+"base/query",
                json, HttpHeadersExtensions.BuilderHeadersParam(systemConfig.getToken()));
        if (responseResult.Result == ResponseCode.Ok.getValue())
        {
            JSONArray array=JSONArray.parseArray(responseResult.Data.toString());
            responseResult.Data=array;
        }
        return responseResult;
    }

    @Override
    public ResponseResult Query(List<Condition> conditions, String tableName, String order, String sort,
                                TableViewType type, String database) {
        SingleDynamicQuery singleDynamicQuery = new SingleDynamicQuery();
        singleDynamicQuery.DataBase = StringUtil.isEmpty(database) ? systemConfig.getDatabase() : database;
        DynamicQuery dynamicQuery = new DynamicQuery();
        dynamicQuery.TableView = tableName;
        dynamicQuery.Type = type;

        QueryArgs queryArgs = new QueryArgs();
        queryArgs.Order = order;
        queryArgs.Sort = sort;
        queryArgs.Conditions = conditions;
        dynamicQuery.QueryArgs = queryArgs;
        singleDynamicQuery.DynamicQuery = dynamicQuery;

        String json=JSONObject.toJSONString(singleDynamicQuery);
        ResponseResult responseResult = httpClient.PostAsync(systemConfig.getBaseUrl()+"base/query",
                json, HttpHeadersExtensions.BuilderHeadersParam(systemConfig.getToken()));
        if (responseResult.Result == ResponseCode.Ok.getValue())
        {
            JSONArray array=JSONArray.parseArray(responseResult.Data.toString());
            responseResult.Data=array;
        }
        return responseResult;
    }

}
