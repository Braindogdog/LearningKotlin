package com.firebaselibrary.database;

import android.content.Context;

import com.firebaselibrary.greendao.DaoMaster;
import com.firebaselibrary.greendao.DaoSession;

/**
 * Created by chasen on 2018/4/13.
 * 数据库管理类
 */

public class DBManager {
    private final static String dbName = "fire_station_db";
    private static DBManager mInstance;
    private DaoMaster mDaoMaster;
    private Context context;
    private DaoSession mDaoSession;

    private DBManager(Context context) {
        this.context = context;
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        DaoMaster mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoMaster getMaster() {
        return mDaoMaster;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }
}
