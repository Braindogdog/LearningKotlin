package common.baidunavigationlibrary.module;

/**
 * Created by MHshachang on 2017/7/16.
 */

public class OfflineMap {

    /**
     * 离线地图包名称
     * 如：yinchuan_360
     */
    private String lp;


    /**
     * 地图所属区域
     * 如：银川市
     */
    private String ln;


    public String getDescription() {
        return ln;
    }

    public void setDescription(String description) {
        this.ln = description;
    }

    public String getName() {
        return lp;
    }

    public void setName(String name) {
        this.lp = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfflineMap that = (OfflineMap) o;

        return lp != null ? lp.equals(that.lp) : that.lp == null;

    }

    @Override
    public int hashCode() {
        return lp != null ? lp.hashCode() : 0;
    }
}
