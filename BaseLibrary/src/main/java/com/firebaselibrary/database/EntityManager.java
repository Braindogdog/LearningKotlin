package com.firebaselibrary.database;

import android.content.Context;

import com.firebaselibrary.greendao.AreaBeanDao;
import com.firebaselibrary.greendao.FireUnitBeanDao;
import com.firebaselibrary.greendao.LocationBeanDao;
import com.firebaselibrary.greendao.NavigationAddressBeanDao;
import com.firebaselibrary.greendao.NavigationHistoryBeanDao;

/**
 * Created by chasen on 2018/4/13.
 * 获取数据库表格工具类
 */

public class EntityManager {
    private static EntityManager entityManager;
    private Context context;

    private EntityManager(Context context) {
        this.context = context;
    }

    /**
     * 创建NavigationAddressBeanDao表实例
     *
     * @return
     */
    public NavigationAddressBeanDao getNavigationAddressBeanDao() {
        NavigationAddressBeanDao beanDao = DBManager.getInstance(context).getSession().getNavigationAddressBeanDao();
        return beanDao;
    }

    /**
     * 创建NavigationHistoryBeanDao表实例
     *
     * @return
     */
    public NavigationHistoryBeanDao getNavigationHistoryBeanDao() {
        NavigationHistoryBeanDao beanDao = DBManager.getInstance(context).getSession().getNavigationHistoryBeanDao();
        return beanDao;
    }

    /**
     * 创建FireUnitBeanDao表实例
     *
     * @return
     */
    public FireUnitBeanDao getFireUnitBeanDao() {
        FireUnitBeanDao beanDao = DBManager.getInstance(context).getSession().getFireUnitBeanDao();
        return beanDao;
    }

    /**
     * 创建UnitVMDao表实例
     *
     * @return
     */
    public LocationBeanDao getLocationBeanDao() {
        LocationBeanDao beanDao = DBManager.getInstance(context).getSession().getLocationBeanDao();
        return beanDao;
    }

    /**
     * 创建AreaBeanDao表实例
     *
     * @return
     */
    public AreaBeanDao getAreaBeanDao() {
        AreaBeanDao beanDao = DBManager.getInstance(context).getSession().getAreaBeanDao();
        return beanDao;
    }

    /**
     * 创建单例
     *
     * @return
     */
    public static EntityManager getInstance(Context context) {
        if (entityManager == null) {
            entityManager = new EntityManager(context);
        }
        return entityManager;
    }
}