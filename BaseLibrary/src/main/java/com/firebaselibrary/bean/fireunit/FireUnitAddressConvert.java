package com.firebaselibrary.bean.fireunit;

import org.greenrobot.greendao.converter.PropertyConverter;

import common.baselibrary.baseutil.GsonUtil;

/**
 * Created by chasen on 2018/5/23.
 */

public class FireUnitAddressConvert implements PropertyConverter<FireUnitAddressBean, String> {
    @Override
    public FireUnitAddressBean convertToEntityProperty(String databaseValue) {
        return GsonUtil.json2Bean(databaseValue, FireUnitAddressBean.class);
    }

    @Override
    public String convertToDatabaseValue(FireUnitAddressBean entityProperty) {
        return GsonUtil.obj2String(entityProperty);
    }
}
