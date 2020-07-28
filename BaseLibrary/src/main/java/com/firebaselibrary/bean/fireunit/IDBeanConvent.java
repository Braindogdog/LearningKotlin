package com.firebaselibrary.bean.fireunit;

import com.firebaselibrary.bean.IDBean;

import org.greenrobot.greendao.converter.PropertyConverter;

import common.baselibrary.baseutil.GsonUtil;

/**
 * Created by chasen on 2018/5/23.
 */

public class IDBeanConvent implements PropertyConverter<IDBean, String> {
    @Override
    public IDBean convertToEntityProperty(String databaseValue) {
        return GsonUtil.json2Bean(databaseValue, IDBean.class);
    }

    @Override
    public String convertToDatabaseValue(IDBean entityProperty) {
        return GsonUtil.obj2String(entityProperty);
    }
}
