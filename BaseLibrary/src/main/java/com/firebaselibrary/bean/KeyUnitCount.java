package com.firebaselibrary.bean;

import java.util.List;

/**
 * Created by yrx on 15/9/20.
 *
 */
public class KeyUnitCount implements Comparable<KeyUnitCount> {

    /// <summary>
    /// 消防单位Id
    /// </summary>
    public String UnitId;

    /// <summary>
    /// 消防单位名称
    /// </summary>
    public String UnitName;

    /// <summary>
    /// 本消防单位的重点单位总数
    /// </summary>
    public int ImportmentUnitSelfTotal;

    /// <summary>
    /// 本消防单位及其下属单位的重点单位总数
    /// </summary>
    public int ImportmentUnitTotal;

    private boolean hasChild;

    public List<KeyUnitCount> ChildrenImportmentUnitCount;

    public String getUnitId() {
        return UnitId;
    }

    public void setUnitId(String unitId) {
        UnitId = unitId;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public int getImportmentUnitSelfTotal() {
        return ImportmentUnitSelfTotal;
    }

    public void setImportmentUnitSelfTotal(int importmentUnitSelfTotal) {
        ImportmentUnitSelfTotal = importmentUnitSelfTotal;
    }

    public int getImportmentUnitTotal() {
        return ImportmentUnitTotal;
    }

    public void setImportmentUnitTotal(int importmentUnitTotal) {
        ImportmentUnitTotal = importmentUnitTotal;
    }

    public List<KeyUnitCount> getChildrenImportmentUnitCount() {
        return ChildrenImportmentUnitCount;
    }

    public void setChildrenImportmentUnitCount(List<KeyUnitCount> childrenImportmentUnitCount) {
        ChildrenImportmentUnitCount = childrenImportmentUnitCount;
    }

    public int getTotal() {

        return getImportmentUnitTotal();
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    @Override
    public int compareTo(KeyUnitCount k) {


//        if (this.ImportmentUnitTotal - k.getImportmentUnitTotal() > 0) {
//            return -1;
//        }
//
//        if (this.ImportmentUnitTotal - k.getImportmentUnitTotal() < 0) {
//            return 1;
//        }
//
//        if (this.ImportmentUnitTotal - k.getImportmentUnitTotal() == 0) {
//            return 0;
//        }
//
//        return 0;

        return -(this.ImportmentUnitTotal - k.getImportmentUnitTotal());
    }
}
