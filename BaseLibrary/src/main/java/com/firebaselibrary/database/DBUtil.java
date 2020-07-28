package com.firebaselibrary.database;

import android.content.Context;

import com.firebaselibrary.bean.AreaBean;
import com.firebaselibrary.bean.LocationBean;
import com.firebaselibrary.bean.NavigationAddressBean;
import com.firebaselibrary.bean.NavigationHistoryBean;
import com.firebaselibrary.bean.fireunit.FireUnitBean;
import com.firebaselibrary.greendao.AreaBeanDao;
import com.firebaselibrary.greendao.FireUnitBeanDao;
import com.firebaselibrary.greendao.LocationBeanDao;
import com.firebaselibrary.greendao.NavigationAddressBeanDao;
import com.firebaselibrary.greendao.NavigationHistoryBeanDao;

import org.creation.common.geometry.GeoLocation;

import java.util.ArrayList;
import java.util.List;

import common.baselibrary.baseutil.EmptyUtil;
import common.map.api.utils.LocationUtils;

/**
 * Created by chasen on 2018/4/13.
 * 数据库操作工具类
 */

public class DBUtil {
    /**
     * 添加一条地址搜索记录
     *
     * @param context
     * @param location
     */
    public static void setSeachAddressHistory(Context context, GeoLocation location) {
        if (location == null) {
            return;
        }
        NavigationAddressBeanDao addressBeanDao = EntityManager.getInstance(context).getNavigationAddressBeanDao();
        List<NavigationAddressBean> list = addressBeanDao.queryBuilder()
                .where(NavigationAddressBeanDao.Properties.Address.eq(location.getDescription())
                        , NavigationAddressBeanDao.Properties.Type.eq(1))
                .build()
                .list();
        if (!EmptyUtil.isEmpty(list))
            return;
        if (LocationUtils.checkLocation(location.getPoint())) {
            NavigationAddressBean addressBean = new NavigationAddressBean(null, location.getPoint().getX(), location.getPoint().getY()
                    , location.getDescription(), 1, 0);
            addressBeanDao.insert(addressBean);
        }

    }

    /**
     * 获取地址搜索记录
     *
     * @param context
     * @return
     */
    public static List<GeoLocation> getSearchAddressHistory(Context context) {
        NavigationAddressBeanDao addressBeanDao = EntityManager.getInstance(context).getNavigationAddressBeanDao();
        List<NavigationAddressBean> list = addressBeanDao.queryBuilder()
                .where(NavigationAddressBeanDao.Properties.Type.eq(1))
                .build()
                .list();
        List<GeoLocation> listLocation = new ArrayList<>();
        for (NavigationAddressBean addressBean : list) {
            GeoLocation location = new GeoLocation(addressBean.getX(), addressBean.getY(), addressBean.getAddress());
            listLocation.add(location);
        }
        return listLocation;
    }

    /**
     * 删除地址搜索记录
     *
     * @param context
     */
    public static void deleteSearchAddress(Context context) {
        NavigationAddressBeanDao addressBeanDao = EntityManager.getInstance(context).getNavigationAddressBeanDao();
        List<NavigationAddressBean> list = addressBeanDao.queryBuilder()
                .where(NavigationAddressBeanDao.Properties.Type.eq(1))
                .build()
                .list();
        for (NavigationAddressBean addressBean : list) {
            addressBeanDao.delete(addressBean);
        }
    }

    /**
     * 添加导航记录
     *
     * @param historyBean
     * @param context
     */
    public static void setNavigationHistory(NavigationHistoryBean historyBean, Context context) {
        NavigationAddressBeanDao addressBeanDao = EntityManager.getInstance(context).getNavigationAddressBeanDao();
        List<NavigationAddressBean> listStart = addressBeanDao.queryBuilder()
                .where(NavigationAddressBeanDao.Properties.Address.eq(historyBean.getStartPoint().getDescription())
                        , NavigationAddressBeanDao.Properties.Type.eq(2))
                .build()
                .list();
        List<NavigationAddressBean> listEnd = addressBeanDao.queryBuilder()
                .where(NavigationAddressBeanDao.Properties.Address.eq(historyBean.getEndPoint().getDescription())
                        , NavigationAddressBeanDao.Properties.Type.eq(3))
                .build()
                .list();
        if (!EmptyUtil.isEmpty(listStart) && !EmptyUtil.isEmpty(listEnd))
            return;

        NavigationHistoryBeanDao historyBeanDao = EntityManager.getInstance(context).getNavigationHistoryBeanDao();
        long index = historyBeanDao.insert(historyBean);
        GeoLocation startPoint = historyBean.getStartPoint();
        GeoLocation endPoint = historyBean.getEndPoint();
        NavigationAddressBean startAddressBean = new NavigationAddressBean(null, startPoint.getPoint().getX(), startPoint.getPoint().getY()
                , startPoint.getDescription(), 2, index);
        NavigationAddressBean endAddressBean = new NavigationAddressBean(null, endPoint.getPoint().getX(), endPoint.getPoint().getY()
                , endPoint.getDescription(), 3, index);
        addressBeanDao.insert(startAddressBean);
        addressBeanDao.insert(endAddressBean);
    }

    /**
     * 获取导航记录
     *
     * @param context
     * @return
     */
    public static List<NavigationHistoryBean> getNavigationHistory(Context context) {
        NavigationHistoryBeanDao historyBeanDao = EntityManager.getInstance(context).getNavigationHistoryBeanDao();
        NavigationAddressBeanDao addressBeanDao = EntityManager.getInstance(context).getNavigationAddressBeanDao();
        List<NavigationHistoryBean> listNavigationHistory = historyBeanDao.queryBuilder().build().list();
        for (NavigationHistoryBean bean : listNavigationHistory) {
            List<NavigationAddressBean> listStart = addressBeanDao.queryBuilder()
                    .where(NavigationAddressBeanDao.Properties.Histroyid.eq(bean.getHistroyid())
                            , NavigationAddressBeanDao.Properties.Type.eq(2))
                    .limit(1)
                    .build()
                    .list();
            if (EmptyUtil.isEmpty(listStart))
                break;
            bean.setStartPoint(new GeoLocation(listStart.get(0).getX(), listStart.get(0).getY(), listStart.get(0).getAddress()));
            List<NavigationAddressBean> listEnd = addressBeanDao.queryBuilder()
                    .where(NavigationAddressBeanDao.Properties.Histroyid.eq(bean.getHistroyid())
                            , NavigationAddressBeanDao.Properties.Type.eq(3))
                    .limit(1)
                    .build()
                    .list();
            if (EmptyUtil.isEmpty(listEnd))
                break;
            bean.setEndPoint(new GeoLocation(listEnd.get(0).getX(), listEnd.get(0).getY(), listEnd.get(0).getAddress()));
        }
        return listNavigationHistory;
    }

    /**
     * 删除导航记录
     *
     * @param context
     */
    public static void deleteNavigationHistory(Context context) {
        NavigationHistoryBeanDao historyBeanDao = EntityManager.getInstance(context).getNavigationHistoryBeanDao();
        NavigationAddressBeanDao addressBeanDao = EntityManager.getInstance(context).getNavigationAddressBeanDao();
        List<NavigationHistoryBean> listNavigationHistory = historyBeanDao.queryBuilder().build().list();
        for (NavigationHistoryBean bean : listNavigationHistory) {
            List<NavigationAddressBean> list = addressBeanDao.queryBuilder()
                    .where(NavigationAddressBeanDao.Properties.Histroyid.eq(bean.getHistroyid()))
                    .build()
                    .list();
            for (NavigationAddressBean addressBean : list) {
                addressBeanDao.delete(addressBean);
            }
        }
        historyBeanDao.deleteAll();
    }

    /**
     * 添加消防单位到数据库
     *
     * @param context
     * @param list
     */
    public static void setFireUnitList(Context context, List<FireUnitBean> list) {
        if (list == null) {
            return;
        }
        FireUnitBeanDao fireUnitBeanDao = EntityManager.getInstance(context).getFireUnitBeanDao();
        fireUnitBeanDao.insertOrReplaceInTx(list);
        fireUnitBeanDao.detachAll();
    }

    /**
     * 获取所有消防单位
     *
     * @param context
     * @return
     */
    public static List<FireUnitBean> getFireUnitList(Context context) {
        FireUnitBeanDao fireUnitBeanDao = EntityManager.getInstance(context).getFireUnitBeanDao();
        List<FireUnitBean> list = fireUnitBeanDao.queryBuilder()
                .build()
                .list();
        return list;
    }

    /**
     * 获取消防总队
     *
     * @param context
     * @return
     */
    public static List<FireUnitBean> getRootFireUnit(Context context) {
        FireUnitBeanDao fireUnitBeanDao = EntityManager.getInstance(context).getFireUnitBeanDao();
        List<FireUnitBean> list = fireUnitBeanDao.queryBuilder()
                .where(FireUnitBeanDao.Properties.Parent.eq("{\"id\":\"\"}"))
                .build()
                .list();
        return list;
    }

    /**
     * 获取指定消防队
     *
     * @param context
     * @return
     */
    public static List<FireUnitBean> getFireUnitById(Context context, String unitId) {
        FireUnitBeanDao fireUnitBeanDao = EntityManager.getInstance(context).getFireUnitBeanDao();
        List<FireUnitBean> list = fireUnitBeanDao.queryBuilder()
                .where(FireUnitBeanDao.Properties.Id.eq(unitId))
                .build()
                .list();
        return list;
    }

    /**
     * 获取消防单位的子单位
     *
     * @param context
     * @return
     */
    public static List<FireUnitBean> getFireUnitListByParant(Context context, String parantId) {
        FireUnitBeanDao fireUnitBeanDao = EntityManager.getInstance(context).getFireUnitBeanDao();
        List<FireUnitBean> list = fireUnitBeanDao.queryBuilder()
                .where(FireUnitBeanDao.Properties.Parent.eq("{\"id\":\"" + parantId + "\"}"))
                .build()
                .list();
        return list;
    }

    /**
     * 删除所有消防单位
     *
     * @param context
     */
    public static void deleteFireUnitList(Context context) {
        FireUnitBeanDao fireUnitBeanDao = EntityManager.getInstance(context).getFireUnitBeanDao();
        fireUnitBeanDao.deleteAll();
    }


    /**
     * 添加定位点到数据库
     *
     * @param context
     * @param list
     */
    public static void setLocationList(Context context, List<LocationBean> list) {
        if (list == null) {
            return;
        }
        LocationBeanDao unitVMDao = EntityManager.getInstance(context).getLocationBeanDao();
        unitVMDao.insertOrReplaceInTx(list);
        unitVMDao.detachAll();
    }

    /**
     * 添加定位点
     *
     * @param context
     * @param locationBean
     */
    public static void setLocation(Context context, LocationBean locationBean) {
        if (locationBean == null) {
            return;
        }
        LocationBeanDao unitVMDao = EntityManager.getInstance(context).getLocationBeanDao();
        unitVMDao.insertOrReplace(locationBean);
        unitVMDao.detachAll();
    }

    /**
     * 获取所有定位点
     *
     * @param context
     * @return
     */
    public static List<LocationBean> getLocationList(Context context) {
        LocationBeanDao unitVMDao = EntityManager.getInstance(context).getLocationBeanDao();
        List<LocationBean> list = unitVMDao.queryBuilder()
                .limit(100)
                .build()
                .list();
        return list;
    }

    public static int getAllSize(Context context) {
        LocationBeanDao unitVMDao = EntityManager.getInstance(context).getLocationBeanDao();
        return (int) unitVMDao.count();
    }

    /**
     * 获取指定数量的定位点
     *
     * @param context
     * @param number
     * @return
     */
    public static List<LocationBean> getLocationList(Context context, int number) {
        LocationBeanDao unitVMDao = EntityManager.getInstance(context).getLocationBeanDao();
        List<LocationBean> list = unitVMDao.queryBuilder()
                .limit(number)
                .build()
                .list();
        return list;
    }

    /**
     * 删除所有定位点
     *
     * @param context
     */
    public static void deleteLocation(Context context) {
        LocationBeanDao unitVMDao = EntityManager.getInstance(context).getLocationBeanDao();
        unitVMDao.deleteAll();
    }

    /**
     * 删除指定数量的定位点
     *
     * @param context
     * @param number
     */
    public static void deleteLocation(Context context, int number) {
        List<LocationBean> list = getLocationList(context, number);
        LocationBeanDao unitVMDao = EntityManager.getInstance(context).getLocationBeanDao();
        for (LocationBean bean : list) {
            unitVMDao.deleteByKey(bean.getId());
        }
    }


    /**
     * 添加区域到数据库
     *
     * @param context
     * @param list
     */
    public static void setAreaList(Context context, List<AreaBean> list) {
        if (list == null) {
            return;
        }
        AreaBeanDao areaBeanDao = EntityManager.getInstance(context).getAreaBeanDao();
        areaBeanDao.insertOrReplaceInTx(list);
        areaBeanDao.detachAll();
    }

    /**
     * 获取所有区域
     *
     * @param context
     * @return
     */
    public static List<AreaBean> getAreaList(Context context) {
        AreaBeanDao areaBeanDao = EntityManager.getInstance(context).getAreaBeanDao();
        List<AreaBean> list = areaBeanDao.queryBuilder()
                .build()
                .list();
        return list;
    }

    /**
     * 删除所有区域
     *
     * @param context
     */
    public static void deleteAreaList(Context context) {
        AreaBeanDao areaBeanDao = EntityManager.getInstance(context).getAreaBeanDao();
        areaBeanDao.deleteAll();
    }
}
