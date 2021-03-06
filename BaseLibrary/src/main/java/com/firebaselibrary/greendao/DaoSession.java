package com.firebaselibrary.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.firebaselibrary.bean.AreaBean;
import com.firebaselibrary.bean.fireunit.FireUnitBean;
import com.firebaselibrary.bean.LocationBean;
import com.firebaselibrary.bean.NavigationAddressBean;
import com.firebaselibrary.bean.NavigationHistoryBean;

import com.firebaselibrary.greendao.AreaBeanDao;
import com.firebaselibrary.greendao.FireUnitBeanDao;
import com.firebaselibrary.greendao.LocationBeanDao;
import com.firebaselibrary.greendao.NavigationAddressBeanDao;
import com.firebaselibrary.greendao.NavigationHistoryBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig areaBeanDaoConfig;
    private final DaoConfig fireUnitBeanDaoConfig;
    private final DaoConfig locationBeanDaoConfig;
    private final DaoConfig navigationAddressBeanDaoConfig;
    private final DaoConfig navigationHistoryBeanDaoConfig;

    private final AreaBeanDao areaBeanDao;
    private final FireUnitBeanDao fireUnitBeanDao;
    private final LocationBeanDao locationBeanDao;
    private final NavigationAddressBeanDao navigationAddressBeanDao;
    private final NavigationHistoryBeanDao navigationHistoryBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        areaBeanDaoConfig = daoConfigMap.get(AreaBeanDao.class).clone();
        areaBeanDaoConfig.initIdentityScope(type);

        fireUnitBeanDaoConfig = daoConfigMap.get(FireUnitBeanDao.class).clone();
        fireUnitBeanDaoConfig.initIdentityScope(type);

        locationBeanDaoConfig = daoConfigMap.get(LocationBeanDao.class).clone();
        locationBeanDaoConfig.initIdentityScope(type);

        navigationAddressBeanDaoConfig = daoConfigMap.get(NavigationAddressBeanDao.class).clone();
        navigationAddressBeanDaoConfig.initIdentityScope(type);

        navigationHistoryBeanDaoConfig = daoConfigMap.get(NavigationHistoryBeanDao.class).clone();
        navigationHistoryBeanDaoConfig.initIdentityScope(type);

        areaBeanDao = new AreaBeanDao(areaBeanDaoConfig, this);
        fireUnitBeanDao = new FireUnitBeanDao(fireUnitBeanDaoConfig, this);
        locationBeanDao = new LocationBeanDao(locationBeanDaoConfig, this);
        navigationAddressBeanDao = new NavigationAddressBeanDao(navigationAddressBeanDaoConfig, this);
        navigationHistoryBeanDao = new NavigationHistoryBeanDao(navigationHistoryBeanDaoConfig, this);

        registerDao(AreaBean.class, areaBeanDao);
        registerDao(FireUnitBean.class, fireUnitBeanDao);
        registerDao(LocationBean.class, locationBeanDao);
        registerDao(NavigationAddressBean.class, navigationAddressBeanDao);
        registerDao(NavigationHistoryBean.class, navigationHistoryBeanDao);
    }
    
    public void clear() {
        areaBeanDaoConfig.clearIdentityScope();
        fireUnitBeanDaoConfig.clearIdentityScope();
        locationBeanDaoConfig.clearIdentityScope();
        navigationAddressBeanDaoConfig.clearIdentityScope();
        navigationHistoryBeanDaoConfig.clearIdentityScope();
    }

    public AreaBeanDao getAreaBeanDao() {
        return areaBeanDao;
    }

    public FireUnitBeanDao getFireUnitBeanDao() {
        return fireUnitBeanDao;
    }

    public LocationBeanDao getLocationBeanDao() {
        return locationBeanDao;
    }

    public NavigationAddressBeanDao getNavigationAddressBeanDao() {
        return navigationAddressBeanDao;
    }

    public NavigationHistoryBeanDao getNavigationHistoryBeanDao() {
        return navigationHistoryBeanDao;
    }

}
