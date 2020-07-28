package common.map.api.entity;

import java.util.List;

/**
 * Created by zhangmh on 2018/3/12.
 * 路线
 */
public class CreStep {
    //本步骤的路线
    private List<CreGeometry> navStepsList;


    public List<CreGeometry> getNavStepsList() {
        return navStepsList;
    }

    public void setNavStepsList(List<CreGeometry> navStepsList) {
        this.navStepsList = navStepsList;
    }
}
