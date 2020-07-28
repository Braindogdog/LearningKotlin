package common.baselibrary.choosecity;

import java.util.List;

/**
 * Created by chasen on 2018/3/22.
 */

public class AddressModel {
    private List<AreaModel> listAreas;
    private List<CityModel> listCitys;
    private List<ProvinceModel> listProvinces;

    public List<AreaModel> getListAreas() {
        return listAreas;
    }

    public void setListAreas(List<AreaModel> listAreas) {
        this.listAreas = listAreas;
    }

    public List<CityModel> getListCitys() {
        return listCitys;
    }

    public void setListCitys(List<CityModel> listCitys) {
        this.listCitys = listCitys;
    }

    public List<ProvinceModel> getListProvinces() {
        return listProvinces;
    }

    public void setListProvinces(List<ProvinceModel> listProvinces) {
        this.listProvinces = listProvinces;
    }
}
