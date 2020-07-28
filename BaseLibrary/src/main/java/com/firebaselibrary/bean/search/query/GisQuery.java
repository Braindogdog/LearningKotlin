package com.firebaselibrary.bean.search.query;

import android.util.Log;

import com.krx.ydzh.commoncore.config.ConfigManager;
import com.krx.ydzh.commoncore.utils.ConvertUtils;

import org.creation.common.models.BaseQuery;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GisQuery extends BaseQuery {
    private static final long serialVersionUID = -2347107034703245943L;
    private double longitude;
    private double latitude;
    private double radius;
    private int page;
    private int pageSize;
    private boolean isAddRadius;

    public double getRadius() {
        Log.e("111111111111111", isAddRadius + "");
        if (!isAddRadius) {
            return 1000000000;
        } else {
            return radius;
        }
    }


    public int getPage() {
        return 1;
    }

    public int getPageSize() {
        return ConvertUtils.parseInt(ConfigManager.getInstance().getBaseConfig("maker_count"), 500);
    }

}
