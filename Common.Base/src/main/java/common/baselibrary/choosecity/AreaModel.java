package common.baselibrary.choosecity;

/**
 * Created by chasen on 2018/3/22.
 */

public class AreaModel {
    private String name;
    private int code;
    private int cityCode;
    private int provinceCode;

    public AreaModel() {
    }

    public AreaModel(String name, int code, int cityCode, int provinceCode) {
        this.name = name;
        this.code = code;
        this.cityCode = cityCode;
        this.provinceCode = provinceCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getPickerViewText() {
        return name;
    }
}
